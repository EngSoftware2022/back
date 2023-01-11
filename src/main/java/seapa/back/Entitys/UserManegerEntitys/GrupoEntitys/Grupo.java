package seapa.back.Entitys.UserManegerEntitys.GrupoEntitys;

import lombok.Data;
import seapa.back.Audit.Auditable;
import seapa.back.Entitys.BetManagerEntitys.Apostas.CentralDeGerenciamentoDasApostas;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SEAPA_GRUPO")
@Data
public class Grupo extends Auditable<String> implements Serializable {

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

    @OneToMany(mappedBy="grupo", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<CentralDeGerenciamentoDasApostas> centralDeGerenciamentoDasApostas;

    public void setIntegrantes(IntegrantesGrupo integrantes) {
        this.integrantes.add(integrantes);
    }
}
