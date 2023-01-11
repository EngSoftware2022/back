package seapa.back.Models;

import lombok.Data;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.ApostaComum;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.GerenciadorApostas;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.ParticipanteAposta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CriarAposta {
    private long usuarioId;

    private Long gerenciaApostaId;

    private Date dataInicio;

    private Date dataFim;

    private Float valorAposta;

    //private Long idEquipeVencedora;

    private Boolean empate;

    private String tipoAposta;

    private String tipoGerenciamento;

    private List<ParticipanteAposta> ganhadores = new ArrayList<>();

    public ApostaComum conversor(GerenciadorApostas gerenciadorApostas, List<ParticipanteAposta> participantesAposta) {
        ApostaComum aposta = new ApostaComum();
        aposta.setGerenciador(gerenciadorApostas);
        aposta.setDataInicio(this.dataInicio);
        aposta.setDataFim(this.dataFim);
        //aposta.setValor(this.valorAposta);
        //Equipe equipeVencedora = new Equipe();
        //equipeVencedora.setId(this.idEquipeVencedora);
        //aposta.setVencedor(equipeVencedora);
        aposta.setEmpate(this.empate);
        aposta.setTipoAposta(this.tipoAposta);
        aposta.setTipoGerenciamento(this.tipoGerenciamento);
        aposta.setStatusAposta("Aguardando"); //TODO: não tô ligado os status possíveis
        aposta.setApostadores(participantesAposta);
        aposta.setVencedores(this.ganhadores);
        //aposta.setAnulado(false);
        return aposta;
    }
}
