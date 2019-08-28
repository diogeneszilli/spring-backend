package com.dogezilli.gestaoprojetos.utils;

import com.dogezilli.gestaoprojetos.model.Processo;
import com.dogezilli.gestaoprojetos.service.ProcessoService;
import com.querydsl.core.BooleanBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;

@Getter
@Setter
public class ResultPageProcesso {

    ArrayList<Processo> records;
    long records_number;

    public ResultPageProcesso(ArrayList<Processo> records, long records_number) {
        this.records = records;
        this.records_number = records_number;
    }

    public static ResultPageProcesso getResultPage(Integer page, Integer size, ProcessoService service) {
        BooleanBuilder predicate = new BooleanBuilder();
        Iterable<Processo> recordsPagination = service.findAll(predicate,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
        ArrayList<Processo> records = new ArrayList();
        recordsPagination.forEach((item) -> {
            records.add(item);
        });
        long records_number = service.count();
        return new ResultPageProcesso(records, records_number);
    }

    public static ResultPageProcesso getResultPageParecerPendente(ProcessoService service, Long id) {
        Iterable<Processo> recordsPagination = service.findAllParecerPendente(id);
        ArrayList<Processo> records = new ArrayList();
        recordsPagination.forEach((item) -> {
            records.add(item);
        });
        long records_number = records.size();
        return new ResultPageProcesso(records, records_number);
    }
}
