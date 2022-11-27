package seapa.back.Repository.UserManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seapa.back.Entitys.UserManegerEntitys.ConviteAmizade;

@Repository
public interface ConviteAmizadeRepository extends JpaRepository<ConviteAmizade, Long> {
}
