package mx.edu.utez.ejercicio1.repository;

import mx.edu.utez.ejercicio1.model.Refugio;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefugioRepository  extends JpaRepository<Refugio,Long> {
}
