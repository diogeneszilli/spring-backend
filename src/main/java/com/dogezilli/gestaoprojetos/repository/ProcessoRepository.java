package com.dogezilli.gestaoprojetos.repository;

import com.dogezilli.gestaoprojetos.model.Processo;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoRepository extends CrudRepository<Processo, Long>, QuerydslPredicateExecutor<Processo> {
}
