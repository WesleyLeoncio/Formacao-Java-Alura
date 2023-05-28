package br.com.alura.leilao.service;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FinalizarLeilaoServiceTest {

    private FinalizarLeilaoService service;

    @Mock
    private LeilaoDao leilaoDao;

    @Mock
    private EnviadorDeEmails enviadorDeEmails;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks((this));
        this.service = new FinalizarLeilaoService(leilaoDao, enviadorDeEmails);
    }

    @Test
    @DisplayName("Deveria finalizar um leilao")
    void deveriaFinalizarUmLeilao(){
        List<Leilao> leiloes = leiloes();

        when(leilaoDao.buscarLeiloesExpirados()).thenReturn(leiloes);

        service.finalizarLeiloesExpirados();

        Leilao leilao = leiloes.get(0);
        assertTrue(leilao.isFechado());
        assertEquals(new BigDecimal("900"), leilao.getLanceVencedor().getValor());

        verify(leilaoDao).salvar(leilao);
    }

    @Test
    @DisplayName("Deveria enviar um email para o vencedor do leilao")
    void deveriaEnviarEmail(){
        List<Leilao> leiloes = leiloes();

        when(leilaoDao.buscarLeiloesExpirados()).thenReturn(leiloes);

        service.finalizarLeiloesExpirados();

        Leilao leilao = leiloes.get(0);
        Lance lanceVencedor = leilao.getLanceVencedor();

        verify(enviadorDeEmails).enviarEmailVencedorLeilao(lanceVencedor);
    }

    @Test
    @DisplayName("Não deveria enviar um email caso aconteça um erro")
    void naodeveriaEnviarEmailEmCasoDeErro(){
        List<Leilao> leiloes = leiloes();

        when(leilaoDao.buscarLeiloesExpirados()).thenReturn(leiloes);

        when(leilaoDao.salvar(any())).thenThrow(RuntimeException.class);

        try{
            service.finalizarLeiloesExpirados();
            verifyNoInteractions(enviadorDeEmails);
        }catch (Exception e){}

    }


    // Lista fake
    private List<Leilao> leiloes(){
        List<Leilao> lista = new ArrayList<>();
        Leilao leilao = new Leilao("Celular", new BigDecimal("500"), new Usuario("Ana"));
        Lance primeiro = new Lance(new Usuario("Maria"), new BigDecimal("600"));
        Lance segundo = new Lance(new Usuario("Carlos"), new BigDecimal("900"));

        leilao.propoe(primeiro);
        leilao.propoe(segundo);
        lista.add(leilao);

        return  lista;
    }
}
