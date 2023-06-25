package med.voll.apicurso.model.consulta.interfaces;

import med.voll.apicurso.model.consulta.request.ConsultaAgendamentoRequest;

public interface ValidadorAgendamentoDeConsulta {

    void validar(ConsultaAgendamentoRequest dados);
}
