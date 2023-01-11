package seapa.back.Controllers.ApostasManagerController.Antigo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.ApostaComum;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.GerenciadorApostas;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.ParticipanteAposta;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.Grupo;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Models.CriarAposta;
import seapa.back.Repository.ApostasManagerRepositosy.ApostasRepository;
import seapa.back.Repository.ApostasManagerRepositosy.GerenciadorApostasRepository;
import seapa.back.Repository.ApostasManagerRepositosy.ParticipanteApostaRepository;
import seapa.back.Repository.TeamManagerRepository.TimeRepository;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/Apostas")
public class ApostasController {
    @Autowired
    private ApostasRepository apostasRepository;
    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private GerenciadorApostasRepository gerenciadorApostasRepository;
    @Autowired
    private ParticipanteApostaRepository participanteApostaRepository;
    @Autowired
    private ContaUsuarioRepository contaUsuarioRepository;

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{apostaID}&{grupoID}&{userID}")
    public ResponseEntity<Object> deleteAposta(@PathVariable Long apostaID, @PathVariable Long grupoID, @PathVariable Long userID) {
        if (timeRepository.findById(grupoID).get().getModeradorId() == userID) {
            if ((apostasRepository.findById(apostaID).get().getStatusAposta() == "Fechada") || (apostasRepository.findById(apostaID).get().getStatusAposta() == "Aguardando")) {
                apostasRepository.deleteById(apostaID);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/criarAposta")
    public ResponseEntity<Object> insertAposta(@RequestBody CriarAposta criarAposta) {
        GerenciadorApostas gerenciaAposta = gerenciadorApostasRepository.findById(criarAposta.getGerenciaApostaId()).isPresent() ? gerenciadorApostasRepository.findById(criarAposta.getGerenciaApostaId()).get() : null;

        if (gerenciaAposta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ContaUsuario contaUsuario = contaUsuarioRepository.findById(criarAposta.getUsuarioId()).isPresent() ? contaUsuarioRepository.findById(criarAposta.getUsuarioId()).get() : null;

        if (contaUsuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Grupo grupo = gerenciaAposta.getGrupo();

        if (grupo.getIntegrantes().stream().noneMatch(x -> Objects.equals(x.getId(), contaUsuario.getId())) && !Objects.equals(grupo.getModeradorId(), contaUsuario.getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ParticipanteAposta participanteAposta = new ParticipanteAposta();
        //participanteAposta.setParticipanteAposta(contaUsuario);
        participanteAposta.setIdApostador(contaUsuario.getId());
        //participanteAposta.setLadoEscolhido(criarAposta.getIdEquipeVencedora().toString());

        List<ParticipanteAposta> participantesAposta = new ArrayList<>();

        // TODO: não sei se precisa passar a lista de participantes aqui
        ApostaComum aposta = criarAposta.conversor(gerenciaAposta, participantesAposta);

        apostasRepository.save(aposta);

        //participanteAposta.setAposta(aposta);
        participanteAposta.setIdAposta(aposta.getId());

        participanteApostaRepository.save(participanteAposta);

        // TODO: não sei se precisa atualizar a apostaComum com a lista de participantes definida tbm
//        participantesAposta.add(participanteAposta);

//        ApostaComum apostaComParticipantes = criarAposta.conversor(gerenciaAposta, participantesAposta);
//        apostaComParticipantes.setId(aposta.getId());
//
//        apostasRepository.save(apostaComParticipantes);

        return null;
    }
}
