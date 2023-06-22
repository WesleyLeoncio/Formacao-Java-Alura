package med.voll.apicurso.service.validacao;

import med.voll.apicurso.infra.exceptions.ValidacaoException;
import med.voll.apicurso.model.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.apicurso.model.consulta.request.ConsultaAgendamentoRequest;
import med.voll.apicurso.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(ConsultaAgendamentoRequest dados) {
        boolean medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }

}
