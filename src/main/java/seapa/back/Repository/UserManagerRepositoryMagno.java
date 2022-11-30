package seapa.back.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserManagerRepositoryMagno {
    /*
        @Resource
        private BaseRepository baseRepository;

        public void salvaUsuario(Usuario usuario) {
            this.baseRepository.save(usuario);
        }

        public Usuario achaUsuario(String nomeDeUsuario) {
            Criteria criteria = this.baseRepository.getSession().createCriteria(Usuario.class);
            criteria.add(Restrictions.eq("nomeDeUsuario", nomeDeUsuario));
            return (Usuario) criteria.uniqueResult();
        }

        public void pedeAmizade(PedidosDeAmizade pedido) {
            this.baseRepository.save(pedido);
        }

        public void criaAmizade(Usuario usuario) {
            this.baseRepository.save(usuario);
        }

        public void deletarPedido(PedidosDeAmizade pedido) {
            this.baseRepository.delete(pedido);
        }

        public void deletarAmizade(ListaAmigos amizade) {
            this.baseRepository.delete(amizade);
        }

        public Usuario verificarUsuarioESenha(String usuario, String senha) {
            Criteria criteria = this.baseRepository.getSession().createCriteria(Usuario.class);
            criteria.add(Restrictions.eq("nomeDeUsuario", usuario));
            criteria.add(Restrictions.eq("senha",senha));
            return (Usuario) criteria.uniqueResult();
        }

        public List<PedidosDeAmizade> acharTodasSolicitacoesAmizade(String usuarioCorrente) {
            Criteria criteria = this.baseRepository.getSession().createCriteria(PedidosDeAmizade.class);
            criteria.add(Restrictions.eq("usuarioDestino",usuarioCorrente));
            return criteria.list();
        }

    public List<ListaAmigos> acharTodosAmigos(String usuarioCorrente) {
        StringBuilder hql = new StringBuilder();
        hql.append(" FROM Amigos join Usuarios as usu");
        hql.append(" WHERE usu.nomeDeUsuario =:usuarioCorrente");
        Query query = baseRepository.getSession().createQuery(hql.toString());
        query.setParameter("usuarioCorrente", usuarioCorrente);
        query.setResultTransformer(Transformers.aliasToBean(ListaAmigos.class));
        return query.list();
    }
    */

}
