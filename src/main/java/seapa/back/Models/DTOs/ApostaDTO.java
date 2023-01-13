package seapa.back.Models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seapa.back.Utils.StatusDaApostaEnum;
import seapa.back.Utils.TiposDeApostasEnum;
import seapa.back.Utils.TiposDeGerenciamentoEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApostaDTO {

    private Long id;

    private Long gerenciadorId;

    private String descricaoAposta;

    private Date dataInicio;

    private Date dataFim;

    private TiposDeGerenciamentoEnum tipoGerenciamento;

    private TiposDeApostasEnum tipoAposta;

    private List<OpcaoApostaDTO> opcoesApostas;

    private BigDecimal valorAposta;

    private StatusDaApostaEnum statusAposta;

    private OpcaoApostaDTO opcaoVencedora;

}
