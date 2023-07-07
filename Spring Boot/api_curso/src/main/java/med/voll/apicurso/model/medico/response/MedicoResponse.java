package med.voll.apicurso.model.medico.response;

import med.voll.apicurso.model.medico.Especialidade;
import med.voll.apicurso.model.medico.entity.Medico;

public record MedicoResponse(Long id,String nome, String email, String crm, Especialidade especialidade) {
    public MedicoResponse(Medico medico) {
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
