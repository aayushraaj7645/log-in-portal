package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.apiResponse.WeatherResponse;
import net.engineeringdigest.journalApp.cacheApp.CacheApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CacheApp cacheApp;

    @Value("${weather.api.key}")
    private  String apiKey ;










public  WeatherResponse getWeather(String city){
      String finalUrl =  cacheApp.appCache.replace("CITY",city).replace("api",apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalUrl, HttpMethod.GET,
                null, WeatherResponse.class);
        return response.getBody();

    }



}
