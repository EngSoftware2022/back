package seapa.back.Controllers.UserManagerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.Extrato;
import seapa.back.Models.AtualizaExtratoModel;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Repository.UserManagerRepository.ExtratoRepository;

@RestController
@RequestMapping(value = "/extrato")
public class ExtratoController {

    @Autowired
    private ContaUsuarioRepository contaUsuarioRepository;

    @Autowired
    private ExtratoRepository repository;

    @GetMapping
    public Extrato getSaldo(Long UsuarioId){
        ContaUsuario usuario = contaUsuarioRepository.findById(UsuarioId).get();
        Extrato extrato = repository.findById(usuario.getExtrato().getId()).get();
        return extrato;
    }

    @PostMapping
    public HttpStatus atualizaSaldo(AtualizaExtratoModel extratoModel){
        ContaUsuario usuario = contaUsuarioRepository.findById(extratoModel.getUsuarioId()).get();
        Extrato extratoAtual = repository.findById(usuario.getExtrato().getId()).get();
        extratoAtual.atualizaExtrato(extratoModel.getNovoSaldo());
        repository.save(extratoAtual);
        return HttpStatus.OK;
    }
}
