package com.dogezilli.gestaoprojetos.controller;

import com.dogezilli.gestaoprojetos.model.Processo;
import com.dogezilli.gestaoprojetos.representation.ProcessoRepresentation;
import com.dogezilli.gestaoprojetos.service.ProcessoService;
import com.dogezilli.gestaoprojetos.utils.ResultPageProcesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

@RestController
@RequestMapping("/api/processos")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ProcessoController {

    @Autowired
    ProcessoService service;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                     @RequestParam(required = false, defaultValue = "10") Integer size) {
        ResultPageProcesso result = ResultPageProcesso.getResultPage(page, size, service);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/parecer-pendente/{id}")
    public ResponseEntity<?> findParecerPendenteByUserId(@PathVariable("id") Long id,
                                                 @RequestParam(required = false, defaultValue = "0") Integer page,
                                                 @RequestParam(required = false, defaultValue = "10") Integer size) {
        ResultPageProcesso result = ResultPageProcesso.getResultPageParecerPendente(service, id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            Processo processo = this.service.findById(id);
            return id.equals(processo.getId())
                    ? new ResponseEntity(processo, HttpStatus.OK)
                    : new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception err) {
            return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Processo processo) {
        try {
            Processo novo = ProcessoRepresentation.build(processo);
            this.service.insert(novo);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception err) {
            return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestBody Processo processo) {
        try {
            Processo processoConsolidado = this.service.findById(id);
            if (Objects.nonNull(processoConsolidado)) {
                this.service.update(processo);
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
            }
        } catch (Exception err) {
            return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
        }
    }
}
