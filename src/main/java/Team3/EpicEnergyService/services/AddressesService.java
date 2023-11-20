package Team3.EpicEnergyService.services;

import Team3.EpicEnergyService.entities.Address;
import Team3.EpicEnergyService.payloads.users.AddressesDTO;
import Team3.EpicEnergyService.repositories.AddressesRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AddressesService {

    @Autowired
    private AddressesRepository addressesRepository;

    public List<String[]> readFile(File file) {
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }


    public void saveAddress(AddressesDTO fromRequest) {
        Address a = new Address();
        File comuniItalianiFile = new File("/Users/Simo/Documents/Epicode/BackEnd/Buildweek-Energia/file/comuni-italiani.csv");
        File provinceFile = new File("/Users/Simo/Documents/Epicode/BackEnd/Buildweek-Energia/file/province-italiane.csv");
        List<String[]> listaComuni = new ArrayList<>();
        List<String[]> listaProvince = new ArrayList<>();
        listaComuni.addAll(this.readFile(comuniItalianiFile));
        for (String[] comune : listaComuni) {
            //System.out.println("NOT IF" + Arrays.toString(comune));
            if (Arrays.stream(comune).allMatch(c -> c.contains(fromRequest.city()))) {
                a.setCity(Arrays.toString(comune));
            }
        }
        for (String[] provincia : listaProvince) {
            if (Arrays.stream(provincia).allMatch(p -> p.contains(fromRequest.province()))) {
                a.setProvince(Arrays.toString(provincia));
            }
        }


       /* a.setZipcode(fromRequest.zipCode());
        a.setCivicNumber(fromRequest.civicNumber());
        return addressesRepository.save(a);*/

    }
}
