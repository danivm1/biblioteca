package com.biblioteca.resource;

import com.biblioteca.entity.Cliente;
import com.biblioteca.repository.ClienteRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/cliente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    private ClienteRepository clienteRepository;

    @GET
    public List<Cliente> buscarClientes() {
        return clienteRepository.listAll();
    }

    @GET
    @Path("/nome/{nome}")
    public List<Cliente> buscarClientesPorNome(@PathParam("nome") String nome) {
        return clienteRepository.listByName(nome);
    }

    @GET
    @Path("{id}")
    public Cliente buscarClientePorId(@PathParam("id") Long id) {
        return clienteRepository.findById(id);
    }

    @POST
    @Transactional
    public Cliente salvarCliente(@Valid Cliente cliente) {
        cliente.persist();
        return cliente;
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Cliente alterarClientePorId(@PathParam("id") Long id, @Valid Cliente cliente) {
        Optional<Cliente> clienteOp = clienteRepository.findByIdOptional(id);

        if (clienteOp.isPresent()) {
            cliente.persist();
            clienteOp.ifPresentOrElse(Cliente::delete, ()-> {throw  new NotFoundException();});
            return cliente;
        } else {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletarClientePorId(@PathParam("id") Long id) {
        Optional<Cliente> clienteOp = clienteRepository.findByIdOptional(id);

        clienteOp.ifPresentOrElse(Cliente::delete, () -> {
            throw new NotFoundException();
        });
    }
}