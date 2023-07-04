package med.voll.apicurso.model;

import med.voll.apicurso.model.medico.Especialidade;
import med.voll.apicurso.model.medico.entity.Medico;
import med.voll.apicurso.model.medico.request.MedicoCreatRequest;

public class MedicoFactoryTest {

    public Medico dadosMedico() {
        return new Medico(
                new MedicoCreatRequest(
                        "Medico Teste",
                        "medico@email.com",
                        "61999999999",
                        "123456",
                        Especialidade.CARDIOLOGIA,
                        new EnderecoFactoryTest().dadosEndereco()
                )
        );
    }

}
