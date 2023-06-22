package med.voll.apicurso.model.agendamento;

import med.voll.apicurso.model.consulta.request.ConsultaAgendamentoRequest;

public interface ValidadorAgendamentoDeConsulta {

    void validar(ConsultaAgendamentoRequest dados);
}
