package seapa.back.Entitys.UserManegerEntitys.UserEntitys;

import lombok.Data;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@Embeddable
public class UltimasMovimentacoes {

    private BigDecimal saldo1 = BigDecimal.ZERO;

    private BigDecimal saldo2 = BigDecimal.ZERO;

    private BigDecimal saldo3 = BigDecimal.ZERO;

    private BigDecimal saldo4 = BigDecimal.ZERO;

    private BigDecimal saldo5 = BigDecimal.ZERO;
}
