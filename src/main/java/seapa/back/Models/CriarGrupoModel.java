package seapa.back.Models;

import lombok.Data;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.Grupo;

@Data
public class CriarGrupoModel {

    private String nomeGrupo;

    private Long moderadorId;

    private String nomeModerador;

    public Grupo toGrupo() {
        Grupo grupo = new Grupo();
        grupo.setModeradorId(getModeradorId());
        grupo.setNomeGrupo(getNomeGrupo());
        grupo.setNomeModerador(getNomeModerador());
        return grupo;
    }
}
