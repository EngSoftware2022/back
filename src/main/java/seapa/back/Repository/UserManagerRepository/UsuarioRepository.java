package seapa.back.Repository.UserManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import seapa.back.Entitys.UserManegerEntitys.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
