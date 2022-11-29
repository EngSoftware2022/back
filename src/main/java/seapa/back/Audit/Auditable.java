package seapa.back.Audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class Auditable<U> {

    /*
    @CreatedBy
    @Column(name = "criado_por")
    protected U criadoPor;
    */

    @CreatedDate
    @Column(name = "data_criacao", updatable = false)
    protected Date dataCriacao;

    /*
    @LastModifiedBy
    @Column(name = "ultimo_modificador")
    protected U ultimoModificador;
    */

    @LastModifiedDate
    @Column(name = "data_ultima_modificacao")
    protected Date dataUltimaModificacao;
}
