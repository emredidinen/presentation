package com.n11.presentation.dto;

import com.n11.presentation.entity.Presentation;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Emre on 20.04.2018.
 */
@Data
@NoArgsConstructor
public class PresentationDTO {

    @Value("${lightning.time}")
    private Integer lightningTime;

    private Long id;

    private String name;

    private Integer duration;

    private Boolean lightning;

    public PresentationDTO(String name) {
        this.name = name;
    }

    public PresentationDTO(Presentation presentation) {
        this.duration = presentation.getDuration();
        this.lightning = presentation.getLightning();
        this.name = presentation.getName();
        this.id = presentation.getId();
    }

}
