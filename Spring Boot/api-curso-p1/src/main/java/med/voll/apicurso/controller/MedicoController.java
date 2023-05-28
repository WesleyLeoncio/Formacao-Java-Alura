package med.voll.apicurso.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.apicurso.model.Medico;
import med.voll.apicurso.model.request.medico.MedicoCreatRequest;
import med.voll.apicurso.model.request.medico.MedicoEditRequest;
import med.voll.apicurso.model.response.MedicoResponse;
import med.voll.apicurso.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

//TODO ESSA CLASS ESTÁ SEGUINDO OS REQUISISTOS QUE FORAM REQUISITADOS PELO USUARIO
@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    MedicoRepository repository;

    @PostMapping
    @Transactional
    public MedicoResponse cadastrar(@RequestBody @Valid MedicoCreatRequest medicoRequest) {
        return new MedicoResponse(repository.save(new Medico(medicoRequest)));
    }

    @GetMapping
    public Page<MedicoResponse> litarTodos(Pageable pageable){
        return repository.findAllByAtivoTrue(pageable).map(MedicoResponse::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid MedicoEditRequest medicoEditRequest){
        Medico medico = repository.getReferenceById(medicoEditRequest.id());
        medico.atualizarInformacoes(medicoEditRequest);
    }

    //TODO EXCLUSÃO LOGICA
    @DeleteMapping("/{id}")
    @org.springframework.transaction.annotation.Transactional
    public void excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }

}
