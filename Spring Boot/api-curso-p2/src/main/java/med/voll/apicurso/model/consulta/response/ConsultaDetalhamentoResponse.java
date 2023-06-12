package med.voll.apicurso.model.consulta.response;

import java.time.LocalDateTime;

public record ConsultaDetalhamentoResponse(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
}
