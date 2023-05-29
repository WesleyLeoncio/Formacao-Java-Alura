package med.voll.apicurso.model.request.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoRequest(
        @NotBlank
        String logradouro,

        @NotBlank
        String bairro,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,

        @NotBlank
        String cidade,

        @NotBlank
        String uf,

        String complemento,

        String numero
) {
}