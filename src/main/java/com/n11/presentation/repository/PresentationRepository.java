package com.n11.presentation.repository;

import com.n11.presentation.entity.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Emre on 20.04.2018.
 */
public interface PresentationRepository extends JpaRepository<Presentation, Long> {
}
