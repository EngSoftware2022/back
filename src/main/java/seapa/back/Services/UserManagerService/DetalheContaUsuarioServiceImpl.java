package seapa.back.Services.UserManagerService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import seapa.back.Data.DetalheContaUsuarioData;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;

import java.util.Optional;

@Component
public class DetalheContaUsuarioServiceImpl implements UserDetailsService {

    private final ContaUsuarioRepository contaUsuarioRepository;

    public DetalheContaUsuarioServiceImpl(ContaUsuarioRepository repository) {
        this.contaUsuarioRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        Optional<ContaUsuario> contaUsuario = contaUsuarioRepository.findByNomeUsuario(nomeUsuario);

        if(contaUsuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário '" + nomeUsuario + "' não encontrado.");
        }

        return new DetalheContaUsuarioData(contaUsuario);
    }
}
