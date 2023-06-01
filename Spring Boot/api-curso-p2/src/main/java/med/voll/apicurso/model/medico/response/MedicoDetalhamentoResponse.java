package med.voll.apicurso.model.medico.response;

import med.voll.apicurso.model.endereco.entity.Endereco;
import med.voll.apicurso.model.medico.Especialidade;
import med.voll.apicurso.model.medico.entity.Medico;

public record MedicoDetalhamentoResponse(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {
    public MedicoDetalhamentoResponse(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
