package seapa.back.Repository.ApostasManagerRepositosy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seapa.back.Entitys.UserManegerEntitys.ApostasEntitys.ParticipanteAposta;

@Repository
public interface ParticipanteApostaRepository extends JpaRepository<ParticipanteAposta, Long> {
}
