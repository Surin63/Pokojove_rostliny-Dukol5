package com.engeto.plant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantList {
    private List<Plant> plantList = new ArrayList<>();
    private Plant plant;

    public PlantList(List<Plant> plantList, Plant plant) {
        this.plantList = plantList;
        this.plant = plant;
    }

    public String getWateringInfo() {
        for (Plant plant : plantList)
            return "Name of Plant: " + plant.getName() + " " + plant.getWatering() + " " + plant.getFrequencyOfWatering();
        return null;
    }
    public static Plant loadFromfile(String fileName) throws PlantException {
        Plant result = new Plant();

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
            }
        }catch (FileNotFoundException e) {
            throw new PlantException("Nepodarilo se nalezt soubor "+fileName+": "+e.getLocalizedMessage());
        }
        return result;
    }
    public void addPlant(Plant newPlant) {plantList.add(newPlant);}
    public Plant getPlant(int index) {return plantList.get(index);}
    public void removePlant(int index) {plantList.remove(index);}
    public List<Plant> getPlantList() {return plantList;}
    public void addAllPlant(List<Plant> plantNewList) {this.plantList.addAll(plantNewList);}
    public void setPlantList(List<Plant> plantList) {this.plantList = plantList;}
}
