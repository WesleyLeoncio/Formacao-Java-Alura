package med.voll.apicurso.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import med.voll.apicurso.model.medico.entity.Medico;
import med.voll.apicurso.model.paciente.entity.Paciente;
import med.voll.apicurso.model.paciente.request.PacienteCreatRequest;
import med.voll.apicurso.model.paciente.request.PacienteEditRequest;
import med.voll.apicurso.model.paciente.response.PacienteDetalhamentoResponse;
import med.voll.apicurso.model.paciente.response.PacienteResponse;
import med.voll.apicurso.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteDetalhamentoResponse> cadastrar(@RequestBody @Valid PacienteCreatRequest pacienteRequest, UriComponentsBuilder uriBuilder) {
        Paciente paciente = repository.save(new Paciente(pacienteRequest));
        PacienteDetalhamentoResponse pacienteDetalhamento = new PacienteDetalhamentoResponse(paciente);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pacienteDetalhamento.id()).toUri();
        return ResponseEntity.created(uri).body(pacienteDetalhamento);
    }

    @GetMapping
    public ResponseEntity<Page<PacienteResponse>> litarTodos(Pageable pageable){
        Page<PacienteResponse> pacienteResponsePage = repository.findAllByAtivoTrue(pageable).map(PacienteResponse::new);
        return ResponseEntity.ok(pacienteResponsePage);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PacienteDetalhamentoResponse> atualizar(@RequestBody @Valid PacienteEditRequest pacienteEditRequest){
        Paciente paciente = repository.getReferenceById(pacienteEditRequest.id());
        paciente.atualizarInformacoes(pacienteEditRequest);
        return ResponseEntity.ok(new PacienteDetalhamentoResponse(paciente));
    }

    //TODO EXCLUSÃO LOGICA
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Null> excluir(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDetalhamentoResponse> detalhar(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteDetalhamentoResponse(paciente));
    }
}
