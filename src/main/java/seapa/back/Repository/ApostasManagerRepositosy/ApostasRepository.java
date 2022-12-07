package seapa.back.Repository.ApostasManagerRepositosy;

import org.springframework.data.jpa.repository.JpaRepository;
import seapa.back.Entitys.UserManegerEntitys.ApostasEntitys.ApostaComum;

public interface ApostasRepository extends JpaRepository<ApostaComum, Long> {
}
