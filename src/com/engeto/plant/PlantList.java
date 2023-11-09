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

public class PlantList extends Plant {
    private List<Plant> plants = new ArrayList<>();
    public PlantList(){};



    public String getWateringInfo() {
        for (Plant plant : plants)
            return "Name of Plant: " + plant.getName() + " " + plant.getWatering() + " " + plant.getFrequencyOfWatering();
        return null;
    }
    public static Plant loadFromFile(String fileName) throws PlantException {
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
        if(numOfBlocks !=4) {
            throw new PlantException(
                    "Nespravny pocet polozek na radku: " +line+
                            "! Pocet polozek: "+numOfBlocks+".");
        }
        String name = blocks[0].trim();
        String notes = blocks[1].trim();
        LocalDate dateOfPlanted = LocalDate.parse(blocks[3].trim());
        LocalDate dateOfWatering = LocalDate.parse(blocks[4].trim());
        int frequencyOfWatering = 0;
        try {
            frequencyOfWatering = Integer.parseInt(blocks[2].trim());
        }catch (NumberFormatException e) {
            throw new PlantException("Neplatny format frekvence zavlazovani v souboru.");
        }

            Plant newPlant = new Plant(name, notes, dateOfPlanted, dateOfWatering, frequencyOfWatering);



    }

    public void addPlant(Plant newPlant) {plants.add(newPlant);}
    public void removePlant(int index) {
        plants.remove(index);
    }
    public Plant getPlant(int index) {return plants.get(index);}
    public List<Plant> getPlants(){
        return new ArrayList<>(plants);
    }
    public void addAllPlant(List<Plant> listOfNewPlants) {
        this.plants = new ArrayList<>(listOfNewPlants);
    }
    public void replaceAllPlant(List<Plant> listOfNewPlants) {
        this.plants = new ArrayList<>(listOfNewPlants);
    }
    public Plant get(int index) {
        return plants.get(index);
    }

}

