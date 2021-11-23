package com.biblioteca.resource;

import com.biblioteca.entity.Cliente;
import com.biblioteca.entity.Emprestimo;
import com.biblioteca.entity.Emprestimo;
import com.biblioteca.repository.EmprestimoRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/emprestimo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmprestimoResource {

    @Inject
    private EmprestimoRepository emprestimoRepository;

    @GET
    public List<Emprestimo> listarEmprestimos(){
        return emprestimoRepository.listAll();
    }

    @GET
    @Path("{id}")
    public Emprestimo buscarEmprestimoPeloId(@PathParam("id") Long id){
        return emprestimoRepository.findById(id);
    }

    @POST
    @Transactional
    public Emprestimo salvarEmprestimo( Emprestimo emprestimo){
        emprestimo.persist();
        return emprestimo;
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Emprestimo alterarEmprestimoPorId(@PathParam("id") Long id, @Valid Emprestimo emprestimo) {
        Optional<Emprestimo> emprestimoOp = emprestimoRepository.findByIdOptional(id);

        if (emprestimoOp.isPresent()) {
            emprestimo.persist();
            emprestimoOp.ifPresentOrElse(Emprestimo::delete, ()-> {throw  new NotFoundException();});
            return emprestimo;
        } else {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletarEmprestimoPorId(@PathParam("id") Long id) {
        Optional<Emprestimo> emprestimoOp = emprestimoRepository.findByIdOptional(id);

        emprestimoOp.ifPresentOrElse(Emprestimo::delete, () -> {
            throw new NotFoundException();
        });
    }

}
