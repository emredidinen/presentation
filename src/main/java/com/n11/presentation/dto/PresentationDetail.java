package com.n11.presentation.dto;

import lombok.Data;

/**
 * Created by Emre on 21.04.2018.
 */
@Data
public class PresentationDetail {
    private PresentationDTO presentationDTO;
    private String beginningTime;
    private String duration;
}
