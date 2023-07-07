package med.voll.apicurso.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.apicurso.model.consulta.request.ConsultaAgendamentoRequest;
import med.voll.apicurso.model.consulta.request.ConsultaCancelarRequest;
import med.voll.apicurso.model.consulta.response.ConsultaDetalhamentoResponse;
import med.voll.apicurso.service.AgendaDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsulta agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid ConsultaAgendamentoRequest dados) {
        ConsultaDetalhamentoResponse dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid ConsultaCancelarRequest dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
