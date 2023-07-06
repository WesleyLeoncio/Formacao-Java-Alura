package med.voll.apicurso.model;

import med.voll.apicurso.model.paciente.entity.Paciente;
import med.voll.apicurso.model.paciente.request.PacienteCreatRequest;
import med.voll.apicurso.model.paciente.response.PacienteDetalhamentoResponse;

public class PacienteFactoryTest {

    public Paciente dadosPaciente() {
        return new Paciente(pacienteCreat());
    }

    public PacienteCreatRequest pacienteCreat() {
        return new PacienteCreatRequest(
                "Paciente Teste",
                "paciente@email.com",
                "61999999999",
                "000.000.000-00",
                new EnderecoFactoryTest().dadosEnderecoRequest()
        );
    }

    public PacienteDetalhamentoResponse pacienteDetalhamento() {
        return new PacienteDetalhamentoResponse(
                null,
                "Paciente Teste",
                "paciente@email.com",
                "000.000.000-00",
                "61999999999",
                new EnderecoFactoryTest().dadosEdereco()
        );
    }
}
