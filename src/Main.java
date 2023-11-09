import com.engeto.plant.Plant;
import com.engeto.plant.PlantException;
import com.engeto.plant.PlantList;
import com.engeto.plant.Settings;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       PlantList listOfPlants = new PlantList();
       try {
            listOfPlants = (PlantList) PlantList.loadFromFile(Settings.defaultFileName());
            System.out.println(listOfPlants.getPlants());
        } catch (PlantException e) {
            System.err.println("Chyba pri cteni ze souboru: "+e.getLocalizedMessage());
        }
       listOfPlants.addPlant(new Plant("Karafiat", LocalDate.now()));

    }





}