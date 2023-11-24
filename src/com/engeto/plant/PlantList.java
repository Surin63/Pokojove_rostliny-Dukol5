package com.engeto.plant;

import java.io.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantList {
    private List<Plant> plants = new ArrayList<>();
    public PlantList(){};



    public void getWateringInfo() {
        for (Plant plant : plants) {
            System.out.println(plant.getName() + " " + plant.getWatering() + " " + plant.getFrequencyOfWatering());
        }

    }
    public static PlantList loadFromFile(String fileName) throws PlantException {
        PlantList result = new PlantList();
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
    private static void parseLine(String line, PlantList plantList, int lineNumber) throws PlantException{
        String[] blocks = line.split(Settings.fileItemDelimiter());
        int numOfBlocks = blocks.length;
        if(numOfBlocks !=5) {
            throw new PlantException(
                    "Nespravny pocet polozek na radku: " +line+
                            "! Pocet polozek: "+numOfBlocks+".");
        }
        String name = blocks[0].trim();
        String notes = blocks[1].trim();
        LocalDate dateOfPlanted = LocalDate.parse(blocks[3].trim());
        LocalDate dateOfWatering = LocalDate.parse(blocks[4].trim());
        try {
            int frequencyOfWatering = Integer.parseInt(blocks[2].trim());
            Plant newPlant = new Plant(name, notes, dateOfPlanted, dateOfWatering, frequencyOfWatering);
            plantList.addPlant(newPlant);
        }catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        } catch (NumberFormatException e){
            System.err.println("Neplatny format frekvence zavlazovani v souboru.");
        }
    }
    public static void saveToFile(String filename,PlantList plantList ) throws PlantException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Plant plant : plantList.plants) {
                writer.println(plant.getName()+Settings.fileItemDelimiter()
                +plant.getNotes()+Settings.fileItemDelimiter() + plant.getFrequencyOfWatering()+Settings.fileItemDelimiter()
                +plant.getPlanted()+Settings.fileItemDelimiter()
                +plant.getWatering());

            }
        }catch (IOException e) {
            throw new PlantException("Chyba pri zapisu do souboru '"+filename+"': "+e.getLocalizedMessage());
        }
    }

    public void addPlant(Plant newPlant) {plants.add(newPlant);}
    public void removePlant(int index) {
        plants.remove(index);
    }
    public Plant getPlant(int index) {return plants.get(index);}
    public List<Plant> getPlants(){
        return plants;
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

    @Override
    public String toString() {
        return "PlantList{" +
                "plants=" + plants +
                '}';
    }
}

