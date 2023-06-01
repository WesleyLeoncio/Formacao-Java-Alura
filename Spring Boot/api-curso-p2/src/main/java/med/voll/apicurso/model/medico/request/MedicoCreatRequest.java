package med.voll.apicurso.model.medico.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.apicurso.model.medico.Especialidade;
import med.voll.apicurso.model.endereco.request.EnderecoRequest;

public record MedicoCreatRequest(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull @Valid
        EnderecoRequest endereco
) {
}
