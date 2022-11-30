package seapa.back.Repository.TeamManagerRepository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.ConviteGrupo;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.Grupo;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.IntegrantesGrupo;
import seapa.back.Repository.BaseRepository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Transactional
public class TimeRepositorioAuxiliar {

    @Resource
    private BaseRepository baseRepository;

    public List<IntegrantesGrupo> findByGrupo(Long idGrupo) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(IntegrantesGrupo.class);
        criteria.add(Restrictions.eq("grupo", idGrupo));
        return criteria.list();
    }

    public List<Grupo> findGruposOndeSouAdmin(Long idUsuario) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(Grupo.class);
        criteria.add(Restrictions.eq("moderadorId", idUsuario));
        return criteria.list();
    }


    public List<Grupo> findGruposOndeSouMembro(Long idUsuario) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(IntegrantesGrupo.class);
        criteria.add(Restrictions.eq("idUsuarioIntegrante", idUsuario));
        return criteria.list();
    }

    public List<ConviteGrupo> findConvitesDeGrupo(Long idUsuario) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(ConviteGrupo.class);
        criteria.add(Restrictions.eq("idUsuarioConvidado", idUsuario));
        return criteria.list();
    }

    public IntegrantesGrupo findIntegranteByIdUsuario(Long idUsuario, Long idGrupo) {
        Criteria criteria = this.baseRepository.getSession().createCriteria(IntegrantesGrupo.class);
        criteria.add(Restrictions.eq("idUsuarioIntegrante", idUsuario));
        criteria.add(Restrictions.eq("idGrupo",idGrupo));
        return (IntegrantesGrupo) criteria.uniqueResult();
    }
}
