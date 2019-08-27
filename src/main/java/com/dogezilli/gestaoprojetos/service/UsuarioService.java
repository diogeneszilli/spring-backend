package com.dogezilli.gestaoprojetos.service;

import com.dogezilli.gestaoprojetos.model.Usuario;
import com.dogezilli.gestaoprojetos.repository.UsuarioRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Iterable<Usuario> findAll(BooleanBuilder predicate, PageRequest pagination) {
        return this.repository.findAll(predicate, pagination);
    }

    public Iterable<Usuario> findAll(BooleanBuilder predicate) {
        return this.repository.findAll(predicate);
    }

    public Iterable<Usuario> findAll() {
        return this.repository.findAll();
    }

    public Usuario insert(Usuario responsavel) {
        this.repository.save(responsavel);
        return responsavel;
    }

    public Usuario findById(Long id) {
        return (Usuario) this.repository.findById(id).get();
    }

    public Usuario update(Usuario responsavel) {
        return (Usuario) this.repository.save(responsavel);
    }

    public void remove(Long id) {
        this.repository.deleteById(id);
    }

    public long count() {
        return this.repository.count();
    }
}
