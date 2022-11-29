package seapa.back.Entitys.UserManegerEntitys.GrupoEntitys;

import lombok.Data;
import seapa.back.Audit.Auditable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SEAPA_GRUPO")
@Data
public class Grupo extends Auditable<String> {

    @Id
    @GeneratedValue(generator = "SEQ_ATIVO", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nomeGrupo;

    @Column(nullable = false)
    private Long moderadorId;

    @Column(nullable = false)
    private String nomeModerador;

    @OneToMany
    @JoinColumn(name = "integrantes.id")
    private List<IntegrantesGrupo> integrantes;

}
