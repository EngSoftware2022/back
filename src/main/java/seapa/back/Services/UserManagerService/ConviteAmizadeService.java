package seapa.back.Services.UserManagerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ConviteAmizade;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static seapa.back.Entitys.UserManegerEntitys.UserEntitys.ConviteAmizade.FIND_ALL_CONVITES_PENDENTES_BY_SOLICITADO_ID;

@Service
@Transactional
public class ConviteAmizadeService {

    @PersistenceContext
    private EntityManager em;

    /*
    public List<ConviteAmizadeJson> findAllConvitesPendentesByUsuarioIdToJson(Long solicitanteId) {
        Query query = this.em.createNamedQuery(FIND_ALL_CONVITES_PENDENTES_BY_SOLICITANTE_ID);
        query.setParameter("solicitanteId", solicitanteId);

        List<ConviteAmizade> convitesAmizades = query.getResultList();
        List<ConviteAmizadeJson> convitesAmizadesJsons = new ArrayList<>();

        for(ConviteAmizade conviteAmizade : convitesAmizades) {
            ConviteAmizadeJson convite = new ConviteAmizadeJson();

            convite.setConviteid(conviteAmizade.getId());
            convite.setSolicitanteId(conviteAmizade.getSolicitante().getId());
            convite.setNomeSolicitante(conviteAmizade.getSolicitante().getUsuario().getNome());
            convite.setSobrenomeSolicitante(conviteAmizade.getSolicitante().getUsuario().getSobrenome());
            convite.setNomeUsuarioSolicitante(conviteAmizade.getSolicitante().getNomeUsuario());
            convite.setSolicitadoId(conviteAmizade.getSolicitado().getId());
            convite.setNomeSolicitado(conviteAmizade.getSolicitado().getUsuario().getNome());
            convite.setSobrenomeSolicitado(conviteAmizade.getSolicitado().getUsuario().getSobrenome());
            convite.setNomeUsuarioSolicitado(conviteAmizade.getSolicitado().getNomeUsuario());
            convite.setDataSolicitacao(conviteAmizade.getDataSolicitacao());
            convite.setDataConfirmacao(conviteAmizade.getDataConfirmacao());
            convite.setStatusConvite(conviteAmizade.getStatusConvite());

            convitesAmizadesJsons.add(convite);
        }

        return convitesAmizadesJsons;
    }
    */

    public List<ConviteAmizade> findAllConvitesPendentesByUsuarioId(Long solicitadoId) {
        Query query = this.em.createNamedQuery(FIND_ALL_CONVITES_PENDENTES_BY_SOLICITADO_ID);
        query.setParameter("solicitadoId", solicitadoId);

        return query.getResultList();
    }

}
