package med.voll.apicurso.model;

import med.voll.apicurso.model.endereco.request.EnderecoRequest;

public class EnderecoFactoryTest {

    public EnderecoRequest dadosEndereco() {
        return new EnderecoRequest(
                "rua teste",
                "bairro teste",
                "00000000",
                "Brasilia",
                "DF",
                "Casa de Teste",
                "52"
        );
    }

}
