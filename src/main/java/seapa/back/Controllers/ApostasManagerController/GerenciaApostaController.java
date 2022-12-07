package seapa.back.Controllers.ApostasManagerController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
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
    private TimeRepository timeRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void insertGerenciadorAposta(@RequestBody CriarGerenciaAposta gerenciadorApostas) {
        try {
            GerenciadorApostas gerenciaAposta = gerenciadorApostas.conversor();
            gerenciadorApostasRepository.save(gerenciaAposta);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/editarGerenciaAposta")
    public void editarGerenciadorAposta(@RequestBody EditarGerenciaAposta editarGerenciaAposta) {
        try {
            if (Objects.equals(editarGerenciaAposta.getDataInicio(), new Date())) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
            }

            Grupo grupo = timeRepository.findById(editarGerenciaAposta.getGrupoId()).isPresent() ? timeRepository.findById(editarGerenciaAposta.getGrupoId()).get() : null;

            if (grupo == null) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
            }
            if (Objects.equals(grupo.getModeradorId(), editarGerenciaAposta.getIdUsuario())) {
                GerenciadorApostas gerenciaApostaExistente = gerenciadorApostasRepository.findById(editarGerenciaAposta.getIdGerenciaAposta()).isPresent() ?
                        gerenciadorApostasRepository.findById(editarGerenciaAposta.getIdGerenciaAposta()).get() : null;

                if (gerenciaApostaExistente == null) {
                    throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
                }
                GerenciadorApostas gerenciaApostaAtualizado = editarGerenciaAposta.conversor();
                gerenciaApostaAtualizado.setId(gerenciaApostaExistente.getId());

                gerenciadorApostasRepository.save(gerenciaApostaAtualizado);
            } else {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
