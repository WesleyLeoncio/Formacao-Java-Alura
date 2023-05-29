package med.voll.apicurso.model.request.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.apicurso.model.request.endereco.EnderecoRequest;

public record MedicoEditRequest(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoRequest endereco
) {
}
