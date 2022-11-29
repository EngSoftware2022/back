package seapa.back.Repository.TeamManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.ConviteGrupo;

@Repository
public interface ConviteTimeRepository extends JpaRepository<ConviteGrupo, Long> {
}
