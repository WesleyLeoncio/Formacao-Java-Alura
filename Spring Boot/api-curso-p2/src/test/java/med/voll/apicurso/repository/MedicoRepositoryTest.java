package med.voll.apicurso.repository;

import med.voll.apicurso.model.MedicoFactoryTest;
import med.voll.apicurso.model.PacienteFactoryTest;
import med.voll.apicurso.model.consulta.entity.Consulta;
import med.voll.apicurso.model.endereco.request.EnderecoRequest;
import med.voll.apicurso.model.medico.Especialidade;
import med.voll.apicurso.model.medico.entity.Medico;
import med.voll.apicurso.model.medico.request.MedicoCreatRequest;
import med.voll.apicurso.model.paciente.entity.Paciente;
import med.voll.apicurso.model.paciente.request.PacienteCreatRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //TODO PARA USAR O BANCO PADRÃO
@ActiveProfiles("test") // TODO PARA USAR O PROFILE DE TESTE
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Deveria devolver null quando o unico medico cadastrado nao esta disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        //given ou arrange
        LocalDateTime proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        Medico medico = cadastrarMedico(new MedicoFactoryTest().dadosMedico());
        Paciente paciente = cadastrarPaciente(new PacienteFactoryTest().dadosPaciente());
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        //when ou act
        Medico medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        //then ou assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        //given ou arrange
        LocalDateTime proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        Medico medico = cadastrarMedico(new MedicoFactoryTest().dadosMedico());

        //when ou act
        Medico medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        //then ou assert
        assertThat(medicoLivre).isEqualTo(medico);
    }


    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(Medico medico) {
        return em.persist(medico);
    }

    private Paciente cadastrarPaciente(Paciente paciente) {
        return em.persist(paciente);
    }

}