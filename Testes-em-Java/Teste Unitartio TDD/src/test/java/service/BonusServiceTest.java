package service;


import model.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class BonusServiceTest {

    private BonusService bonusService;

    @BeforeEach
    public void inicializar(){
        this.bonusService = new BonusService();
    }

    @Test
    void deveriaReceberUmaExeptionSeOsalarioForMuitoAlto() {
        Funcionario funcionario = new Funcionario("F1", LocalDate.now(), new BigDecimal("25000"));
        assertThrows(IllegalArgumentException.class, ()-> bonusService.calcularBonus(funcionario));
    }

    @Test
    void deveriaDaUmBonusdeDezPorcentoSeOsalarioForMenorQueDezMil(){
        Funcionario funcionario = new Funcionario("F1", LocalDate.now(), new BigDecimal("2000"));
        BigDecimal valor = bonusService.calcularBonus(funcionario);
        assertEquals(new BigDecimal("200.0"),valor);
    }

    @Test
    void deveriaDaUmBonusDeDezPorcentoSeOsalarioForIqualADezMil(){
        Funcionario funcionario = new Funcionario("F1", LocalDate.now(), new BigDecimal("10000"));
        BigDecimal valor = bonusService.calcularBonus(funcionario);
        assertEquals(new BigDecimal("1000.0"),valor);
    }



}