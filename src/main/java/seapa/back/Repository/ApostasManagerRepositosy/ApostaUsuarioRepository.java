package seapa.back.Repository.ApostasManagerRepositosy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seapa.back.Entitys.BetManagerEntitys.ApostasUsuarioEntitys.ApostaDoUsuario;

@Repository
public interface ApostaUsuarioRepository extends JpaRepository<ApostaDoUsuario, Long> {
}
