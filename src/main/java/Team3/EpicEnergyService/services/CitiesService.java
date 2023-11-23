package Team3.EpicEnergyService.services;

import Team3.EpicEnergyService.entities.City;
import Team3.EpicEnergyService.repositories.CitiesRepository;
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
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CitiesService {
    @Autowired
    private CitiesRepository citiesRepository;
    @Autowired
    private ProvincesRepository provincesRepository;
    @Autowired
    private ProvincesService provincesService;

    public List<String[]> readFile(File file) {
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveCityFromFile() {
        File comuniItalianiFile = new File("/Users/andreaferrara/Desktop/EPICODE/BACK-END/Buildweek-Energia/file/comuni-italiani.csv");
        //File provinceFile = new File("/Users/Simo/Documents/Epicode/BackEnd/Buildweek-Energia/file/province-italiane.csv");
        List<String[]> listaComuni = new ArrayList<>(this.readFile(comuniItalianiFile));
        AtomicInteger i = new AtomicInteger();
        listaComuni.forEach(elem -> {
            for (String e : elem) {
                if (!e.contains("Codice Provincia (Storico)(1);Progressivo del Comune (2);Denominazione in italiano")) {
                    if (e.contains("#RIF!")) {
                        i.getAndIncrement();
                        City c = new City();
                        String[] split = e.split(";");
                        if (provincesRepository.findByProvince(split[3]) != null) {
                            c.setProvince(provincesRepository.findByProvince(split[3]));
                        }
                        c.setCity(split[2]);
                        c.setCodeCity(Integer.parseInt("00" + i));
                        c.setCodeProv(Integer.parseInt(split[0]));
                        citiesRepository.save(c);
                    } else {
                        City c = new City();
                        String[] split = e.split(";");
                        if (provincesRepository.findByProvince(split[3]) != null) {
                            c.setProvince(provincesRepository.findByProvince(split[3]));
                        } else {
                            System.err.println(split[3]);
                        }
                        c.setCity(split[2]);
                        c.setCodeCity(Integer.parseInt(split[1]));
                        c.setCodeProv(Integer.parseInt(split[0]));
                        citiesRepository.save(c);
                    }
                }
            }
        });
    }
}
