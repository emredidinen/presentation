package com.n11.presentation.entity;

import com.n11.presentation.dto.PresentationDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Created by Emre on 20.04.2018.
 */
@Entity
@Data
@NoArgsConstructor
public class Presentation {

    @Transient
    @Value("${lightning.time}")
    private Integer lightningTime;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer duration;

    private Boolean lightning;

    public Presentation(PresentationDTO presentationDTO) {
        this.id = presentationDTO.getId();
        this.name = presentationDTO.getName();
        this.lightning = presentationDTO.getLightning();
        this.duration = presentationDTO.getDuration();
    }

    public Presentation(String name, Integer duration,Boolean lightning) {
        this.name = name;
        this.duration = duration;
        this.lightning = lightning;
    }
}
