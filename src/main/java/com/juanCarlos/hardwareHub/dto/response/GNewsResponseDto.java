package com.juanCarlos.hardwareHub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO INTERNO — deserializa la respuesta cruda de la API de GNews.
 * No debe salir nunca del servicio. El front no conoce esta clase.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GNewsResponseDto {

    private List<ArticleDto> articles;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleDto {

        private String title;
        private String description;
        private String url;
        private String image;
        private String publishedAt;
        private SourceDto source;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SourceDto {

        private String name;
        private String url;
    }
}

