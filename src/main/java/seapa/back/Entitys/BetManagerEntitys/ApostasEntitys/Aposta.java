package seapa.back.Entitys.BetManagerEntitys.ApostasEntitys;

import com.google.common.collect.Lists;
import lombok.Data;
import seapa.back.Entitys.BetManagerEntitys.ApostasUsuarioEntitys.ApostaDoUsuario;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ListaAmigos;
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
@NamedQueries(value = {
        @NamedQuery(name = Aposta.FIND_ALL_APOSTAS_BY_USUARIO_ID, query = Aposta.FIND_ALL_APOSTAS_BY_USUARIO_ID),
})
@Data
public class Aposta implements Serializable {

    public static final String FIND_ALL_APOSTAS_BY_USUARIO_ID = "SELECT a FROM Aposta a WHERE a.gerenciador.grupo.id = :grupoId";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gerenciador_id")
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

    @OneToMany(targetEntity= OpcoesAposta.class, mappedBy="aposta", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<OpcoesAposta> opcoesApostas;

    @Column(name = "valor_aposta")
    private BigDecimal valorAposta = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_aposta")
    private StatusDaApostaEnum statusAposta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "opcao_vencedora_id")
    private OpcoesAposta opcaoVencedora;

    @OneToMany(targetEntity = ApostaDoUsuario.class,mappedBy="aposta", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<ApostaDoUsuario> apostadores;

    @Transient
    private List<ApostaDoUsuario> vencedores = Lists.newArrayList();

}
