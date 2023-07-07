package med.voll.apicurso.model.consulta.interfaces;

import med.voll.apicurso.model.consulta.request.ConsultaCancelarRequest;

public interface ValidadorCancelamentoConsulta {
    void validar(ConsultaCancelarRequest dados);
}
