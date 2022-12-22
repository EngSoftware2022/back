package seapa.back.Repository.UserManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.CarteiraEntitys.Banca;

import java.util.Optional;

@Repository
public interface BancaRepository extends JpaRepository<Banca, Long> {

    Optional<Banca> findByUsuarioId(Long id);
}
