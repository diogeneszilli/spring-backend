package com.dogezilli.gestaoprojetos.service;

import com.dogezilli.gestaoprojetos.model.Processo;
import com.dogezilli.gestaoprojetos.model.QParecer;
import com.dogezilli.gestaoprojetos.model.QProcesso;
import com.dogezilli.gestaoprojetos.repository.ProcessoRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Service
public class ProcessoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProcessoRepository repository;

    public Iterable<Processo> findAll(BooleanBuilder predicate, PageRequest pagination) {
        return this.repository.findAll(predicate, pagination);
    }

    public Iterable<Processo> findAllParecerPendente(Long id) {
        QProcesso qProcesso = QProcesso.processo;
        QParecer qParecer = QParecer.parecer;
        JPAQuery<Processo> query = new JPAQuery<Processo>(entityManager);

        if (Objects.nonNull(id)) {
            return query
                    .from(qProcesso)
                    .innerJoin(qProcesso.pareceres ,qParecer)
                    .where(qParecer.usuario.id.eq(id).and(qParecer.pendente.isTrue()))
                    .fetch();
        }
        return query
                .from(qProcesso)
                .innerJoin(qProcesso.pareceres ,qParecer)
                .where(qParecer.pendente.isTrue())
                .fetch();

    }

    public Processo insert(Processo responsavel) {
        this.repository.save(responsavel);
        return responsavel;
    }

    public Processo findById(Long id) {
        return (Processo) this.repository.findById(id).get();
    }

    public Processo update(Processo responsavel) {
        return (Processo) this.repository.save(responsavel);
    }

    public long count() {
        return this.repository.count();
    }
}
