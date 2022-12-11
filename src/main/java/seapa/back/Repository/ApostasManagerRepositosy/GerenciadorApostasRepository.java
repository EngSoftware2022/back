package seapa.back.Repository.ApostasManagerRepositosy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seapa.back.Entitys.UserManegerEntitys.ApostasEntitys.GerenciadorApostas;

@Repository
public interface GerenciadorApostasRepository extends JpaRepository<GerenciadorApostas, Long> {
}
