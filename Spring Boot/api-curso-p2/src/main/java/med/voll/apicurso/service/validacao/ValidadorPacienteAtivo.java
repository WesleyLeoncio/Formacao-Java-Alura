package med.voll.apicurso.service.validacao;


import med.voll.apicurso.infra.exceptions.ValidacaoException;
import med.voll.apicurso.model.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.apicurso.model.consulta.request.ConsultaAgendamentoRequest;
import med.voll.apicurso.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(ConsultaAgendamentoRequest dados) {
        boolean pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
