package seapa.back.Models.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class MovimentacaoMonetariaDTO {

    private String tipoMovimentacao;

    private BigDecimal valorMovimentacao;

    private Date dataUltimaModificacao;
}
