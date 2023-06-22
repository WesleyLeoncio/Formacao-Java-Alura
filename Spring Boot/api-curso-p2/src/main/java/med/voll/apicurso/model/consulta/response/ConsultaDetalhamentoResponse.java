package med.voll.apicurso.model.consulta.response;

import med.voll.apicurso.model.consulta.entity.Consulta;

import java.time.LocalDateTime;

public record ConsultaDetalhamentoResponse(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public ConsultaDetalhamentoResponse(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
