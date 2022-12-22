package seapa.back.Repository.UserManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.CarteiraEntitys.MovimentacaoMonetaria;

import java.util.List;

public interface MovimentacaoMonetariaRepository extends JpaRepository<MovimentacaoMonetaria, Long> {

    List<MovimentacaoMonetaria> findAllByBancaUsuarioId(Long id);
}
