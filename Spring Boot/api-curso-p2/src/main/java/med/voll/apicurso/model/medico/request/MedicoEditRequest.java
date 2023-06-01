package med.voll.apicurso.model.medico.request;

import jakarta.validation.constraints.NotNull;
import med.voll.apicurso.model.endereco.request.EnderecoRequest;

public record MedicoEditRequest(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoRequest endereco
) {
}
