package seapa.back.Entitys.BetManagerEntitys.Apostas;

import lombok.Data;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.Grupo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SEAPA_GERENCIAMENTO_APOSTAS")
@SequenceGenerator(name = "gerenciamento_apostas_seq", sequenceName = "gerenciamento_apostas_seq", allocationSize = 1, initialValue = 1)
@Data
public class CentralDeGerenciamentoDasApostas implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator = "gerenciamento_apostas_seq")
    private Long id;

    @ManyToOne
    @MapsId("grupoId")
    private Grupo grupo;

    @Column(name = "tipo_esporte")
    private String tipoEsporte;

    @OneToMany(mappedBy="gerenciador", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Aposta> apostas;

}
