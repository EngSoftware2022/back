package seapa.back.Models;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ConviteAmizadeDTO {

    private Long id;
    private Long solicitanteId;
    private Long solicitadoId;
    private Date dataSolicitacao;
    private Date dataConfirmacao;
    private String statusConvite;
}
