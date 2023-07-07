package med.voll.apicurso.controller;

import med.voll.apicurso.model.PacienteFactoryTest;
import med.voll.apicurso.model.paciente.request.PacienteCreatRequest;
import med.voll.apicurso.model.paciente.response.PacienteDetalhamentoResponse;
import med.voll.apicurso.repository.PacienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PacienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<PacienteCreatRequest> dadosCadastroPacienteJson;

    @Autowired
    private JacksonTester<PacienteDetalhamentoResponse> dadosDetalhamentoPacienteJson;

    @MockBean
    private PacienteRepository repository;


    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        MockHttpServletResponse response = mvc.perform(post("/pacientes"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 201 quando informacoes estao validas")
    @WithMockUser()
    void cadastrar_cenario2() throws Exception {
        PacienteFactoryTest pacienteTest = new PacienteFactoryTest();

        PacienteDetalhamentoResponse dadosDetalhamento = pacienteTest.pacienteDetalhamento();
        when(repository.save(any())).thenReturn(pacienteTest.dadosPaciente());

        MockHttpServletResponse response = mvc.perform(post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroPacienteJson.write(
                                pacienteTest.pacienteCreat()
                        ).getJson())
                )
                .andReturn().getResponse();

        String jsonEsperado = dadosDetalhamentoPacienteJson.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}