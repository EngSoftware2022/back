package seapa.back.Repository.ApostasManagerRepositosy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.Aposta;

@Repository
public interface ApostasRepository extends JpaRepository<Aposta, Long> {
}
