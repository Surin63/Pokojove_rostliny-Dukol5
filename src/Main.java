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
            listOfPlants =PlantList.loadFromFile(Settings.defaultFileName());
            System.out.println(listOfPlants.getPlants());
        } catch (PlantException e) {
            System.err.println("Chyba pri cteni ze souboru: "+e.getLocalizedMessage());
        }
       //pridani 2 novych kvetin do seznamu
        Plant plant = new Plant("Karafiat", "nemam ho rad", LocalDate.of(2002, 5, 12), LocalDate.of(2002, 6, 15), 4);
        listOfPlants.addPlant(plant);

        //13 vypis na obrazovku o zalivce
        System.out.println("Vypis na obrazovku informace o zalivce pro vsechny kvetiny"+"\n");
        System.out.println(listOfPlants.getWateringInfo());

        Plant ruze = new Plant("Ruze", "je cervena", LocalDate.now(), LocalDate.now(), 3);
        listOfPlants.addPlant(ruze);
        System.out.println(listOfPlants);

        //odebrani kvetiny s idexem 3
        listOfPlants.removePlant(3);
        System.out.println(listOfPlants);
        // ulozeni seznamu do souboru
        try {
            PlantList.saveToFile("kvetinky.txt", listOfPlants);
        } catch (PlantException e) {
            System.err.println("Chyba pri zapisu do souboru: "+e.getLocalizedMessage());;
        }
        System.out.println("serazeni rostlin podle nazvu");
        Collections.sort(listOfPlants.getPlants());



    }


    }





