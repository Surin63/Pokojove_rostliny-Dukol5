import com.engeto.plant.Plant;
import com.engeto.plant.PlantException;
import com.engeto.plant.PlantList;
import com.engeto.plant.Settings;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
       PlantList listOfPlants = new PlantList();
       try {
            listOfPlants = PlantList.loadFromFile(Settings.defaultFileName());
            System.out.println(listOfPlants.getPlants());
        } catch (PlantException e) {
            System.err.println("Chyba pri cteni ze souboru: "+e.getLocalizedMessage());
        }
       //pridani 2 novych kvetin do seznamu
        try {
            Plant plant = new Plant(
                    "Karafiat",
                    "nemam ho rad",
                    LocalDate.of(2002, 5, 12),
                    LocalDate.of(2002, 6, 15),
                    0
            );
            listOfPlants.addPlant(plant);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }
        try {
            Plant ruze = new Plant("Ruze", "je cervena", LocalDate.now(), LocalDate.now(), 1);
            listOfPlants.addPlant(ruze);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());;
        }

        //13 vypis na obrazovku o zalivce

        System.out.println("******* Vypis na obrazovku informace o zalivce pro vsechny kvetiny *******");

        listOfPlants.getWateringInfo();
        System.out.println("*******");



        System.out.println(listOfPlants);

        // odebrani kvetiny s indexem 1
        //listOfPlants.removePlant(1);
        System.out.println(listOfPlants);
        // ulozeni seznamu do souboru
        try {
            PlantList.saveToFile("kvetinky.txt", listOfPlants);
        } catch (PlantException e) {
            System.err.println("Chyba pri zapisu do souboru: "+e.getLocalizedMessage());;
        }
        System.out.println("serazeni rostlin podle nazvu");
        var plants = listOfPlants.getPlants();
        Collections.sort(plants, (first, second) -> {
            return first.getName().compareTo(second.getName());
        });
        System.out.println(plants);

        Collections.sort(plants, (first, second) -> {
            return first.getWatering().compareTo(second.getWatering());
        });
        System.out.println(plants);

    }


    }





