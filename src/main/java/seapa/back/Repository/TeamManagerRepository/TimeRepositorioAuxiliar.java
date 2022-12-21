package seapa.back.Repository.TeamManagerRepository;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.ConviteGrupo;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.Grupo;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.IntegrantesGrupo;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Repository.BaseRepository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Transactional
public class TimeRepositorioAuxiliar {

    @Resource
    private BaseRepository baseRepository;

    public Grupo findByGrupo(Long idGrupo) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(Grupo.class);
        criteria.add(Restrictions.eq("id", idGrupo));
        return (Grupo) criteria.uniqueResult();
    }

    public List<Grupo> findGruposOndeSouAdmin(Long idUsuario) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(Grupo.class);
        criteria.add(Restrictions.eq("moderadorId", idUsuario));
        return criteria.list();
    }


    public List<Grupo> findGruposOndeSouMembro(Long idUsuario) {
        StringBuilder hql = new StringBuilder()
                .append("SELECT ")
                .append(" g")
                .append(" FROM")
                .append(" Grupo g")
                .append(" Join g.integrantes i")
                .append(" WHERE")
                .append(" i.idUsuarioIntegrante =: idUsu");

        Query query = baseRepository.getSession().createQuery(hql.toString(),Grupo.class);
        query.setParameter("idUsu",idUsuario);
        return query.list();
    }

    public List<ConviteGrupo> findConvitesDeGrupo(Long idUsuario) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(ConviteGrupo.class);
        criteria.add(Restrictions.eq("idUsuarioConvidado", idUsuario));
        return criteria.list();
    }

    public ConviteGrupo findConvitesDeGrupo(Long idGrupo,Long idUsuario) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(ConviteGrupo.class);
        criteria.add(Restrictions.eq("idUsuarioConvidado", idUsuario));
        criteria.add(Restrictions.eq("grupo", idGrupo));
        return (ConviteGrupo) criteria.uniqueResult();
    }

    public IntegrantesGrupo findIntegranteByIdUsuario(Long idUsuario, Long idGrupo) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(IntegrantesGrupo.class);
        criteria.add(Restrictions.eq("idUsuarioIntegrante", idUsuario));
        criteria.add(Restrictions.eq("idGrupo",idGrupo));
        return (IntegrantesGrupo) criteria.uniqueResult();
    }

    public Grupo findConviteGrupoEspecifico(Long idGrupo) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(Grupo.class);
        criteria.add(Restrictions.eq("id", idGrupo));
        return (Grupo) criteria.uniqueResult();
    }

    public String findNomeUsuarioById(Long idUsuario) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(ContaUsuario.class);
        criteria.add(Restrictions.eq("id",idUsuario));
        criteria.setProjection(Projections.property("nomeUsuario"));
        return (String) criteria.uniqueResult();
    }
}
