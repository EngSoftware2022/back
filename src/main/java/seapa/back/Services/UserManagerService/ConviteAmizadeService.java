package seapa.back.Services.UserManagerService;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import seapa.back.Entitys.UserManegerEntitys.ConviteAmizade;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static seapa.back.Entitys.UserManegerEntitys.ConviteAmizade.FIND_ALL_CONVITES_PENDENTES_BY_SOLICITANTE_ID;

@Component
@Transactional
public class ConviteAmizadeService {

    @PersistenceContext
    private EntityManager em;

    public List<ConviteAmizade> findAllConvitesPendentesByUsuarioId(Long solicitanteId) {
        Query query = this.em.createNamedQuery(FIND_ALL_CONVITES_PENDENTES_BY_SOLICITANTE_ID);
        query.setParameter("solicitanteId", solicitanteId);

        return query.getResultList();
    }
}
