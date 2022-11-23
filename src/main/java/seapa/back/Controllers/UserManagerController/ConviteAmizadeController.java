package seapa.back.Controllers.UserManagerController;


import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.UserManegerEntitys.ConviteAmizade;
import seapa.back.Entitys.UserManegerEntitys.ListaAmigos;
import seapa.back.Models.ConviteAmizadeJson;
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

    public static final String CONVITE_AMIZADE_ID = "CONVITE_AMIZADE_ID";
    public static final String SOLICITANTE_ID = "SOLICITANTE_ID";
    public static final String SOLICITADO_ID = "SOLICITADO_ID";
    public static final String DATA_SOLICITACAO = "DATA_SOLICITACAO";
    public static final String DATA_CONFIRMACAO = "DATA_CONFIRMACAO";
    public static final String STATUS_CONVITE = "STATUS_CONVITE";

    SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @GetMapping(value = "/{id}")
    public ConviteAmizade findConvitePendenteById(@PathVariable Long id) {
        return conviteAmizadeRepository.findById(id).get();
    }

    /*
    precisa retornar:
        - conviteId,
        - solicitanteId,
        - nomeSolicitante
        - sobrenomeSolicitante,
        - nomeUsuarioSolicitante

    @GetMapping(value = "/solicitante={id}")
    public JSONArray findAllConvitesPendentesByUsuarioId(@PathVariable Long id) throws Exception {
        List<ConviteAmizadeJson> allConvitesDeAmizades = conviteAmizadeService.findAllConvitesPendentesByUsuarioIdToJson(id);

        JSONArray retorno = new JSONArray();

        for(ConviteAmizadeJson conviteDeAmizade : allConvitesDeAmizades) {
            JSONObject json = new JSONObject();

            json.put(CONVITE_AMIZADE_ID, conviteDeAmizade.id);
            json.put(SOLICITANTE_ID, conviteDeAmizade.solicitanteId);
            json.put(SOLICITADO_ID, conviteDeAmizade.solicitadoId);
            json.put(DATA_SOLICITACAO, conviteDeAmizade.dataSolicitacao);
            json.put(DATA_CONFIRMACAO, conviteDeAmizade.dataConfirmacao);
            json.put(STATUS_CONVITE, conviteDeAmizade.statusConvite);

            retorno.put(json);
        }

        return retorno;
    }
    */

    @GetMapping(value = "/solicitante={id}")
    public List<ConviteAmizadeJson> findAllConvitesPendentesByUsuarioId(@PathVariable Long id) {
        return conviteAmizadeService.findAllConvitesPendentesByUsuarioIdToJson(id);
    }

    @ResponseBody
    @RequestMapping(value = "/novoConvite/solicitanteId={solicitanteId}&solicitadoId={solicitadoId}",
            method = { RequestMethod.POST, RequestMethod.PUT })
    public ConviteAmizade insertConviteAmizade(
            @PathVariable Long solicitanteId,
            @PathVariable Long solicitadoId) {

        ConviteAmizade novoConvite = new ConviteAmizade();

        novoConvite.setSolicitante(contaUsuarioController.findContaUsuarioById(solicitanteId));
        novoConvite.setSolicitado(contaUsuarioController.findContaUsuarioById(solicitadoId));

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
