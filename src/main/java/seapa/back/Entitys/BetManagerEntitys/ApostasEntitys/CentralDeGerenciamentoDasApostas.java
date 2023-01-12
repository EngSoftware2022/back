package seapa.back.Entitys.BetManagerEntitys.ApostasEntitys;

import lombok.Data;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.Grupo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SEAPA_GERENCIAMENTO_APOSTAS")
@Data
public class CentralDeGerenciamentoDasApostas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @Column(name = "tipo_esporte")
    private String tipoEsporte;

    @OneToMany(targetEntity = Aposta.class, mappedBy="gerenciador", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Aposta> apostas;

}
