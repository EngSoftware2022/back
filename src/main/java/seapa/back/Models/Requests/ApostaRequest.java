package seapa.back.Models.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApostaRequest {

    private Long centralApostasId;

    private String descricaoAposta;

    private Date dataInicio;

    private Date dataFim;

    private String tipoGerenciamento;

    private String tipoAposta;

    private BigDecimal valorAposta;

    private List<OpcoesApostaRequest> opcoesAposta;

}
