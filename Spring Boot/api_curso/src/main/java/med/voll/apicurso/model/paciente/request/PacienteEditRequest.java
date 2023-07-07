package med.voll.apicurso.model.paciente.request;

import jakarta.validation.constraints.NotNull;
import med.voll.apicurso.model.endereco.request.EnderecoRequest;

public record PacienteEditRequest(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoRequest endereco
) {
}
