package com.dogezilli.gestaoprojetos.controller;

import com.dogezilli.gestaoprojetos.model.Usuario;
import com.dogezilli.gestaoprojetos.representation.UsuarioRepresentation;
import com.dogezilli.gestaoprojetos.service.UsuarioService;
import com.dogezilli.gestaoprojetos.utils.ResultPageUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

@RestController
@RequestMapping("/usuarios")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                     @RequestParam(required = false, defaultValue = "10") Integer size) {
        ResultPageUsuario result = ResultPageUsuario.getResultPage(page, size, service);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            Usuario responsavel = this.service.findById(id);
            return id.equals(responsavel.getId())
                    ? new ResponseEntity(responsavel, HttpStatus.OK)
                    : new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception err) {
            return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Usuario usuario) {
        try {
            Usuario novo = UsuarioRepresentation.build(usuario);
            this.service.insert(novo);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception err) {
            return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestBody Usuario usuario) {
        try {
            Usuario usuarioConsolidado = this.service.findById(id);
            if (Objects.nonNull(usuarioConsolidado)) {
                    this.service.update(usuario);
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
                }
        } catch (Exception err) {
            return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            Usuario usuario = this.service.findById(id);
            if (Objects.nonNull(usuario)) {
                this.service.remove(id);
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
            }
        } catch (Exception err) {
            return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
        }
    }
}
