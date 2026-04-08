package mx.edu.utez.ejercicio1.service;

import mx.edu.utez.ejercicio1.dto.*;
import mx.edu.utez.ejercicio1.model.*;
import mx.edu.utez.ejercicio1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaService {

    @Autowired private MascotaRepository mascotaRepo;
    @Autowired private RefugioRepository refugioRepo;
    @Autowired private VacunaRepository vacunaRepo;

    // --- LECTURAS ---

    @Transactional(readOnly = true)
    public List<RefugioResponseDTO> listarRefugios() {
        return refugioRepo.findAll().stream().map(refugio -> new RefugioResponseDTO(
                refugio.getNombre(),
                // Mapeamos a MascotaSimple para que NO haya ciclo infinito
                refugio.getMascotas().stream()
                        .map(m -> new MascotaSimpleDTO(m.getNombre(), m.getEdad()))
                        .collect(Collectors.toList())
        )).collect(Collectors.toList());
    }

    @Transactional(readOnly = true) // El transactional evita el LazyInitializationException
    public MascotaResponseDTO buscarMascota(Long id) {
        Mascota m = mascotaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        return new MascotaResponseDTO(
                m.getNombre(),
                m.getRefugio() != null ? m.getRefugio().getNombre() : "Sin Refugio",
                m.getExpediente() != null ? m.getExpediente().getAlergias() : "Sin datos",
                m.getVacunas().stream().map(Vacuna::getEnfermedad).collect(Collectors.toList())
        );
    }

    // EL PAGINADOR
    @Transactional(readOnly = true)
    public Page<MascotaSimpleDTO> listarTodasPaginadas(Pageable pageable) {
        return mascotaRepo.findAll(pageable)
                .map(m -> new MascotaSimpleDTO(m.getNombre(), m.getEdad()));
    }

    // --- ESCRITURAS ---

    @Transactional
    public MascotaResponseDTO crearMascota(MascotaRequestDTO dto) {
        // Trampa atajada: Validamos que el refugio exista
        Refugio refugio = refugioRepo.findById(dto.refugioId())
                .orElseThrow(() -> new RuntimeException("El refugio no existe"));

        Mascota mascota = new Mascota();
        mascota.setNombre(dto.nombre());
        mascota.setEdad(dto.edad());
        mascota.setRefugio(refugio);

        Expediente exp = new Expediente();
        exp.setTipoSangre(dto.tipoSangre());
        exp.setAlergias(dto.alergias());
        mascota.setExpediente(exp); // Por el cascade=ALL, se guardará solo

        mascotaRepo.save(mascota);
        return buscarMascota(mascota.getId());
    }

    @Transactional
    public void aplicarVacuna(Long idMascota, Long idVacuna) {
        // Trampa atajada: Validamos que ambos existan antes de unirlos
        Mascota mascota = mascotaRepo.findById(idMascota)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        Vacuna vacuna = vacunaRepo.findById(idVacuna)
                .orElseThrow(() -> new RuntimeException("Vacuna no encontrada"));

        // Se unen gracias a la tabla intermedia
        mascota.getVacunas().add(vacuna);
        mascotaRepo.save(mascota);
    }
}

