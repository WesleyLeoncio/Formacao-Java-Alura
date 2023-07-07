package med.voll.apicurso.model.consulta.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.apicurso.model.medico.Especialidade;

import java.time.LocalDateTime;

public record ConsultaAgendamentoRequest(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade) {
}