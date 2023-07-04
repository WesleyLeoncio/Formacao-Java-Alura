package med.voll.apicurso.model;

import med.voll.apicurso.model.paciente.entity.Paciente;
import med.voll.apicurso.model.paciente.request.PacienteCreatRequest;

public class PacienteFactoryTest {

    public Paciente dadosPaciente() {
        return new Paciente(
                new PacienteCreatRequest(
                        "Paciente Teste",
                        "paciente@email.com",
                        "61999999999",
                        "00000000000",
                        new EnderecoFactoryTest().dadosEndereco()
                )
        );
    }
}
