package Team3.EpicEnergyService;

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

    /*CAMBIARE IL PATH DEL FILE PER CARICARE SU BD*/
    @Override
    public void run(String... args) throws Exception {
        provincesService.saveProvinceFromFile();
        citiesService.saveCityFromFile();

    }
}
