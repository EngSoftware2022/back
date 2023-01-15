package seapa.back.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.Aposta;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.Aposta.FIND_ALL_APOSTAS_BY_USUARIO_ID;

@Service
@Transactional
public class BetManagerService {

    @PersistenceContext
    private EntityManager em;

    public List<Aposta> findApostasByGrupo(Long grupoId) {
        Query query = this.em.createNamedQuery(FIND_ALL_APOSTAS_BY_USUARIO_ID);
        query.setParameter("grupoId", grupoId);

        return query.getResultList();
    }
}
