package Team3.EpicEnergyService;

import Team3.EpicEnergyService.services.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunnerProva implements CommandLineRunner {
    @Autowired
    private CitiesService citiesService;

    @Override
    public void run(String... args) throws Exception {
        /*File provinceFile = new File("/Users/Simo/Documents/Epicode/BackEnd/Buildweek-Energia/file/province-italiane.csv");
        List<String[]> r = addressesService.readFile(provinceFile);
        r.forEach(x -> System.out.println(Arrays.toString(x)));*/
        citiesService.saveCityFromFile();
    }
}
