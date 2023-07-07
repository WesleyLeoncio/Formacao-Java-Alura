package med.voll.apicurso.model;

import med.voll.apicurso.model.consulta.request.ConsultaAgendamentoRequest;
import med.voll.apicurso.model.consulta.response.ConsultaDetalhamentoResponse;
import med.voll.apicurso.model.medico.Especialidade;

import java.time.LocalDateTime;

public class ConsultaFactoryTest {

    public ConsultaDetalhamentoResponse consultaDetalhamento(){
        LocalDateTime data = LocalDateTime.now().plusHours(1);
        return new ConsultaDetalhamentoResponse(null, 2l, 5l, data);
    }

    public ConsultaAgendamentoRequest consultaAgendamento(){
        LocalDateTime data = LocalDateTime.now().plusHours(1);
        return new ConsultaAgendamentoRequest(2l, 5l, data, Especialidade.CARDIOLOGIA);
    }

}
