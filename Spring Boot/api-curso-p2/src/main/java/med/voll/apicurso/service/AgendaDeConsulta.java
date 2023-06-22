package med.voll.apicurso.service;

import med.voll.apicurso.infra.exceptions.ValidacaoException;
import med.voll.apicurso.model.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.apicurso.model.consulta.entity.Consulta;
import med.voll.apicurso.model.consulta.request.ConsultaAgendamentoRequest;
import med.voll.apicurso.model.consulta.response.ConsultaDetalhamentoResponse;
import med.voll.apicurso.model.medico.entity.Medico;
import med.voll.apicurso.model.paciente.entity.Paciente;
import med.voll.apicurso.repository.ConsultaRepository;
import med.voll.apicurso.repository.MedicoRepository;
import med.voll.apicurso.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public ConsultaDetalhamentoResponse agendar(ConsultaAgendamentoRequest dados){
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        Medico medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        Consulta consulta = new Consulta(null,medico,paciente,dados.data());
        consultaRepository.save(consulta);
        return new ConsultaDetalhamentoResponse(consulta);
    }

    private Medico escolherMedico(ConsultaAgendamentoRequest dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

}
