package seapa.back.Entitys.BetManagerEntitys.ApostasEntitys;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SEAPA_OPCOES_APOSTAS")
@Data
public class OpcoesAposta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aposta_id")
    private Aposta aposta;

    @Column(name = "primeira_opcao")
    private String primeiraOpcao;

    @Column(name = "segunda_opcao")
    private String segundaOpcao;
}
