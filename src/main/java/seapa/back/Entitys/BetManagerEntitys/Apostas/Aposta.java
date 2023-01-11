package seapa.back.Entitys.BetManagerEntitys.Apostas;

import lombok.Data;
import seapa.back.Entitys.BetManagerEntitys.Apostas.TiposDasApostas.TiposDeApostas;
import seapa.back.Utils.StatusDaApostaEnum;
import seapa.back.Utils.TiposDeApostasEnum;
import seapa.back.Utils.TiposDeGerenciamentoEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SEAPA_APOSTAS")
@SequenceGenerator(name = "aposta_comum_seq", sequenceName = "aposta_comum_seq", allocationSize = 1, initialValue = 1)
@Data
public class Aposta extends TiposDeApostas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aposta_comum_seq")
    private Long id;

    @ManyToOne
    @MapsId("gerenciadorId")
    private CentralDeGerenciamentoDasApostas gerenciador;

    @Column(name = "descricao_aposta")
    private String descricaoAposta;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_fim")
    private Date dataFim;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_gerenciamento")
    private TiposDeGerenciamentoEnum tipoGerenciamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_aposta")
    private TiposDeApostasEnum tipoAposta;

    @Column(name = "valor_aposta")
    private BigDecimal valorAposta = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_aposta")
    private StatusDaApostaEnum statusAposta;

    @OneToMany(mappedBy="aposta", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<ApostaDoUsuario> apostadores;

    @Transient
    private List<ApostaDoUsuario> vencedores;
}
