package seapa.back.Entitys.UserManegerEntitys.GrupoEntitys;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(name = "integrantes_seq", sequenceName = "integrantes_seq", allocationSize = 1, initialValue = 1)
@Table(name = "SEAPA_INTEGRANTES_GRUPO")
public class IntegrantesGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "integrantes_seq")
    private Long id;

    @Column(nullable = false)
    @JoinColumn(name = "grupo_id")
    private Long idUsuarioIntegrante;

    @Column(nullable = false)
    private String nomeUsuario;

}
