package med.voll.apicurso.model.paciente.response;

import med.voll.apicurso.model.paciente.entity.Paciente;

public record PacienteResponse(Long id, String nome, String email, String cpf) {
    public PacienteResponse(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
