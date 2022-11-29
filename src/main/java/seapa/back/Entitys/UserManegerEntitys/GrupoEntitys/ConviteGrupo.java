package seapa.back.Entitys.UserManegerEntitys.GrupoEntitys;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SEAPA_CONVITE_GRUPO")
@Data
public class ConviteGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nomeAdmGrupo;

    @Column(nullable = false)
    private Long idUsuarioConvidado;

    @ManyToOne
    private Grupo grupo;
}
