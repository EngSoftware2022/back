package seapa.back.Models;

import lombok.Data;

@Data
public class EditarGerenciaAposta extends CriarGerenciaAposta {

    private Long idUsuario;

    private Long idGerenciaAposta;

}
