package rh.service.reajuste;

import rh.exeptions.ValidacaoException;
import rh.model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ValidacaoPeriodicidadeEntreReajustes implements ValidacaoReajuste{
    @Override
    public void validar(Funcionario funcionario, BigDecimal aumento) {
        LocalDate dataUtimoReajuste = funcionario.getDataUltimoReajuste();
        LocalDate dataAtual = LocalDate.now();
        long mesesDesUltimoReajuste = ChronoUnit.MONTHS.between(dataUtimoReajuste, dataAtual);
        if(mesesDesUltimoReajuste < 6){
            throw new ValidacaoException("Intevarlo entre reajustes deve ser de no minimo 6 meses!");
        }
    }
}
