package mx.edu.utez.ejercicio1.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "refugios")
public class Refugio {

    @Id @GeneratedValue
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max= 60, min= 3)
    private String nombre;

    @OneToMany(mappedBy = "refugio",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<Mascota> mascotas = new ArrayList<>();

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
