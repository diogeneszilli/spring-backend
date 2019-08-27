package com.dogezilli.gestaoprojetos.utils;

import com.dogezilli.gestaoprojetos.model.Usuario;
import com.dogezilli.gestaoprojetos.service.UsuarioService;
import com.querydsl.core.BooleanBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;

@Getter
@Setter
public class ResultPageUsuario {

    ArrayList<Usuario> records;
    long records_number;

    public ResultPageUsuario(ArrayList<Usuario> records, long records_number) {
        this.records = records;
        this.records_number = records_number;
    }

    public static ResultPageUsuario getResultPage(Integer page, Integer size, UsuarioService service) {
        BooleanBuilder predicate = new BooleanBuilder();
        Iterable<Usuario> recordsPagination = service.findAll(predicate,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
        ArrayList<Usuario> records = new ArrayList();
        recordsPagination.forEach((item) -> {
            records.add(item);
        });
        long records_number = service.count();
        return new ResultPageUsuario(records, records_number);
    }
}
