package net.engineeringdigest.journalApp.apiResponse;
import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import javax.xml.stream.Location;
import java.util.ArrayList;

@Data
public class WeatherResponse {


    private  Location location;
    private Current current;

    @Data
    public class Current{
        private int temperature;
        @JsonProperty("wind_speed")
        private int windSpeed;
        private int feelslike ;

    }






}
