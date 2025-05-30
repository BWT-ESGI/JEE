package jee.spring.esgi.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Component
public class VelibStationApiClient {
    @Value("${velib.api.url}")
    private String velibApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @SuppressWarnings("unchecked")
    public Set<String> getTownsWithStations(List<String> towns) {
        Map<String, Object> body = Map.of("towns", towns);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                velibApiUrl + "/stations/velibs",
                request,
                Map.class
        );
        List<Map<String, Object>> stations = (List<Map<String, Object>>) response.getBody().get("stations");
        Set<String> townsWithStations = new HashSet<>();
        if (stations != null) {
            for (Map<String, Object> s : stations) {
                String town = (String) s.get("nomCommune");
                if (town != null) townsWithStations.add(town);
            }
        }
        return townsWithStations;
    }
}
