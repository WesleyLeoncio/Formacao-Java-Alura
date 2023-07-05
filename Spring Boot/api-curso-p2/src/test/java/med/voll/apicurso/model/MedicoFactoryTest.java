package med.voll.apicurso.model;

import med.voll.apicurso.model.endereco.entity.Endereco;
import med.voll.apicurso.model.medico.Especialidade;
import med.voll.apicurso.model.medico.entity.Medico;
import med.voll.apicurso.model.medico.request.MedicoCreatRequest;
import med.voll.apicurso.model.medico.response.MedicoDetalhamentoResponse;

public class MedicoFactoryTest {

    public Medico dadosMedico() {
        return new Medico(medicoCreat());
    }

    public MedicoCreatRequest medicoCreat(){
        return new MedicoCreatRequest(
                "Medico Teste",
                "medico@email.com",
                "61999999999",
                "123456",
                Especialidade.CARDIOLOGIA,
                new EnderecoFactoryTest().dadosEnderecoRequest()
        );
    }

    public MedicoDetalhamentoResponse medicoDetalhamento(){
        return new MedicoDetalhamentoResponse(
                null,
                "Medico Teste",
                "medico@email.com",
                "61999999999",
                "123456",
                Especialidade.CARDIOLOGIA,
                new EnderecoFactoryTest().dadosEdereco()
        );
    }

}
