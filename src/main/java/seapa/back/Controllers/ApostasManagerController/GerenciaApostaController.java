package seapa.back.Controllers.ApostasManagerController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.UserManegerEntitys.ApostasEntitys.GerenciadorApostas;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.Grupo;
import seapa.back.Models.CriarGerenciaAposta;
import seapa.back.Models.EditarGerenciaAposta;
import seapa.back.Repository.ApostasManagerRepositosy.GerenciadorApostasRepository;
import seapa.back.Repository.TeamManagerRepository.TimeRepository;

import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping(value = "/gerenciaAposta")
public class GerenciaApostaController {
    @Autowired
    private GerenciadorApostasRepository gerenciadorApostasRepository;
    @Autowired
    private TimeRepository timeRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Object> insertGerenciadorAposta(@RequestBody CriarGerenciaAposta gerenciadorApostas) {
        Grupo grupo = timeRepository.findById(gerenciadorApostas.getGrupoId()).isPresent() ? timeRepository.findById(gerenciadorApostas.getGrupoId()).get() : null;

        if (grupo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        GerenciadorApostas gerenciaAposta = gerenciadorApostas.conversor(grupo);
        gerenciadorApostasRepository.save(gerenciaAposta);
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/editarGerenciaAposta")
    public ResponseEntity<Object> editarGerenciadorAposta(@RequestBody EditarGerenciaAposta editarGerenciaAposta) {
        if (Objects.equals(editarGerenciaAposta.getDataInicio(), new Date())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Grupo grupo = timeRepository.findById(editarGerenciaAposta.getGrupoId()).isPresent() ? timeRepository.findById(editarGerenciaAposta.getGrupoId()).get() : null;

        if (grupo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (Objects.equals(grupo.getModeradorId(), editarGerenciaAposta.getIdUsuario())) {
            GerenciadorApostas gerenciaApostaExistente = gerenciadorApostasRepository.findById(editarGerenciaAposta.getIdGerenciaAposta()).isPresent() ? gerenciadorApostasRepository.findById(editarGerenciaAposta.getIdGerenciaAposta()).get() : null;

            if (gerenciaApostaExistente == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            GerenciadorApostas gerenciaApostaAtualizado = editarGerenciaAposta.conversor(grupo);
            gerenciaApostaAtualizado.setId(gerenciaApostaExistente.getId());

            gerenciadorApostasRepository.save(gerenciaApostaAtualizado);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

