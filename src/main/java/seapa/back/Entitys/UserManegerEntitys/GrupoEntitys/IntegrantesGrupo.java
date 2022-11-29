package seapa.back.Entitys.UserManegerEntitys.GrupoEntitys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "SEAPA_INTEGRANTES_GRUPO")
public class IntegrantesGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Long idGrupo;

    @Column(nullable = false)
    private Long idUsuarioIntegrante;

    @Column(nullable = false)
    private String nomeUsuario;
}
