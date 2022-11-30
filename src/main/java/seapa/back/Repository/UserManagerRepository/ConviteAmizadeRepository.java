package seapa.back.Repository.UserManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ConviteAmizade;

@Repository
public interface ConviteAmizadeRepository extends JpaRepository<ConviteAmizade, Long> {
}
