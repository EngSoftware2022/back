package seapa.back.Controllers.UserManagerController;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.Extrato;
import seapa.back.Models.AtualizaExtratoModel;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Repository.UserManagerRepository.ExtratoRepository;

import java.util.Optional;

@RestController
@RequestMapping(value = "/extrato")
public class ExtratoController {

    @Autowired
    private ContaUsuarioRepository contaUsuarioRepository;

    @Autowired
    private ExtratoRepository repository;

    @GetMapping
    public Extrato getSaldo(@RequestParam Long UsuarioId){
        ContaUsuario usuario = contaUsuarioRepository.findById(UsuarioId).get();
        Extrato extrato = usuario.getExtrato();
        return extrato;
    }

    @PostMapping
    public HttpStatus atualizaSaldo(@RequestBody AtualizaExtratoModel extratoModel){
        ContaUsuario usuario = contaUsuarioRepository.findById(extratoModel.getUsuarioId()).get();
        Extrato extratoAtual = Optional.ofNullable(usuario.getExtrato()).orElse(new Extrato());
        extratoAtual.atualizaExtrato(extratoModel.getNovoSaldo());
        usuario.setExtrato(extratoAtual);
        contaUsuarioRepository.save(usuario);
        return HttpStatus.OK;
    }
}
