package BullyCars.Resenas.Config;

import BullyCars.Resenas.Models.Resena;
import BullyCars.Resenas.Repositories.ResenaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final ResenaRepository resenaRepository;

    public DataSeeder(ResenaRepository resenaRepository) {
        this.resenaRepository = resenaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (resenaRepository.count() == 0) {
            Resena resena = new Resena();
            resena.setClienteId(1L);
            resena.setCalificacion(5);
            resena.setComentario("Excelente servicio, muy rápidos, prolijos y transparentes en la cotización.");
            resenaRepository.save(resena);

            Resena resena2 = new Resena();
            resena2.setClienteId(2L);
            resena2.setCalificacion(5);
            resena2.setComentario("El Stealth Matte Wrap quedó espectacular, la atención al detalle y las terminaciones son perfectas. Totalmente recomendado.");
            resenaRepository.save(resena2);
        }
    }
}
