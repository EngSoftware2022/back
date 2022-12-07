package seapa.back.Controllers.UserManagerController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ConviteAmizade;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ListaAmigos;
import seapa.back.Models.ConviteAmizadeDTO;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Repository.UserManagerRepository.ConviteAmizadeRepository;
import seapa.back.Services.UserManagerService.ConviteAmizadeService;
import seapa.back.Settings.Mappers.ConviteAmizadeMapper;
import seapa.back.Utils.StatusConviteAmizade;

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
    private ContaUsuarioRepository contaUsuarioRepository;

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

    @GetMapping(value = "solicitadoId={solicitadoId}")
    public ResponseEntity<List<ConviteAmizadeDTO>> findAllConvitesPendentesByUsuarioId(@PathVariable Long solicitadoId) {
        List<ConviteAmizade> convitesDeAmizadePendentes = conviteAmizadeService.findAllConvitesPendentesByUsuarioId(solicitadoId);
        List<ConviteAmizadeDTO> convitesDeAmizadePendentesDTO = conviteAmizadeMapper.toConviteAmizadeDTOList(convitesDeAmizadePendentes);

        return ResponseEntity.status(HttpStatus.OK).body(convitesDeAmizadePendentesDTO);
    }

    @PostMapping(value = "/novoConvite")
    public ResponseEntity<ConviteAmizadeDTO> insertConviteAmizade(
            @RequestParam Long solicitanteId,
            @RequestParam Long solicitadoId) {

        ConviteAmizade novoConvite = new ConviteAmizade();

        novoConvite.setSolicitante(contaUsuarioRepository.findById(solicitanteId).get());
        novoConvite.setSolicitado(contaUsuarioRepository.findById(solicitadoId).get());
        novoConvite.setDataSolicitacao(new Date(System.currentTimeMillis()));
        novoConvite.setStatusConvite(StatusConviteAmizade.PENDENTE.toString());

        conviteAmizadeRepository.save(novoConvite);

        ConviteAmizadeDTO conviteAmizadeDTO = conviteAmizadeMapper.toConviteAmizadeDTO(novoConvite);

        return ResponseEntity.status(HttpStatus.CREATED).body(conviteAmizadeDTO);
    }

    @PostMapping(value = "/atualizaConviteAmizade/")
    public ResponseEntity<String> atualizaStatusConviteAmizade(
            @RequestParam Long conviteId,
            @RequestParam String statusConvite) {

        ConviteAmizade conviteAmizade = conviteAmizadeRepository.findById(conviteId).get();

        if (conviteAmizade == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Convite de amizade expirado");
        }

        if (statusConvite.equals(StatusConviteAmizade.REJEITADO.toString())){
            this.deleteConviteAmizade(conviteId);

            return ResponseEntity.status(HttpStatus.OK).body("Convite de amizade rejeitado com SUCESSO");
        }
        else if (statusConvite.equals(StatusConviteAmizade.ACEITO.toString())){
            conviteAmizade.setDataConfirmacao(new Date(System.currentTimeMillis()));
            conviteAmizade.setStatusConvite(StatusConviteAmizade.ACEITO.toString());

            ListaAmigos addNovoAmigoNaListaDoSolicitante = new ListaAmigos();
            ListaAmigos addNovoAmigoNaListaDoSolicitado = new ListaAmigos();

            addNovoAmigoNaListaDoSolicitante.setUsuarioId(conviteAmizade.getSolicitante().getId());
            addNovoAmigoNaListaDoSolicitante.setAmizadeId(conviteAmizade.getSolicitado().getId());

            addNovoAmigoNaListaDoSolicitado.setUsuarioId(conviteAmizade.getSolicitado().getId());
            addNovoAmigoNaListaDoSolicitado.setAmizadeId(conviteAmizade.getSolicitante().getId());

            listaAmigosController.insertNovoAmigo(addNovoAmigoNaListaDoSolicitante);
            listaAmigosController.insertNovoAmigo(addNovoAmigoNaListaDoSolicitado);
        }

        conviteAmizadeRepository.save(conviteAmizade);

        return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body("Convite de amizade aceito com SUCESSO");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteConviteAmizade(@RequestParam Long id) {
        if (this.findConvitePendenteById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Convite informado não existe");
        }

        conviteAmizadeRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("O convite de amizade foi deletado com SUCESSO.");
    }
}
