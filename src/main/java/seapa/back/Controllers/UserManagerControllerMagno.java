package seapa.back.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seapa.back.Services.UserManagerServiceMagno;

@RestController
@RequestMapping("/usermanager")
public class UserManagerControllerMagno {

    @Autowired
    private UserManagerServiceMagno service;
/*
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
    public ResponseEntity<List<ListaAmigos>> listaAmigos(@RequestParam String usuarioCorrente){
        return new ResponseEntity<>(this.service.listarTodosAmigos(usuarioCorrente), OK);
    }

    @DeleteMapping(value = "/buscaamigos/deletar")
    public ResponseEntity deletaAmigos(@RequestParam String usuarioCorrente, @RequestParam String usuarioDestino) {
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
*/
}
