package rh.service.promocao;

import rh.exeptions.ValidacaoException;
import rh.model.Cargo;
import rh.model.Funcionario;

public class PromocaoService {
    public  void promover(Funcionario funcionario, boolean metaBatida){
        Cargo cargoAtual = funcionario.getCargo();
        if(Cargo.GERENTE == cargoAtual){
            throw new ValidacaoException("Gerentes não podem ser promovidos!");
        }
        if (metaBatida){
            Cargo novoCargo = cargoAtual.getProximoCargo();
            funcionario.promover(novoCargo);
        }else{
            throw new ValidacaoException("Funcionario não bateu a meta!");
        }
    }
}
