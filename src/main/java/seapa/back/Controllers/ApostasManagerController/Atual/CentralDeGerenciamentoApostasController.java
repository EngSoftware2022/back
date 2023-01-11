package seapa.back.Controllers.ApostasManagerController.Atual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seapa.back.Repository.TeamManagerRepository.TimeRepositorioAuxiliar;
import seapa.back.Settings.Mappers.CentralDeGerenciamentoDeApostasMapper;

@RestController
@RequestMapping(value = "/centralDeApostas")
public class CentralDeGerenciamentoApostasController {

    @Autowired
    private CentralDeGerenciamentoDeApostasMapper centralDeGerenciamentoDeApostasMapper;

    @Autowired
    private TimeRepositorioAuxiliar repositorioAuxiliar;

}
