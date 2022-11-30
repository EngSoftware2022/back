package seapa.back.Models;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class AtualizaExtratoModel {

    private Long UsuarioId;

    private BigDecimal novoSaldo;
}
