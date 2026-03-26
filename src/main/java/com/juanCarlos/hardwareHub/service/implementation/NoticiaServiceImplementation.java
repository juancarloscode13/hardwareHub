package com.juanCarlos.hardwareHub.service.implementation;

import com.juanCarlos.hardwareHub.dto.response.GNewsResponseDto;
import com.juanCarlos.hardwareHub.dto.response.NoticiaResponseDto;
import com.juanCarlos.hardwareHub.service.NoticiaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio encargado de consultar la API de GNews y devolver noticias tecnológicas
 * de la última semana en el formato que necesita el front.
 *
 * El caché evita llamadas repetidas a GNews dentro de la misma ventana de 30 minutos,
 * protegiendo el límite de peticiones del plan gratuito.
 */
@Service
@CacheConfig(cacheNames = "noticias")
public class NoticiaServiceImplementation implements NoticiaService {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKey;
    private final String category;
    private final String lang;

    public NoticiaServiceImplementation(
            RestTemplate restTemplate,
            @Value("${gnews.api.base-url}") String baseUrl,
            @Value("${gnews.api.key}") String apiKey,
            @Value("${gnews.api.category}") String category,
            @Value("${gnews.api.lang}") String lang) {

        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.category = category;
        this.lang = lang;
    }

    /**
     * Obtiene los titulares tecnológicos de la última semana desde GNews.
     * El resultado se almacena en caché: la primera llamada real a GNews se produce
     * una vez cada vez que el caché expira.
     */
    @Override
    @Cacheable
    public List<NoticiaResponseDto> getNoticias() {
        String from = LocalDateTime.now()
                .minusWeeks(1)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z";

        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("category", category)
                .queryParam("lang", lang)
                .queryParam("from", from)
                .queryParam("apikey", apiKey)
                .toUriString();

        GNewsResponseDto respuesta = restTemplate.getForObject(url, GNewsResponseDto.class);

        if (respuesta == null || respuesta.getArticles() == null) {
            return List.of();
        }

        return respuesta.getArticles().stream()
                .map(this::mapearArticulo)
                .collect(Collectors.toList());
    }

    /**
     * Mapea un artículo del DTO interno de GNews al DTO público de salida.
     */
    private NoticiaResponseDto mapearArticulo(GNewsResponseDto.ArticleDto article) {
        return new NoticiaResponseDto(
                article.getTitle(),
                article.getDescription(),
                article.getUrl(),
                article.getImage(),
                article.getPublishedAt(),
                article.getSource() != null ? article.getSource().getName() : null
        );
    }
}


