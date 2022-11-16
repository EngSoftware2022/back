package seapa.back.Controllers;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seapa.back.Entitys.Amigos;
import seapa.back.Entitys.PedidosDeAmizade;
import seapa.back.Models.ConfirmacaoAmizade;
import seapa.back.Models.Login;
import seapa.back.Models.SolicitacaoAmizade;
import seapa.back.Models.UsuarioModel;
import seapa.back.Services.UserManagerService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/usermanager")
public class UserManagerController {

    @Autowired
    private UserManagerService service;

    @PostMapping(value = "/login")//funcionando
    public ResponseEntity conferirLogin(@RequestBody Login login){
        if(this.service.validaUsuario(login.getNomeDeUsuario(), login.getSenha())) {
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/cadastro")//funcionando
    public ResponseEntity cadastroUsuario (@RequestBody UsuarioModel model){
        this.service.cadastrarUsuario(model);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/cadastro")//funcionando
    public ResponseEntity atualizaDadosUsuario(@RequestBody UsuarioModel model){
        this.service.atualizaUsuario(model);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/busca")//funcionando
    public ResponseEntity<UsuarioModel> buscaUsuario (@ApiParam(value = "usuario da requisacao", required = true) @RequestParam String usuario){
       return new ResponseEntity<>(this.service.buscarUsuarioPorNomeDeUsuario(usuario), OK);
    }

    @PostMapping(value = "/solicitaamizade")//funcionando
    public ResponseEntity pedidoAmizade(@RequestBody SolicitacaoAmizade solicitacao){
        this.service.pedirAmizade(solicitacao.getUsuarioCorrente(), solicitacao.getUsuarioDestino());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/buscaamigos")
    public ResponseEntity<List<Amigos>> listaAmigos(@RequestParam String usuarioCorrente){
        return new ResponseEntity<>(this.service.listarTodosAmigos(usuarioCorrente), OK);
    }

    @DeleteMapping(value = "/buscaamigos/deletar")
    public ResponseEntity deletaAmigos(@RequestParam String usuarioCorrente, @RequestParam String usuarioDestino){
        this.service.deletarAmizade(usuarioCorrente, usuarioDestino);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value ="/buscaamigos/solicitacoes")//funcionando
    public ResponseEntity<List<PedidosDeAmizade>> listaSolicitacaoDeAmizade(@RequestParam String usuarioCorrente){
        return new ResponseEntity<>(this.service.listarSolicitacoesDeAmizade(usuarioCorrente), OK);
    }

    @PostMapping(value = "/buscaamigos/responder")
    public ResponseEntity amizadeAceita(@RequestBody ConfirmacaoAmizade confirmacao){
        this.service.criaVinculoAmizade(confirmacao.getUsuarioCorrente(),confirmacao.getUsuarioSolicitante(),confirmacao.getAceitou());
        return ResponseEntity.ok().build();
    }

}
