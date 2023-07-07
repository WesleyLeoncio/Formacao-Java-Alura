package med.voll.apicurso.infra.exceptions;

import org.springframework.validation.FieldError;

public record ErroValidacaoExeption(String campo, String mensagem) {
    public ErroValidacaoExeption(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}
