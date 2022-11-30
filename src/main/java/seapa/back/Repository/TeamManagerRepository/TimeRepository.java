package seapa.back.Repository.TeamManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.Grupo;

@Repository
public interface TimeRepository extends JpaRepository<Grupo, Long> {
}
