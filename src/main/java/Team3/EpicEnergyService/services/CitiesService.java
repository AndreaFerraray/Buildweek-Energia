package Team3.EpicEnergyService.services;

import Team3.EpicEnergyService.entities.City;
import Team3.EpicEnergyService.repositories.CitiesRepository;
import Team3.EpicEnergyService.repositories.ProvincesRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${file.comuni}")
    private String filePath;

    public List<String[]> readFile(File file) {
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveCityFromFile() {
        File comuniItalianiFile = new File(filePath);
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
                            switch (split[3]) {
                                case ("Forlì-Cesena"): {
                                    c.setProvince(provincesRepository.findByProvince("Forli-Cesena"));
                                    break;
                                }
                                case ("Verbano-Cusio-Ossola"): {
                                    c.setProvince(provincesRepository.findByProvince("Verbania"));
                                    break;
                                }
                                case ("Valle d'Aosta/Vallée d'Aoste"): {
                                    c.setProvince(provincesRepository.findByProvince("Valle d'Aosta"));
                                    break;
                                }
                                case ("Monza e della Brianza"): {
                                    c.setProvince(provincesRepository.findByProvince("Monza-Brianza"));
                                    break;
                                }
                                case ("Bolzano/Bozen"): {
                                    c.setProvince(provincesRepository.findByProvince("Bolzano"));
                                    break;
                                }
                                case ("La Spezia"): {
                                    c.setProvince(provincesRepository.findByProvince("La-Spezia"));
                                    break;
                                }
                                case ("Reggio nell'Emilia"): {
                                    c.setProvince(provincesRepository.findByProvince("Reggio-Emilia"));
                                    break;
                                }
                                case ("Pesaro e Urbino"): {
                                    c.setProvince(provincesRepository.findByProvince("Pesaro-Urbino"));
                                    break;
                                }
                                case ("Ascoli Piceno"): {
                                    c.setProvince(provincesRepository.findByProvince("Ascoli-Piceno"));
                                    break;
                                }
                                case ("Reggio Calabria"): {
                                    c.setProvince(provincesRepository.findByProvince("Reggio-Calabria"));
                                    break;
                                }
                                case ("Sud Sardegna"): {
                                    c.setProvince(provincesRepository.findByProvince("Sassari"));
                                    break;
                                }
                                case ("Vibo Valentia"): {
                                    c.setProvince(provincesRepository.findByProvince("Vibo-Valentia"));
                                    break;
                                }
                            }
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

    public List<City> getByAbbreviation(String abbreviation) {
        return citiesRepository.findByAbbreviation(abbreviation);
    }
}
