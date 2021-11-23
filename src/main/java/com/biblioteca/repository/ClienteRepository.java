package com.biblioteca.repository;

import com.biblioteca.entity.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public List<Cliente> listByName(String nome){
        return list("nome", nome);
    }

    public Cliente findById(Long id){
        return find("id", id).firstResult();
    }
}
