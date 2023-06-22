package med.voll.apicurso.service.validacao;


import med.voll.apicurso.infra.exceptions.ValidacaoException;
import med.voll.apicurso.model.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.apicurso.model.consulta.request.ConsultaAgendamentoRequest;
import med.voll.apicurso.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(ConsultaAgendamentoRequest dados) {
        //escolha do medico opcional
        if (dados.idMedico() == null) {
            return;
        }

        boolean medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");
        }
    }

}
