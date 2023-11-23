package Team3.EpicEnergyService;

import Team3.EpicEnergyService.repositories.ProvincesRepository;
import Team3.EpicEnergyService.services.CitiesService;
import Team3.EpicEnergyService.services.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunnerCityAndProvince implements CommandLineRunner {
    @Autowired
    private CitiesService citiesService;
    @Autowired
    private ProvincesService provincesService;
    @Autowired
    private ProvincesRepository provincesRepository;

    /*CAMBIARE IL PATH DEL FILE PER CARICARE SU BD*/
    @Override
    public void run(String... args) throws Exception {
        if (provincesRepository.count() == 0) {
            provincesService.saveProvinceFromFile();
            citiesService.saveCityFromFile();
        } else {
            System.err.println("PROVINCE GIA CARICATE");
        }

    }
}
