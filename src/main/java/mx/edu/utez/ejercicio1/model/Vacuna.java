package mx.edu.utez.ejercicio1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "vacunas")
public class Vacuna {
    @Id @GeneratedValue
    private Long id;

    @NotBlank(message = "Ingresa una enfermedad")
    private String enfermedad;

    @ManyToMany(mappedBy = "vacunas")
    private List<Mascota> mascotas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
