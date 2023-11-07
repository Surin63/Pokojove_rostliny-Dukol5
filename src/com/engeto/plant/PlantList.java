package com.engeto.plant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PlantList {
    private static List<Plant> plants;

    public PlantList() {plants = new ArrayList<>();}
    public static void addPlant(Plant plant){
        plants.add(plant);
    }




    public String getWateringInfo() {
        for (Plant plant : plants)
            return "Name of Plant: " + plant.getName() + " " + plant.getWatering() + " " + plant.getFrequencyOfWatering();
        return null;
    }
    public static Plant loadFromfile(String fileName) throws PlantException {
        Plant result = new Plant();
        int lineNumber = 1;

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parseLine(line, result, lineNumber);
                lineNumber++;
            }
        }catch (FileNotFoundException e) {
            throw new PlantException("Nepodarilo se nalezt soubor "+fileName+": "+e.getLocalizedMessage());
        }
        return result;
    }
    private static void parseLine(String line, Plant plant, int lineNumber) throws PlantException{
        String[] blocks = line.split(Settings.fileItemDelimiter());
        int numOfBlocks = blocks.length;
        if(numOfBlocks !=3) {
            throw new PlantException(
                    "Nespravny pocet polozek na radku: " +line+
                            "! Pocet polozek: "+numOfBlocks+".");
        }
        String name = blocks[0].trim();
        String notes = blocks[1].trim();
        LocalDate.parse(blocks[2].trim());
        LocalDate.parse(blocks[3].trim());
        Integer.parseInt(blocks[4].trim());
        BigDecimal price;
        try {
            price = new BigDecimal(blocks[1].trim());
        }catch (NumberFormatException e) {
            throw new PlantException("Chybe zadane cislo "+blocks[1]+" na radku c. "+lineNumber+": "+line+"!");
        }

        Plant newPlant = new Plant(name, notes, category);
        plant.addPlant(newPlant);

    }


    public Plant getPlant(int index) {return plants.get(index);}
    public void removePlant(int index) {plants.remove(index);}
    public List<Plant> getPlantList() {return plants;}
    public void addAllPlant(List<Plant> plantNewList) {this.plants.addAll(plantNewList);}
    public void setPlantList(List<Plant> plantList) {this.plants = plantList;}

}

