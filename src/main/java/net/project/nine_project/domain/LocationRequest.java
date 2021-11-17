package net.project.nine_project.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationRequest {
    @JsonProperty("provinces_id")
    private String provincesId;

    @JsonProperty("cities_id")
    private String citiesId;

    @JsonProperty("areas_id")
    private String areasId;

    public String getProvincesId() {
        return provincesId;
    }

    @Override
    public String toString() {
        return "LocationRequest{" +
                "provincesId='" + provincesId + '\'' +
                ", citiesId='" + citiesId + '\'' +
                ", areasId='" + areasId + '\'' +
                '}';
    }

    public void setProvincesId(String provincesId) {
        this.provincesId = provincesId;
    }

    public String getCitiesId() {
        return citiesId;
    }

    public void setCitiesId(String citiesId) {
        this.citiesId = citiesId;
    }

    public String getAreasId() {
        return areasId;
    }

    public void setAreasId(String areasId) {
        this.areasId = areasId;
    }
}
