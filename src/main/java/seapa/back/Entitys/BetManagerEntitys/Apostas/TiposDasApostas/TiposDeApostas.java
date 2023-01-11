package seapa.back.Entitys.BetManagerEntitys.Apostas.TiposDasApostas;

import javax.persistence.Transient;
import java.util.List;

public class TiposDeApostas {

    @Transient
    private ApostasBistate apostasBistate;

    @Transient
    private List<ApostasRange> apostasRange;
}
