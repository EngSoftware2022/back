package seapa.back.Entitys.UserManegerEntitys;

import lombok.Data;
import seapa.back.Audit.Auditable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SEAPA_CONTA_USUARIO")
@SequenceGenerator(name = "conta_usuario_seq", sequenceName = "conta_usuario_seq", allocationSize = 1, initialValue = 1)
@Data
public class ContaUsuario extends Auditable<String> {

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

    @Column(name = "status_conta", nullable = false)
    private String statusConta;
}
