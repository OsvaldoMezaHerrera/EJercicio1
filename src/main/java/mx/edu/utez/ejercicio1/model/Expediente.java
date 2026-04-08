package mx.edu.utez.ejercicio1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "expedientes")
public class Expediente {
    @Id @GeneratedValue
    private Long id;

    @NotBlank(message = "el tipo de sangre es obligatorio")
    private String tipoSangre;

    @NotBlank(message = "las alergias son obligatorias")
    private String alergias;

    @OneToOne(mappedBy = "expediente")
    private Mascota mascota;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}
