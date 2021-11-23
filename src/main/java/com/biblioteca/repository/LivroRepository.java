package com.biblioteca.repository;

import com.biblioteca.entity.Livro;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class LivroRepository implements PanacheRepository<Livro> {
    public List<Livro> listByName(String nome){
        return list("nome", nome);
    }

    public Livro findById(Long id){
        return find("id", id).firstResult();
    }
}
