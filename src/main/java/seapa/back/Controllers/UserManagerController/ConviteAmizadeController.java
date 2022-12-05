package seapa.back.Controllers.UserManagerController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ConviteAmizade;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ListaAmigos;
import seapa.back.Models.ConviteAmizadeDTO;
import seapa.back.Repository.UserManagerRepository.ConviteAmizadeRepository;
import seapa.back.Services.UserManagerService.ConviteAmizadeService;
import seapa.back.Settings.Mappers.ConviteAmizadeMapper;
import seapa.back.Utils.StatusConviteAmizadeEnum;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/conviteAmizade")
public class ConviteAmizadeController {

    @Autowired
    private ConviteAmizadeRepository conviteAmizadeRepository;

    @Autowired
    private ConviteAmizadeService conviteAmizadeService;

    @Autowired
    ContaUsuarioController contaUsuarioController;

    @Autowired
    private ListaAmigosController listaAmigosController;

    @Autowired
    ConviteAmizadeMapper conviteAmizadeMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ConviteAmizadeDTO> findConvitePendenteById(@PathVariable Long id) {
        ConviteAmizade conviteAmizade = conviteAmizadeRepository.findById(id).get();
        ConviteAmizadeDTO conviteAmizadeDTO = conviteAmizadeMapper.toConviteAmizadeDTO(conviteAmizade);

        return ResponseEntity.status(HttpStatus.OK).body(conviteAmizadeDTO);
    }

    @GetMapping(value = "/solicitanteId={solicitanteId}")
    public ResponseEntity<List<ConviteAmizadeDTO>> findAllConvitesPendentesByUsuarioId(@PathVariable Long solicitanteId) {
        List<ConviteAmizade> convitesDeAmizadePendentes = conviteAmizadeService.findAllConvitesPendentesByUsuarioId(solicitanteId);
        List<ConviteAmizadeDTO> convitesDeAmizadePendentesDTO = conviteAmizadeMapper.toConviteAmizadeDTOList(convitesDeAmizadePendentes);

        return ResponseEntity.status(HttpStatus.OK).body(convitesDeAmizadePendentesDTO);
    }

    @PostMapping(value = "/novoConvite/solicitanteId={solicitanteId}&solicitadoId={solicitadoId}")
    public ResponseEntity<ConviteAmizadeDTO> insertConviteAmizade(
            @PathVariable Long solicitanteId,
            @PathVariable Long solicitadoId) {

        ConviteAmizade novoConvite = new ConviteAmizade();

        novoConvite.setSolicitante(contaUsuarioController.findContaUsuarioById(solicitanteId));
        novoConvite.setSolicitado(contaUsuarioController.findContaUsuarioById(solicitadoId));
        novoConvite.setDataSolicitacao(new Date(System.currentTimeMillis()));
        novoConvite.setStatusConvite(StatusConviteAmizadeEnum.PENDENTE.toString());

        conviteAmizadeRepository.save(novoConvite);

        ConviteAmizadeDTO conviteAmizadeDTO = conviteAmizadeMapper.toConviteAmizadeDTO(novoConvite);

        return ResponseEntity.status(HttpStatus.CREATED).body(conviteAmizadeDTO);
    }

    @PostMapping(value = "/atualizaConviteAmizade/conviteId={conviteId}&statusConvite={statusConvite}")
    public ResponseEntity<String> atualizaStatusConviteAmizade(
            @PathVariable Long conviteId,
            @PathVariable String statusConvite) {

        ConviteAmizade conviteAmizade = conviteAmizadeRepository.findById(conviteId).get();

        if (conviteAmizade == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Convite de amizade expirado");
        }

        if (statusConvite.equals(StatusConviteAmizadeEnum.REJEITADO.toString())){
            this.deleteConviteAmizade(conviteId);

            return ResponseEntity.status(HttpStatus.OK).body("Convite de amizade rejeitado com SUCESSO");
        }
        else if (statusConvite.equals(StatusConviteAmizadeEnum.ACEITO.toString())){
            conviteAmizade.setDataConfirmacao(new Date(System.currentTimeMillis()));
            conviteAmizade.setStatusConvite(StatusConviteAmizadeEnum.ACEITO.toString());

            ListaAmigos addNovoAmigoNaLista = new ListaAmigos();

            addNovoAmigoNaLista.setUsuarioId(conviteAmizade.getSolicitante().getId());
            addNovoAmigoNaLista.setAmizadeId(conviteAmizade.getSolicitado().getId());

            listaAmigosController.insertNovoAmigo(addNovoAmigoNaLista);
        }

        conviteAmizadeRepository.save(conviteAmizade);

        return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body("Convite de amizade aceito com SUCESSO");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteConviteAmizade(@PathVariable Long id) {
        if (this.findConvitePendenteById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        conviteAmizadeRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
