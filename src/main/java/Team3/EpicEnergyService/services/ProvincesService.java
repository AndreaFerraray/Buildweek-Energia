package Team3.EpicEnergyService.services;

import Team3.EpicEnergyService.entities.Province;
import Team3.EpicEnergyService.repositories.ProvincesRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProvincesService {
    @Autowired
    private ProvincesRepository provincesRepository;

    public List<String[]> readFile(File file) {
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveProvinceFromFile() {
        File provinceFile = new File("/Users/andreaferrara/Desktop/EPICODE/BACK-END/Buildweek-Energia/file/comuni-italiani.csv");
        List<String[]> listaProvince = new ArrayList<>(this.readFile(provinceFile));
        listaProvince.forEach(elem -> {
            for (String e : elem) {
                if (!e.contains("Sigla;Provincia;Regione")) {
                    Province p = new Province();
                    String[] split = e.split(";");
                    p.setAbbreviation(split[0]);
                    p.setProvince(split[1]);
                    p.setRegion(split[2]);
                    provincesRepository.save(p);
                }
            }
        });
    }
}
