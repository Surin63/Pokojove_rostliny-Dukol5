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
           System.out.println("         ******* Nacteni seznamu kvetin ze souboru *******");
            System.out.println(listOfPlants.getPlants());
        } catch (PlantException e) {
            System.err.println("Chyba pri cteni ze souboru: "+e.getLocalizedMessage());
        }

        System.out.println("            ******* Pridani 2 kvetin do seznamu *******");
        try {
            Plant plant = new Plant(
                    "Karafiat",
                    "nemam ho rad",
                    LocalDate.of(2002, 5, 12),
                    LocalDate.of(2002, 6, 15),
                    0);
            listOfPlants.addPlant(plant);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }
        try {
            Plant ruze = new Plant(
                    "Ruze",
                    "je cervena",
                    LocalDate.now(),
                    LocalDate.now(),
                    1);
            listOfPlants.addPlant(ruze);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());;
        }
        try {
            Plant levandule = new Plant(
                    "Levandule",
                    "nekvete",
                    LocalDate.of(2022, 5,13),
                    LocalDate.of(2022,5,23),
                    5);
            listOfPlants.addPlant(levandule);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());;
        }
        System.out.println();
        System.out.println(listOfPlants.getPlants());

        System.out.println("            ******* Odebrani kvetiny ze seznamu pomoci indexu*******");
        listOfPlants.removePlant(1);
        System.out.println(listOfPlants.getPlants());


        //13 vypis na obrazovku o zalivce
        System.out.println("            ******* Vypis na obrazovku informace o zalivce pro vsechny kvetiny *******");

        listOfPlants.getWateringInfo();






        // ulozeni seznamu do souboru
        try {
            PlantList.saveToFile("kvetinky.txt", listOfPlants);
        } catch (PlantException e) {
            System.err.println("Chyba pri zapisu do souboru: "+e.getLocalizedMessage());;
        }
        System.out.println("\n");
        System.out.println("            *******Serazeni rostlin podle nazvu*******");
        var plants = listOfPlants.getPlants();
        Collections.sort(plants, (first, second) -> {
            return first.getName().compareTo(second.getName());
        });
        System.out.println(plants);
        System.out.println("\n");
        System.out.println("            *******Serazeni podle posledni zalivky*******");
        Collections.sort(plants, (first, second) -> {
            return first.getWatering().compareTo(second.getWatering());
        });

        System.out.println(plants);

    }


    }





