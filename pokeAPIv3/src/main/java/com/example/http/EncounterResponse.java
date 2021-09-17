package com.example.http;


public class EncounterResponse {
    LocationArea location_area;

    public LocationArea getLocation_area() {
        return location_area;
    }

    public void setLocation_area(LocationArea location_area) {
        this.location_area = location_area;
    }
}

class LocationArea {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}