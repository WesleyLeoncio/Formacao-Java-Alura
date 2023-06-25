package med.voll.apicurso.service;

import med.voll.apicurso.infra.exceptions.ValidacaoException;
import med.voll.apicurso.model.consulta.interfaces.ValidadorAgendamentoDeConsulta;
import med.voll.apicurso.model.consulta.entity.Consulta;
import med.voll.apicurso.model.consulta.interfaces.ValidadorCancelamentoConsulta;
import med.voll.apicurso.model.consulta.request.ConsultaAgendamentoRequest;
import med.voll.apicurso.model.consulta.request.ConsultaCancelarRequest;
import med.voll.apicurso.model.consulta.response.ConsultaCancelarResponse;
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
    private List<ValidadorAgendamentoDeConsulta> validadoresAgendamento;

    @Autowired
    private List<ValidadorCancelamentoConsulta> validadorCancelamentoConsultas;

    public ConsultaDetalhamentoResponse agendar(ConsultaAgendamentoRequest dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadoresAgendamento.forEach(v -> v.validar(dados));

        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        Medico medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        Consulta consulta = new Consulta(null, medico, paciente, dados.data());
        consultaRepository.save(consulta);
        return new ConsultaDetalhamentoResponse(consulta);
    }

    public ConsultaCancelarResponse cancelar(ConsultaCancelarRequest dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Consulta não existe!");
        }
        if (dados.cancelamento() == null) {
            throw new ValidacaoException("Campo modivo cancelamento deve ser preenchido");
        }
        return realizarCancelamento(dados);
    }

    private ConsultaCancelarResponse realizarCancelamento(ConsultaCancelarRequest dados) {
        validadorCancelamentoConsultas.forEach(v -> v.validar(dados));
        Consulta consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.setMotivoCancelamento(dados.cancelamento());
        consultaRepository.save(consulta);
        return new ConsultaCancelarResponse(consulta);
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
