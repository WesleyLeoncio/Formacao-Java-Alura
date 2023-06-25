package med.voll.apicurso.service.validacao.cancelamento;

import med.voll.apicurso.infra.exceptions.ValidacaoException;
import med.voll.apicurso.model.consulta.entity.Consulta;
import med.voll.apicurso.model.consulta.interfaces.ValidadorCancelamentoConsulta;
import med.voll.apicurso.model.consulta.request.ConsultaCancelarRequest;
import med.voll.apicurso.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ValidadorHorarioAntecedenciaCancelamento implements ValidadorCancelamentoConsulta {

    @Autowired
    ConsultaRepository consultaRepository;

    public void validar(ConsultaCancelarRequest dados) {
        Consulta consulta = consultaRepository.getReferenceById(dados.idConsulta());
        LocalDateTime agora = LocalDateTime.now();
        long diferencaEmHoras = Duration.between(agora,consulta.getData()).toHours();
        if (diferencaEmHoras < 24){
            throw new ValidacaoException("Consulta somente pode ser Cancelada com antecedência mínima de 24h!");
        }
    }
}
