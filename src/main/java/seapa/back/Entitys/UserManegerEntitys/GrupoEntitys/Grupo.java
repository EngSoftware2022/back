package seapa.back.Entitys.UserManegerEntitys.GrupoEntitys;

import lombok.Data;
import seapa.back.Audit.Auditable;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "integrantes.id")
    private List<IntegrantesGrupo> integrantes = new ArrayList<>();

    public void setIntegrantes(IntegrantesGrupo integrantes) {
        this.integrantes.add(integrantes);
    }
}
