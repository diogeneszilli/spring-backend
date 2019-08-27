package com.dogezilli.gestaoprojetos.representation;

import com.dogezilli.gestaoprojetos.model.Usuario;

public class UsuarioRepresentation {

    public static Usuario build(Usuario usuario) {
        return new Usuario.Builder()
                .login(usuario.getLogin())
                .senha(usuario.getSenha())
                .tipoUsuario(usuario.getTipoUsuario().name())
                .build();
    }
}
