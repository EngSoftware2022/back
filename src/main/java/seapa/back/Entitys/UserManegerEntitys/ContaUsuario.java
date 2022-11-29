package seapa.back.Entitys.UserManegerEntitys;

import jakarta.persistence.*;
import lombok.Data;
import seapa.back.Audit.Auditable;


@Entity
@Table(name = "SEAPA_CONTA_USUARIO")
@NamedQueries(value = {
        @NamedQuery(name = ContaUsuario.FIND_CONTA_USUARIO_ID_BY_USUARIO_E_SENHA, query = ContaUsuario.FIND_CONTA_USUARIO_ID_BY_USUARIO_E_SENHA)
})
@SequenceGenerator(name = "conta_usuario_seq", sequenceName = "conta_usuario_seq", allocationSize = 1, initialValue = 1)
@Data
public class ContaUsuario extends Auditable<String> {

    public static final String FIND_CONTA_USUARIO_ID_BY_USUARIO_E_SENHA = "SELECT cu.id FROM ContaUsuario cu WHERE cu.nomeUsuario = :nomeUsuario AND cu.senha = :senha";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_usuario_seq")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "nome_usuario", nullable = false, unique = true)
    private String nomeUsuario;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "status_conta", nullable = false)
    private String statusConta;
}
