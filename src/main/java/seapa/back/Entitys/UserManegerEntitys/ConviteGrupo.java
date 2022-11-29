package seapa.back.Entitys.UserManegerEntitys;

import lombok.Data;

import jakarta.persistence.*;


@Entity
@Table(name = "SEAPA_CONVITE_GRUPO")
@Data
public class ConviteGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "", nullable = false)
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
