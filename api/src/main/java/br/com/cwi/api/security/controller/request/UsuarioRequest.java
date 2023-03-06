package br.com.cwi.api.security.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class UsuarioRequest {

    @NotBlank(message = "Nome não pode ser vazio.")
    private String nome;

    @NotNull(message = "Email não pode ser vazio.")
    @Email
    private String email;

    private String apelido;

    @NotBlank(message = "Senha não pode ser vazio.")
    private String senha;

    @NotNull(message = "Data de nascimento não pode ser vazio.")
    private LocalDate dataNascimento;

    private String imgPerfil;

    @NotNull(message = "Permissão precisa ser preenchido vazio.")
    @NotEmpty
    private List<String> permissoes;

}
