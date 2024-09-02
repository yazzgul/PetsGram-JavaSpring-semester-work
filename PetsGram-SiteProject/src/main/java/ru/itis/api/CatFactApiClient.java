package ru.itis.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.itis.dto.CatFactDto;

@RequiredArgsConstructor
@Component
@Slf4j
public class CatFactApiClient {
    @Value(value = "${catfact.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public CatFactDto getRandomCatFact() {
        log.warn("Cat Fact: {}", restTemplate.getForObject(apiUrl, CatFactDto.class));
        return restTemplate.getForObject(apiUrl, CatFactDto.class);
    }

}
