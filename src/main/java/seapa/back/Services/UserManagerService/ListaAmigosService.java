package seapa.back.Services.UserManagerService;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seapa.back.Entitys.UserManegerEntitys.ListaAmigos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

import static seapa.back.Entitys.UserManegerEntitys.ListaAmigos.FIND_ALL_AMIGOS_BY_USUARIO_ID;
import static seapa.back.Entitys.UserManegerEntitys.ListaAmigos.FIND_UNIQUE_AMIGO_BY_USUARIO_ID_AND_AMIZADE_ID;

@Service
@Transactional
public class ListaAmigosService {

    @PersistenceContext
    private EntityManager em;

    public List<ListaAmigos> findAllAmigosByUsuarioId(Long usuarioId) {
        Query query = this.em.createNamedQuery(FIND_ALL_AMIGOS_BY_USUARIO_ID);
        query.setParameter("usuarioId", usuarioId);

        return query.getResultList();
    }

    public ListaAmigos findUniqueAmigoByUsuawrioIdAndAmizadeId(Long usuarioId, Long amizadeId) {
        Query query = this.em.createNamedQuery(FIND_UNIQUE_AMIGO_BY_USUARIO_ID_AND_AMIZADE_ID);
        query.setParameter("usuarioId", usuarioId);
        query.setParameter("amizadeId", amizadeId);

        return (ListaAmigos) query.getSingleResult();
    }
}
