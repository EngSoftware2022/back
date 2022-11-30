package seapa.back.Repository.UserManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.Extrato;

public interface ExtratoRepository extends JpaRepository<Extrato, Long> {
}
