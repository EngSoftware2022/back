package seapa.back.Repository.UserManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import seapa.back.Entitys.UserManegerEntitys.ListaAmigos;

public interface ListaAmigosRepository extends JpaRepository<ListaAmigos, Long> {
}
