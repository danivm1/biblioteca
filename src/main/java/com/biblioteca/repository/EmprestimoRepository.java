package com.biblioteca.repository;

import com.biblioteca.entity.Cliente;
import com.biblioteca.entity.Emprestimo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EmprestimoRepository implements PanacheRepository<Emprestimo> {

    public List<Emprestimo> listByClientName(String nome){
        return list("cliente", nome);
    }

    public Emprestimo findById(Long id){
        return find("id", id).firstResult();
    }
}
