package seapa.back.Entitys.UserManegerEntitys.ApostasEntitys;

import lombok.Data;
import seapa.back.Entitys.UserManegerEntitys.EquipesEntitys.Equipe;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SEAPA_APOSTA_COMUM")
@SequenceGenerator(name = "aposta_comum_seq", sequenceName = "aposta_comum_seq", allocationSize = 1, initialValue = 1)
@Data
public class ApostaComum implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aposta_comum_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gerenciador_aposta_id")
    private GerenciadorApostas gerenciador;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_fim")
    private Date dataFim;

    @Column(name = "valor_aposta")
    private Float valor;

    @JoinColumn(name = "equipe_vencedora_id")
    private Equipe vencedor;

    @Column(name = "empate")
    private Boolean empate;

    @Column(name = "tipo_aposta")
    private String tipoAposta;

    @Column(name = "tipo_gerenciamento")
    private String tipoGerenciamento;

    @Column(name = "status_aposta")
    private String statusAposta;

    @Column(name = "anulado")
    private Boolean anulado;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="conta_usuario_id")
    private List<ParticipanteAposta> apostadores;
}
