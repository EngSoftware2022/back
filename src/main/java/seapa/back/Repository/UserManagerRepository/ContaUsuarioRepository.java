package seapa.back.Repository.UserManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.Usuario;

import java.util.Optional;

@Repository
public interface ContaUsuarioRepository extends JpaRepository<ContaUsuario, Long> {

    public Optional<ContaUsuario> findByNomeUsuario(String nomeUsuario);
}
