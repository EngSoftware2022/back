package seapa.back.Entitys.UserManegerEntitys.GrupoEntitys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@SequenceGenerator(name = "integrantes_seq", sequenceName = "integrantes_seq", allocationSize = 1, initialValue = 1)
@Table(name = "SEAPA_INTEGRANTES_GRUPO")
public class IntegrantesGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "integrantes_seq")
    private Long id;

    @Column(nullable = false)
    private Long idUsuarioIntegrante;

    @Column(nullable = false)
    private String nomeUsuario;

}
