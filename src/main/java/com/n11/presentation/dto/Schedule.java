package com.n11.presentation.dto;

import lombok.Data;

import java.util.ArrayList;

/**
 * Created by Emre on 21.04.2018.
 */
@Data
public class Schedule {
    private ScheduleDetail firstMorning;
    private ScheduleDetail firstNoon;
    private ScheduleDetail secondMorning;
    private ScheduleDetail secondNoon;

    public Schedule() {
        firstMorning = new ScheduleDetail(new ArrayList<>(), 180, 540);
        firstNoon = new ScheduleDetail(new ArrayList<>(), 240, 780);
        secondMorning = new ScheduleDetail(new ArrayList<>(), 180, 540);
        secondNoon = new ScheduleDetail(new ArrayList<>(), 240, 780);
    }
}
