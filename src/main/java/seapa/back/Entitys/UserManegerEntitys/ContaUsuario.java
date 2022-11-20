package seapa.back.Entitys.UserManegerEntitys;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SEAPA_CONTA_USUARIO")
@SequenceGenerator(name = "conta_usuario_seq", sequenceName = "conta_usuario_seq", allocationSize = 1, initialValue = 1)
@Data
public class ContaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_usuario_seq")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_ultima_alteracao", nullable = false)
    private Date dataUltimaAlteracao;

    @Column(name = "status_conta", nullable = false)
    private String statusConta;
}
