package med.voll.apicurso.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import med.voll.apicurso.model.medico.entity.Medico;
import med.voll.apicurso.model.medico.request.MedicoCreatRequest;
import med.voll.apicurso.model.medico.request.MedicoEditRequest;
import med.voll.apicurso.model.medico.response.MedicoDetalhamentoResponse;
import med.voll.apicurso.model.medico.response.MedicoResponse;
import med.voll.apicurso.repository.MedicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
    final
    MedicoRepository repository;

    public MedicoController(MedicoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDetalhamentoResponse> cadastrar(@RequestBody @Valid MedicoCreatRequest medicoRequest, UriComponentsBuilder uriBuilder) {
        Medico medico = repository.save(new Medico(medicoRequest));
        MedicoDetalhamentoResponse medicoDetalhado = new MedicoDetalhamentoResponse(medico);
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicoDetalhado.id()).toUri();
        return ResponseEntity.created(uri).body(medicoDetalhado);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoResponse>> litarTodos(Pageable pageable){
        Page<MedicoResponse> medicoResponsePage = repository.findAllByAtivoTrue(pageable).map(MedicoResponse::new);
        return ResponseEntity.ok(medicoResponsePage);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoDetalhamentoResponse> atualizar(@RequestBody @Valid MedicoEditRequest medicoEditRequest){
        Medico medico = repository.getReferenceById(medicoEditRequest.id());
        medico.atualizarInformacoes(medicoEditRequest);
        return ResponseEntity.ok(new MedicoDetalhamentoResponse(medico));
    }

    //TODO EXCLUSÃO LOGICA
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Null> excluir(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDetalhamentoResponse> detalhar(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoDetalhamentoResponse(medico));
    }

}
