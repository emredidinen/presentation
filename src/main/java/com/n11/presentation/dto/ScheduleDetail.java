package com.n11.presentation.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by Emre on 21.04.2018.
 */
@Data
public class ScheduleDetail {
    private List<PresentationDetail> presentationDetailList;
    private int totalDuration;
    private int restOfTime;
    private int lastPresentationTime;

    public ScheduleDetail(List<PresentationDetail> presentationDetailList, int totalDuration, int lastPresentationTime) {
        this.presentationDetailList = presentationDetailList;
        this.totalDuration = totalDuration;
        this.lastPresentationTime = lastPresentationTime;
        this.restOfTime = totalDuration;
    }
}
