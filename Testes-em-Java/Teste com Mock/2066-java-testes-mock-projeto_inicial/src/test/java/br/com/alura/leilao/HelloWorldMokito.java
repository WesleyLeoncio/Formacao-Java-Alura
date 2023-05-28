package br.com.alura.leilao;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Leilao;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class HelloWorldMokito {
    @Test
    void hello(){
        LeilaoDao mock = Mockito.mock(LeilaoDao.class);
        List<Leilao> todosLeiloes = mock.buscarTodos();
        Assert.assertTrue(todosLeiloes.isEmpty());
    }
}
