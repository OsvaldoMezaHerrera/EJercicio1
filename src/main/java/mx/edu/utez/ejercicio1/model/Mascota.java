package mx.edu.utez.ejercicio1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mascotas")
public class Mascota {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "La mascota debe tener un nombre")
    private String nombre;

    @Positive
    private int edad;

    @ManyToOne
    @JoinColumn(name = "refugio_id")
    private Refugio refugio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "expediente_id",
                referencedColumnName = "id")
    private Expediente expediente;

    @ManyToMany
    @JoinTable(
            name = "macota_vacuna",
            joinColumns = @JoinColumn(name = "mascota_id"),
            inverseJoinColumns = @JoinColumn(name = "vacuna_id")
    )
    private List<Vacuna> vacunas = new ArrayList<>();

    // getter and setters


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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Refugio getRefugio() {
        return refugio;
    }

    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public List<Vacuna> getVacunas() {
        return vacunas;
    }

    public void setVacunas(List<Vacuna> vacunas) {
        this.vacunas = vacunas;
    }
}
