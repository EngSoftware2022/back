package seapa.back.Entitys.UserManegerEntitys.ClassId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ParticipanteApostaId implements Serializable {

    private Long aposta;
    private Long participanteAposta;
}
