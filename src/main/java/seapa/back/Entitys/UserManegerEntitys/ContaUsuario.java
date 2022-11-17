package seapa.back.Entitys.UserManegerEntitys;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SEAPA_CONTA_USUARIO")
@Data
public class ContaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @CreatedDate
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @LastModifiedDate
    @Column(name = "data_ultima_alteracao", nullable = false)
    private Date dataUltimaAlteracao;

    @Column(name = "status_conta", nullable = false)
    private String statusConta;
}
