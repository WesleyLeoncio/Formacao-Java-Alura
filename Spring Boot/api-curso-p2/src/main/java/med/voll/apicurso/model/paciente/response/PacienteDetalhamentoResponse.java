package med.voll.apicurso.model.paciente.response;

import med.voll.apicurso.model.endereco.entity.Endereco;
import med.voll.apicurso.model.paciente.entity.Paciente;

public record PacienteDetalhamentoResponse(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {
    public PacienteDetalhamentoResponse(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}
