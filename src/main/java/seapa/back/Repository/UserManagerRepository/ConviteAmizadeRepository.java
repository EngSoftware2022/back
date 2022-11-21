package seapa.back.Repository.UserManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import seapa.back.Entitys.UserManegerEntitys.ContaUsuario;
import seapa.back.Entitys.UserManegerEntitys.ConviteAmizade;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


public interface ConviteAmizadeRepository extends JpaRepository<ConviteAmizade, Long> {
}
