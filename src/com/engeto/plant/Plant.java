package com.engeto.plant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plant {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;


    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public Plant(String name, LocalDate planted, int frequencyOfWatering) {
        this.name = name;
        this.notes = " ";
        this.planted = planted;
        this.watering = LocalDate.now();
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public Plant(String name, LocalDate watering) {
        this.name = name;
        this.notes = " ";
        this.planted = LocalDate.now();
        this.watering = watering;
        this.frequencyOfWatering = 7;
    }

    public Plant() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException{
        if (watering.isBefore(planted)) {
            throw new PlantException("Posledni zalivka nesmi bzt starsi nez zasazeni rostliny - zadano: "+watering);
        }
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence zalivky nesmi byt 0 nebo zaporne cislo - zadane: "+frequencyOfWatering+" dnu.");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    @Override
    public String toString() {
        return
                 name +" "+ '\'' +
                notes + '\'' +
                " planted: " + planted +
                " watering: " + watering +
                " frequencyOfWatering: " + frequencyOfWatering;
    }
}


