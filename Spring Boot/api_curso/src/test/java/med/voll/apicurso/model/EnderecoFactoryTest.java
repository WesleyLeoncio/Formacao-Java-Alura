package med.voll.apicurso.model;

import med.voll.apicurso.model.endereco.entity.Endereco;
import med.voll.apicurso.model.endereco.request.EnderecoRequest;

public class EnderecoFactoryTest {


    public Endereco dadosEdereco(){
        return new Endereco(dadosEnderecoRequest());
    }


    public EnderecoRequest dadosEnderecoRequest () {
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
