package seapa.back.Models;

import lombok.Data;
import seapa.back.Entitys.UserManegerEntitys.ApostasEntitys.ApostaComum;
import seapa.back.Entitys.UserManegerEntitys.ApostasEntitys.GerenciadorApostas;
import seapa.back.Entitys.UserManegerEntitys.ApostasEntitys.ParticipanteAposta;
import seapa.back.Entitys.UserManegerEntitys.EquipesEntitys.Equipe;

import java.util.Date;
import java.util.List;

@Data
public class CriarAposta {
    private long usuarioId;

    private Long gerenciaApostaId;

    private Date dataInicio;

    private Date dataFim;

    private Float valorAposta;

    private Long idEquipeVencedora;

    private Boolean empate;

    private String tipoAposta;

    private String tipoGerenciamento;

    public ApostaComum conversor(GerenciadorApostas gerenciadorApostas, List<ParticipanteAposta> participantesAposta) {
        ApostaComum aposta = new ApostaComum();
        aposta.setGerenciador(gerenciadorApostas);
        aposta.setDataInicio(this.dataInicio);
        aposta.setDataFim(this.dataFim);
        aposta.setValor(this.valorAposta);
        Equipe equipeVencedora = new Equipe();
        equipeVencedora.setId(this.idEquipeVencedora);
        aposta.setVencedor(equipeVencedora);
        aposta.setEmpate(this.empate);
        aposta.setTipoAposta(this.tipoAposta);
        aposta.setTipoGerenciamento(this.tipoGerenciamento);
        aposta.setStatusAposta("Aguardando"); //TODO: não tô ligado os status possíveis
        aposta.setApostadores(participantesAposta);
        aposta.setAnulado(false);
        return aposta;
    }
}
