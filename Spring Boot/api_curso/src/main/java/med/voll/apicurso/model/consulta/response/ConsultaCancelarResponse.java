package med.voll.apicurso.model.consulta.response;

import jakarta.validation.constraints.NotNull;
import med.voll.apicurso.model.consulta.MotivoCancelamento;
import med.voll.apicurso.model.consulta.entity.Consulta;

public record ConsultaCancelarResponse(Long idConsulta,MotivoCancelamento cancelamento) {
    public ConsultaCancelarResponse(Consulta consulta){
        this(consulta.getId(), consulta.getMotivoCancelamento());
    }
}
