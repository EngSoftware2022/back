package seapa.back.Services.UserManagerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.CarteiraEntitys.MovimentacaoMonetaria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static seapa.back.Entitys.UserManegerEntitys.UserEntitys.CarteiraEntitys.MovimentacaoMonetaria.FIND_ULTIMAS_CINCO_MOVIMENTACOES_MONETARIAS_USUARIO_BY_USUARIO_ID;


@Service
@Transactional
public class MovimentacaoMonetariaService {
    @PersistenceContext
    private EntityManager em;

    public List<MovimentacaoMonetaria> findUltimasCincoMovimentacoesMonetariasDoUsuario(Long usuarioId) {
        Query query = this.em.createNamedQuery(FIND_ULTIMAS_CINCO_MOVIMENTACOES_MONETARIAS_USUARIO_BY_USUARIO_ID);

        query.setParameter("usuarioId", usuarioId);
        query.setMaxResults(5);

        return query.getResultList();
    }

}
