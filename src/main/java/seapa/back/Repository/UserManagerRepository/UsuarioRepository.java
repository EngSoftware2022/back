package seapa.back.Repository.UserManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seapa.back.Entitys.UserManegerEntitys.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
