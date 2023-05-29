package med.voll.apicurso.model.response;

import med.voll.apicurso.model.Endereco;
import med.voll.apicurso.model.Especialidade;
import med.voll.apicurso.model.Medico;

public record MedicoDetalhamentoResponse(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {
    public MedicoDetalhamentoResponse(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
