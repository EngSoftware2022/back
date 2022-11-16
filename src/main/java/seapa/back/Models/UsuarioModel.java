package seapa.back.Models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioModel {

    private String nome;

    private String ultimoNome;

    private String nomeDeUsuario;

    private String email;

    private String senha;

    private String cpf;

    private LocalDate dataNascimento;

    private String telefone;

}
