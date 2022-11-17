package seapa.back.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seapa.back.Entitys.UserManegerEntitys.ListaAmigos;
import seapa.back.Entitys.UserManegerEntitys.Usuario;
import seapa.back.Models.UsuarioModel;
import seapa.back.Repository.UserManagerRepositoryMagno;

import java.util.List;
import java.util.Objects;

@Service
public class UserManagerService {

    @Autowired
    private UserManagerRepositoryMagno repository;
 /*
    public void cadastrarUsuario(UsuarioModel model) {//ok
        this.repository.salvaUsuario(deModelParaEntidade(model));
    }//ok

    public UsuarioModel buscarUsuarioPorNomeDeUsuario(String nomeDeUsuario) {//ok
       return deEntidadeParaModel(this.repository.achaUsuario(nomeDeUsuario));
    }

    public void pedirAmizade(String usuarioCorrente, String usuarioDestino) {//ok
        PedidosDeAmizade pedido = new PedidosDeAmizade(usuarioCorrente, usuarioDestino);
        this.repository.pedeAmizade(pedido);
    }

    public List<PedidosDeAmizade> listarSolicitacoesDeAmizade(String usuarioCorrente) {//ok
       return this.repository.acharTodasSolicitacoesAmizade(usuarioCorrente);
    }

    public void criaVinculoAmizade(String usuarioCorrente, String usuarioSolicitante, Boolean aceitou) {//ok
        if(aceitou) {
            salvarAmizadeNosUsuarios(usuarioCorrente,usuarioSolicitante);
        }

        this.repository.deletarPedido(new PedidosDeAmizade(usuarioCorrente,usuarioSolicitante));

    }

    private void salvarAmizadeNosUsuarios(String usuarioCorrente, String usuarioSolicitante) {
        Usuario usuarioCor = this.repository.achaUsuario(usuarioCorrente);
        Usuario usuarioSol = this.repository.achaUsuario(usuarioSolicitante);
        ListaAmigos doUserCorParaSol = new ListaAmigos();
        doUserCorParaSol.setUser(usuarioSol);
        ListaAmigos doUserSolParaCor = new ListaAmigos();
        doUserSolParaCor.setUser(usuarioCor);
        usuarioCor.getAmigos().add(doUserSolParaCor);
        usuarioSol.getAmigos().add(doUserCorParaSol);
        this.repository.salvaUsuario(usuarioCor);
        this.repository.salvaUsuario(usuarioSol);
    }

    public void atualizaUsuario(UsuarioModel model) {//ok
        Usuario usuario = this.repository.achaUsuario(model.getNomeDeUsuario());
        atualizarUsuario(usuario,model);
        this.repository.salvaUsuario(usuario);
    }

    private Usuario atualizarUsuario(Usuario usuario, UsuarioModel model) {
        usuario.setDataNascimento(model.getDataNascimento());
        usuario.setEmail(model.getEmail());
        usuario.setNome(model.getNome());
        usuario.setNomeDeUsuario(model.getNomeDeUsuario());
        usuario.setUltimoNome(model.getUltimoNome());
        usuario.setTelefone(model.getTelefone());
        usuario.setCpf(model.getCpf());
        usuario.setSenha(model.getSenha());
        return usuario;
    }
    public boolean validaUsuario(String usuario, String senha) {
        if( Objects.nonNull(this.repository.verificarUsuarioESenha(usuario, senha)) ) return true;

        return false;
    }

    public void deletarAmizade(String usuarioCorrente, String usuarioDestino) {
       // this.repository.deletarAmizade(usuarioCorrente,usuarioDestino);
    }

    public List<ListaAmigos> listarTodosAmigos(String usuarioCorrente) {
      return this.repository.acharTodosAmigos(usuarioCorrente);
    }


    private UsuarioModel deEntidadeParaModel(Usuario entidade){
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setDataNascimento(entidade.getDataNascimento());
        usuarioModel.setEmail(entidade.getEmail());
        usuarioModel.setNome(entidade.getNome());
        usuarioModel.setNomeDeUsuario(entidade.getNomeDeUsuario());
        usuarioModel.setUltimoNome(entidade.getUltimoNome());
        usuarioModel.setTelefone(entidade.getTelefone());
        return usuarioModel;
    }

    private Usuario deModelParaEntidade(UsuarioModel model){
        Usuario usuario = new Usuario();
        return atualizarUsuario(usuario,model);
    }
    */

}
