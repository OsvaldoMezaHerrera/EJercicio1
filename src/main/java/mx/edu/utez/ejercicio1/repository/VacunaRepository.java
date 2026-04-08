package mx.edu.utez.ejercicio1.repository;

import mx.edu.utez.ejercicio1.model.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacunaRepository extends JpaRepository<Vacuna, Long> {
}
