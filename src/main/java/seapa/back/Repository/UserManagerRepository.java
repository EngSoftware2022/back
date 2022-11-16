package seapa.back.Repository;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import seapa.back.Entitys.Amigos;
import seapa.back.Entitys.PedidosDeAmizade;
import seapa.back.Entitys.Usuarios;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Transactional
public class UserManagerRepository {

    @Resource
    private BaseRepository baseRepository;

    public void salvaUsuario(Usuarios usuario) {
        this.baseRepository.save(usuario);
    }

    public Usuarios achaUsuario(String nomeDeUsuario) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(Usuarios.class);
        criteria.add(Restrictions.eq("nomeDeUsuario", nomeDeUsuario));
        return (Usuarios) criteria.uniqueResult();
    }

    public void pedeAmizade(PedidosDeAmizade pedido) {
        this.baseRepository.save(pedido);
    }

    public void criaAmizade(Usuarios usuario) {
        this.baseRepository.save(usuario);
    }

    public void deletarPedido(PedidosDeAmizade pedido) {
        this.baseRepository.delete(pedido);
    }

    public void deletarAmizade(Amigos amizade) {
        this.baseRepository.delete(amizade);
    }

    public Usuarios verificarUsuarioESenha(String usuario, String senha) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(Usuarios.class);
        criteria.add(Restrictions.eq("nomeDeUsuario", usuario));
        criteria.add(Restrictions.eq("senha",senha));
        return (Usuarios) criteria.uniqueResult();
    }

    public List<PedidosDeAmizade> acharTodasSolicitacoesAmizade(String usuarioCorrente) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(PedidosDeAmizade.class);
        criteria.add(Restrictions.eq("usuarioDestino",usuarioCorrente));
        return criteria.list();
    }

    public List<Amigos> acharTodosAmigos(String usuarioCorrente) {
        StringBuilder hql = new StringBuilder();
        hql.append(" FROM Amigos join Usuarios as usu");
        hql.append(" WHERE usu.nomeDeUsuario =:usuarioCorrente");
        Query query = baseRepository.getSession().createQuery(hql.toString());
        query.setParameter("usuarioCorrente", usuarioCorrente);
        query.setResultTransformer(Transformers.aliasToBean(Amigos.class));
        return query.list();
    }
}
