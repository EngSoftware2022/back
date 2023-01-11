package seapa.back.Entitys.UserManegerEntitys.UserEntitys;

import lombok.Data;
import seapa.back.Models.UsuarioModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "SEAPA_USUARIO")
@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1, initialValue = 1)
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "data_aniversario", nullable = false)
    private LocalDate dataAniversario;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    public void atualizar(UsuarioModel cadastroUsuario) {
        this.nome = Objects.isNull(cadastroUsuario.getNome()) ?this.nome : cadastroUsuario.getNome();
        this.sobrenome = Objects.isNull(cadastroUsuario.getUltimoNome()) ? this.sobrenome :  cadastroUsuario.getUltimoNome() ;
        this.cpf = Objects.isNull(cadastroUsuario.getCpf()) ? this.cpf : cadastroUsuario.getCpf() ;
        this.dataAniversario = Objects.isNull(cadastroUsuario.getDataNascimento()) ? this.dataAniversario : cadastroUsuario.getDataNascimento();
        this.telefone = Objects.isNull(cadastroUsuario.getTelefone()) ? this.telefone : cadastroUsuario.getTelefone() ;
    }
}