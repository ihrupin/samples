package com.hrupin.sample.filterlist;

public class Country {

    private String name;
    private int area;
    private int population;

    public Country(String name, int population, int area) {
        this.name = name;
        this.area = area;
        this.population = population;
    }
    
    public int getArea() {
        return area;
    }
    
    public String getName() {
        return name;
    }
    
    public int getPopulation() {
        return population;
    }
    
    @Override
    public String toString() {
        return this.name;
    }

}
