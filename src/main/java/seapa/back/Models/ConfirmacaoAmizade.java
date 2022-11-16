package seapa.back.Models;

import lombok.Data;

@Data
public class ConfirmacaoAmizade {

    private String usuarioCorrente;

    private String usuarioSolicitante;

    private Boolean aceitou;
}
