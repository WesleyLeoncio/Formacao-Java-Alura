package med.voll.apicurso.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.apicurso.model.consulta.request.ConsultaAgendamentoRequest;
import med.voll.apicurso.service.AgendaDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsulta agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid ConsultaAgendamentoRequest dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }
}
