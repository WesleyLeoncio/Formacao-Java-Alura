package med.voll.apicurso.model.usuario.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}
