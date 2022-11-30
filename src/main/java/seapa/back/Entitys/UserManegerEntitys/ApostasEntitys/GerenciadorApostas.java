package seapa.back.Entitys.UserManegerEntitys.ApostasEntitys;

import lombok.Data;
import seapa.back.Audit.Auditable;
import seapa.back.Entitys.UserManegerEntitys.EquipesEntitys.Equipe;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.Grupo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SEAPA_GERENCIADOR_APOSTAS")
@SequenceGenerator(name = "gerenciador_apostas_seq", sequenceName = "gerenciador_apostas_seq", allocationSize = 1, initialValue = 1)
@Data
public class GerenciadorApostas extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gerenciador_apostas_seq")
    private Long id;

    @OneToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_fim")
    private Date dataFim;

    @Column(name = "tipo_esporte")
    private String tipoEsporte;

    @JoinColumn(name = "equipe_id")
    @Column(name = "equipe_alpha_id")
    private Equipe equipeAlpha;

    @JoinColumn(name = "equipe_id")
    @Column(name = "equipe_beta_id")
    private Equipe equipeBeta;

    @Column(name = "tipo_gerenciamento")
    private String tipoGerenciamento;

    @OneToMany
    @JoinColumn(name="gerenciador_aposta_id")
    private List<ApostaComum> apostas;
}
