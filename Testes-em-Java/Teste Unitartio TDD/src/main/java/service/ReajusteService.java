package service;

import model.Desempenho;
import model.Funcionario;

import java.math.BigDecimal;


public class ReajusteService {

    public void concederReajuste(Funcionario funcionario, Desempenho desempenho) {
            BigDecimal valor = desempenho.percentualReajuste();
            BigDecimal reajuste = funcionario.getSalario().multiply(valor);
            funcionario.realizarReajuste(reajuste);
    }

}
