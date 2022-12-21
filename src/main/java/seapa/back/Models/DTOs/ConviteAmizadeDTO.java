package seapa.back.Models.DTOs;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ConviteAmizadeDTO {

    private Long id;
    private Long solicitanteId;
    private String solicitanteNome;
    private Long solicitadoId;
    private String solicitadoNome;
    private Date dataSolicitacao;
    private Date dataConfirmacao;
    private String statusConvite;
}
