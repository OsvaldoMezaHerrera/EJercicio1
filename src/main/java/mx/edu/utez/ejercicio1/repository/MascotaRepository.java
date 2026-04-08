package mx.edu.utez.ejercicio1.repository;

import mx.edu.utez.ejercicio1.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.LinkOption;

public interface MascotaRepository extends JpaRepository<Mascota , Long> {
}
