package seapa.back.Entitys.UserManegerEntitys.GrupoEntitys;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SEAPA_INTEGRANTES_GRUPO")
public class IntegrantesGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @JoinColumn(name = "grupo_id")
    private Long idUsuarioIntegrante;

    @Column(nullable = false)
    private String nomeUsuario;

}
