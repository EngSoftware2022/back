package seapa.back.Services.UserManagerService;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seapa.back.Entitys.UserManegerEntitys.ConviteAmizade;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static seapa.back.Entitys.UserManegerEntitys.ContaUsuario.FIND_CONTA_USUARIO_ID_BY_USUARIO_E_SENHA;

@Service
@Transactional
public class ContaUsuarioService {

    @PersistenceContext
    private EntityManager em;


    public Long findContaUsuarioIdByNomeUsuarioAndSenha(String nomeUsuario, String senha) {
        Query query = this.em.createNamedQuery(FIND_CONTA_USUARIO_ID_BY_USUARIO_E_SENHA);
        query.setParameter("nomeUsuario", nomeUsuario);
        query.setParameter("senha", senha);

        return (Long) query.getSingleResult();
    }
}
