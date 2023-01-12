package seapa.back.Controllers.ApostasManagerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.CentralDeGerenciamentoDasApostas;
import seapa.back.Models.DTOs.ApostaDTO;
import seapa.back.Repository.ApostasManagerRepositosy.CentralDeGerenciamentoDeApostasRepository;
import seapa.back.Settings.Mappers.ApostasMapper;
import seapa.back.Settings.Mappers.CentralDeGerenciamentoDeApostasMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/centralDeApostas")
public class CentralDeGerenciamentoDeApostasController {
    @Autowired
    private CentralDeGerenciamentoDeApostasMapper centralDeGerenciamentoDeApostasMapper;

    @Autowired
    private CentralDeGerenciamentoDeApostasRepository centralDeGerenciamentoDeApostasRepository;

    @Autowired
    private ApostasMapper apostasMapper;

    @GetMapping(value = "/todasApostasCadastradas")
    public ResponseEntity<List<ApostaDTO>> listarTodasAsApostasCadastradasNoGerenciador(@RequestParam Long centralGerenciamentoId) {
        CentralDeGerenciamentoDasApostas centralDeGerenciamentoDasApostas = Optional.of(centralDeGerenciamentoDeApostasRepository.findById(centralGerenciamentoId).get()).orElseThrow(() -> new RuntimeException("Central de gerenciamento não encontrada!"));

        List<ApostaDTO> listaApostasDTO = apostasMapper.toApostaDTOList(centralDeGerenciamentoDasApostas.getApostas());

        return ResponseEntity.status(HttpStatus.CREATED).body(listaApostasDTO);
    }

}
