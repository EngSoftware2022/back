package seapa.back.Repository.UserManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import seapa.back.Entitys.UserManegerEntitys.ContaUsuario;

public interface ContaUsuarioRepository extends JpaRepository<ContaUsuario, Long> {
}
