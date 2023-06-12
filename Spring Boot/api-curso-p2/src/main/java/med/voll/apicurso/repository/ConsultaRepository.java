package med.voll.apicurso.repository;

import med.voll.apicurso.model.consulta.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
