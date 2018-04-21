package com.n11.presentation.service;

import com.n11.presentation.dto.PresentationDTO;
import com.n11.presentation.dto.Schedule;

import java.util.Collection;
import java.util.List;

/**
 * Created by Emre on 20.04.2018.
 */
public interface PresentationService {
    PresentationDTO findById(Long id);

    void delete(Long id);

    void update(PresentationDTO dto);

    void save(PresentationDTO dto);

    List<PresentationDTO> getAll();

    void addDefaultData();

    Schedule getSchedule();
}
