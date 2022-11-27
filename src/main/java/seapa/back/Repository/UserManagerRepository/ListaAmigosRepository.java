package seapa.back.Repository.UserManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seapa.back.Entitys.UserManegerEntitys.ListaAmigos;

@Repository
public interface ListaAmigosRepository extends JpaRepository<ListaAmigos, Long> {
}
