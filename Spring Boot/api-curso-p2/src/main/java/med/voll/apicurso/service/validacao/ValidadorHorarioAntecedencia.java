package med.voll.apicurso.service.validacao;


import med.voll.apicurso.infra.exceptions.ValidacaoException;
import med.voll.apicurso.model.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.apicurso.model.consulta.request.ConsultaAgendamentoRequest;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validar(ConsultaAgendamentoRequest dados) {
        LocalDateTime dataConsulta = dados.data();
        LocalDateTime agora = LocalDateTime.now();
        long diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }

    }
}
