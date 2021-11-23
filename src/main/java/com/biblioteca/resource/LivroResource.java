package com.biblioteca.resource;

import com.biblioteca.entity.Cliente;
import com.biblioteca.entity.Livro;
import com.biblioteca.repository.LivroRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/livro")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LivroResource {

    @Inject
    private LivroRepository livroRepository;

    @GET
    public List<Livro> listarLivros() {
        return livroRepository.listAll();
    }

    @GET
    @Path("/nome/{nome}")
    public List<Livro> buscarLivrosPorNome(@PathParam("nome") String nome) {
        return livroRepository.listByName(nome);
    }

    @GET
    @Path("{id}")
    public Livro buscarLivroPorId(@PathParam("id") Long id) {
        return livroRepository.findById(id);
    }

    @POST
    @Transactional
    public Livro salvarLivro(@Valid Livro livro){
        livro.persist();
        return livro;
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Livro alterarLivroPorId(@PathParam("id") Long id, @Valid Livro livro) {
        Optional<Livro> livroOp = livroRepository.findByIdOptional(id);

        if (livroOp.isPresent()) {
            livro.persist();
            livroOp.ifPresentOrElse(Livro::delete, ()-> {throw  new NotFoundException();});
            return livro;
        } else {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletarLivroPorId(@PathParam("id") Long id) {
        Optional<Livro> livroOp = livroRepository.findByIdOptional(id);

        livroOp.ifPresentOrElse(Livro::delete, () -> {
            throw new NotFoundException();
        });
    }

}
