package seapa.back.Repository.UserManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seapa.back.Entitys.UserManegerEntitys.ContaUsuario;

@Repository
public interface ContaUsuarioRepository extends JpaRepository<ContaUsuario, Long> {
}
