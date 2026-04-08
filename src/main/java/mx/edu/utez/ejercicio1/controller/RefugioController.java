package mx.edu.utez.ejercicio1.controller;

import jakarta.validation.Valid;
import mx.edu.utez.ejercicio1.dto.*;
import mx.edu.utez.ejercicio1.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RefugioController {

    @Autowired
    private MascotaService service;

    // 1. Obtener refugios (Demuestra solución a ciclo infinito)
    @GetMapping("/refugios")
    public List<RefugioResponseDTO> getRefugios() {
        return service.listarRefugios();
    }

    // 2. Obtener mascota específica por ID
    @GetMapping("/mascotas/{id}")
    public MascotaResponseDTO getMascota(@PathVariable Long id) {
        return service.buscarMascota(id);
    }

    // 3. Obtener todas las mascotas PAGINADAS
    @GetMapping("/mascotas/todas")
    public Page<MascotaSimpleDTO> getMascotasPaginadas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.listarTodasPaginadas(PageRequest.of(page, size));
    }

    // 4. Crear mascota (Usando @Valid y @RequestBody)
    @PostMapping("/mascotas")
    public ResponseEntity<MascotaResponseDTO> crearMascota(@Valid @RequestBody MascotaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearMascota(dto));
    }

    // 5. Vincular Muchos a Muchos (Mascota - Vacuna)
    @PostMapping("/mascotas/{idMascota}/vacunas/{idVacuna}")
    public ResponseEntity<String> vacunarMascota(@PathVariable Long idMascota, @PathVariable Long idVacuna) {
        service.aplicarVacuna(idMascota, idVacuna);
        return ResponseEntity.ok("Vacuna aplicada exitosamente a la mascota");
    }
}