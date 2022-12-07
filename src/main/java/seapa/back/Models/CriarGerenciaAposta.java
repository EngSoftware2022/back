package seapa.back.Models;

import lombok.Data;
import seapa.back.Entitys.UserManegerEntitys.ApostasEntitys.GerenciadorApostas;
import seapa.back.Entitys.UserManegerEntitys.EquipesEntitys.Equipe;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.Grupo;

import java.util.Date;

@Data
public class CriarGerenciaAposta {

    private Long grupoId;

    private Date dataInicio;

    private Date dataFim;

    private String tipoEsporte;

    private Long idEquipeAlpha;

    private Long idEquipeBeta;

    private String tipoGerenciamento;

    public GerenciadorApostas conversor(Grupo grupo) {
        GerenciadorApostas aposta = new GerenciadorApostas();
        aposta.setGrupo(grupo);
        aposta.setDataInicio(this.dataInicio);
        aposta.setDataFim(this.dataFim);
        aposta.setTipoEsporte(this.tipoEsporte);
        aposta.setTipoGerenciamento(this.tipoGerenciamento);
        Equipe equipeAlpha = new Equipe();
        equipeAlpha.setId(this.idEquipeAlpha);
        Equipe equipeBeta = new Equipe();
        equipeBeta.setId(this.idEquipeBeta);
        aposta.setEquipeAlpha(equipeAlpha);
        aposta.setEquipeBeta(equipeBeta);
        return aposta;
    }
}
