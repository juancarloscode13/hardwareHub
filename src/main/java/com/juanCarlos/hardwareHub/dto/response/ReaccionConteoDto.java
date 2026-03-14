package com.juanCarlos.hardwareHub.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Conteo de reacciones agrupadas por tipo para una publicación")
public class ReaccionConteoDto {

    @Schema(description = "ID de la publicación", example = "1")
    private Long publicacionId;

    @Schema(description = "Número de LIKE", example = "42")
    private int likesCount;

    @Schema(description = "Número de DISLIKE", example = "3")
    private int dislikesCount;

    @Schema(description = "Número de LOVE", example = "10")
    private int loveCount;

    @Schema(description = "Número de FUNNY", example = "5")
    private int funnyCount;

    @Schema(description = "Número de INTERESTING", example = "7")
    private int interestingCount;
}

