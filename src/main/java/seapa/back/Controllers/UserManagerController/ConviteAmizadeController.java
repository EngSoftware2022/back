package seapa.back.Controllers.UserManagerController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.UserManegerEntitys.ConviteAmizade;
import seapa.back.Entitys.UserManegerEntitys.ListaAmigos;
import seapa.back.Repository.UserManagerRepository.ConviteAmizadeRepository;
import seapa.back.Services.UserManagerService.ConviteAmizadeService;
import seapa.back.Utils.StatusConviteAmizade;

import java.text.SimpleDateFormat;
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

    SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @GetMapping(value = "/{id}")
    public ConviteAmizade findConvitePendenteById(@PathVariable Long id) {
        return conviteAmizadeRepository.findById(id).get();
    }

    @GetMapping(value = "/solicitante={id}")
    public List<ConviteAmizade> findAllConvitesPendentesByUsuarioId(@PathVariable Long id) {
        return conviteAmizadeService.findAllConvitesPendentesByUsuarioId(id);
    }

    @ResponseBody
    @RequestMapping(value = "/novoConvite", method = { RequestMethod.POST, RequestMethod.PUT })
    public ConviteAmizade insertConviteAmizade(
            @RequestParam(value = "solicitanteId", required = false) Long solicitanteId,
            @RequestParam(value = "solicitadoId", required = false) Long solicitadoId) {

        ConviteAmizade novoConvite = new ConviteAmizade();

        novoConvite.setSolicitante(contaUsuarioController.findContaUsuarioById(1L));
        novoConvite.setSolicitado(contaUsuarioController.findContaUsuarioById(3L));

        novoConvite.setDataSolicitacao(new Date(System.currentTimeMillis()));
        novoConvite.setStatusConvite(StatusConviteAmizade.PENDENTE.toString());

        return conviteAmizadeRepository.save(novoConvite);
    }

    @ResponseBody
    @RequestMapping(value = "/atualizaConviteAmizade", method = { RequestMethod.POST, RequestMethod.PUT })
    public HttpStatus atualizaStatusConviteAmizade(
            @RequestParam(value = "conviteId", required = false) Long conviteId,
            @RequestParam(value = "statusConvite", required = false) String statusConvite) {

        conviteId = 1L;
        statusConvite = StatusConviteAmizade.REJEITADO.toString();

        ConviteAmizade conviteAmizade = this.findConvitePendenteById(conviteId);

        if (conviteAmizade == null){
            return HttpStatus.NOT_FOUND;
        }

        if (statusConvite.equals(StatusConviteAmizade.REJEITADO.toString())){
            this.deleteConviteAmizade(conviteId);

            return HttpStatus.OK;
        }
        else if (statusConvite.equals(StatusConviteAmizade.ACEITO.toString())){
            conviteAmizade.setDataConfirmacao(new Date(System.currentTimeMillis()));
            conviteAmizade.setStatusConvite(StatusConviteAmizade.ACEITO.toString());

            ListaAmigos addNovoAmigoNaLista = new ListaAmigos();

            addNovoAmigoNaLista.setUsuarioId(conviteAmizade.getSolicitante().getId());
            addNovoAmigoNaLista.setAmizadeId(conviteAmizade.getSolicitado().getId());

            listaAmigosController.insertNovoAmigo(addNovoAmigoNaLista);
        }

        conviteAmizadeRepository.save(conviteAmizade);


        return HttpStatus.OK;
    }

    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteConviteAmizade(@PathVariable Long id) {
        if (this.findConvitePendenteById(id) == null) {
            return HttpStatus.NOT_FOUND;
        }

        conviteAmizadeRepository.deleteById(id);

        return HttpStatus.OK;
    }
}
