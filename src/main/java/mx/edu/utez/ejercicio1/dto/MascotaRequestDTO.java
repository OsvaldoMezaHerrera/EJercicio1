package mx.edu.utez.ejercicio1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MascotaRequestDTO(
        @NotBlank(message = "Nombre obligatorio") String nombre,
        @Positive(message = "Edad positiva") int edad,
        @NotNull(message = "Debe pertenecer a un refugio") Long refugioId,
        // Pedimos datos del expediente de una vez
        @NotBlank String tipoSangre,
        @NotBlank String alergias
) {}
