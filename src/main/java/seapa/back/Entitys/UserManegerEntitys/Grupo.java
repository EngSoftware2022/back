package seapa.back.Entitys.UserManegerEntitys;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SEAPA_GRUPO")
@Data
public class Grupo {

    @Id
    @GeneratedValue(generator = "SEQ_ATIVO", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String dataCriacao;

    @Column(nullable = false)
    private String dataUltimaAlteracao;

    @Column(nullable = false)
    private String statusConta;
}
