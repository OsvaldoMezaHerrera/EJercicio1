package mx.edu.utez.ejercicio1.dto;

import java.util.List;

public record MascotaResponseDTO(
        String nombre,
        String refugioNombre,
        String alergias,
        List<String> vacunasAplicadas
) {}