package med.voll.apicurso.model.consulta.request;

import jakarta.validation.constraints.NotNull;
import med.voll.apicurso.model.consulta.MotivoCancelamento;

public record ConsultaCancelarRequest(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamento cancelamento
) {
}
