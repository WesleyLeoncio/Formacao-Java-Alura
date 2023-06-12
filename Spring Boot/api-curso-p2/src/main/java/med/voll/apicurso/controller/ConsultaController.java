package med.voll.apicurso.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.apicurso.model.consulta.request.ConsultaAgendamentoRequest;
import med.voll.apicurso.model.consulta.response.ConsultaDetalhamentoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid ConsultaAgendamentoRequest agendamentoRequest) {
        System.out.println(agendamentoRequest);
        return ResponseEntity.ok(new ConsultaDetalhamentoResponse(null, null, null, null));
    }
}
