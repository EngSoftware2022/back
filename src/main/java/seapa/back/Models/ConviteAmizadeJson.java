package seapa.back.Models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode
@Data
public class ConviteAmizadeJson implements Serializable {

    public Long conviteid;
    public Long solicitanteId;
    public String nomeSolicitante;
    public String sobrenomeSolicitante;
    public String nomeUsuarioSolicitante;
    public Long solicitadoId;
    public String nomeSolicitado;
    public String sobrenomeSolicitado;
    public String nomeUsuarioSolicitado;
    public Date dataSolicitacao;
    public Date dataConfirmacao;
    public String statusConvite;

    public ConviteAmizadeJson() {

    }
}
