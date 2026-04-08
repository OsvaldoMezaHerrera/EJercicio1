package mx.edu.utez.ejercicio1.config;

import mx.edu.utez.ejercicio1.model.*;
import mx.edu.utez.ejercicio1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired private RefugioRepository refugioRepo;
    @Autowired private MascotaRepository mascotaRepo;
    @Autowired private VacunaRepository vacunaRepo;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("⏳ Cargando datos de prueba...");

        // 1. Creamos un Refugio
        Refugio refugio1 = new Refugio();
        refugio1.setNombre("Patitas Felices Temixco");
        refugioRepo.save(refugio1);

        // 2. Creamos Vacunas (Catálogo)
        Vacuna rabia = new Vacuna();
        rabia.setEnfermedad("Rabia");
        vacunaRepo.save(rabia);

        Vacuna parvo = new Vacuna();
        parvo.setEnfermedad("Parvovirus");
        vacunaRepo.save(parvo);

        // 3. Creamos Mascota 1 (Max) con su Expediente
        Mascota max = new Mascota();
        max.setNombre("Max");
        max.setEdad(3);
        max.setRefugio(refugio1); // Asignamos el refugio (ManyToOne)

        Expediente expMax = new Expediente();
        expMax.setTipoSangre("DEA 1.1");
        expMax.setAlergias("Pollo");
        max.setExpediente(expMax); // Asignamos expediente (OneToOne)

        // Le aplicamos la vacuna de la rabia (ManyToMany)
        max.getVacunas().add(rabia);

        mascotaRepo.save(max);

        // 4. Creamos Mascota 2 (Luna) con su Expediente
        Mascota luna = new Mascota();
        luna.setNombre("Luna");
        luna.setEdad(1);
        luna.setRefugio(refugio1);

        Expediente expLuna = new Expediente();
        expLuna.setTipoSangre("DEA 1.2");
        expLuna.setAlergias("Ninguna");
        luna.setExpediente(expLuna);

        // Le aplicamos ambas vacunas
        luna.getVacunas().add(rabia);
        luna.getVacunas().add(parvo);

        mascotaRepo.save(luna);

        System.out.println("✅ Datos de prueba cargados exitosamente.");
    }
}