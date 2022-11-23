package seapa.back.Entitys.UserManegerEntitys;

import lombok.Data;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@Embeddable
public class UltimasMovimentacoes {

    private BigDecimal saldo1;

    private BigDecimal saldo2;

    private BigDecimal saldo3;

    private BigDecimal saldo4;

    private BigDecimal saldo5;
}
