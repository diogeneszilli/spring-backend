package com.dogezilli.gestaoprojetos.model;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "usuario_seq", allocationSize = 1)
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "usuario_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "login")
    @NotNull
    private String login;

    @Column(name = "senha")
    @NotNull
    private String senha;

    @Column(name = "tipo_usuario")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoUsuario tipoUsuario;

    public static class Builder {

        private Usuario usuario;

        public Builder() {
            usuario = new Usuario();
        }

        public Builder login(String login) {
            usuario.setLogin(login);
            return this;
        }

        public Builder senha(String senha) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(senha);
            usuario.setSenha(hashedPassword);
            return this;
        }

        public Builder tipoUsuario(String tipoUsuario) {
            usuario.setTipoUsuario(TipoUsuario.valueOf(tipoUsuario));
            return this;
        }

        public Usuario build() {
            return usuario;
        }

    }

}
