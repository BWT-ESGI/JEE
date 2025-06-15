package jee.spring.esgi.dto;

import java.util.List;

public class RentalPropertySearchRequest {
    private Boolean nearVelibStations;
    private List<String> towns;

    public Boolean getNearVelibStations() { return nearVelibStations; }
    public void setNearVelibStations(Boolean nearVelibStations) { this.nearVelibStations = nearVelibStations; }
    public List<String> getTowns() { return towns; }
    public void setTowns(List<String> towns) { this.towns = towns; }
}