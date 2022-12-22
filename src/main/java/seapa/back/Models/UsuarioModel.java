package seapa.back.Models;

import lombok.Data;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.CarteiraEntitys.Banca;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.Usuario;
import seapa.back.Utils.StatusContaEnum;

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

    public ContaUsuario conversor() {
        ContaUsuario user = new ContaUsuario();
        user.setStatusConta(StatusContaEnum.ATIVO.toString());
        user.setEmail(this.email);
        user.setNomeUsuario(this.nomeDeUsuario);
        user.setSenha(this.senha);
        Usuario usuario = new Usuario();
        usuario.setCpf(this.cpf);
        usuario.setDataAniversario(this.dataNascimento);
        usuario.setNome(this.nome);
        usuario.setSobrenome(this.ultimoNome);
        usuario.setTelefone(this.telefone);
        user.setUsuario(usuario);
        Banca banca = new Banca();
        banca.setUsuario(user);
        user.setBanca(banca);

        return user;
    }


}
