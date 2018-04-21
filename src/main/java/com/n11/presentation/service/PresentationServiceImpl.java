package com.n11.presentation.service;

import com.n11.presentation.dto.PresentationDTO;
import com.n11.presentation.dto.PresentationDetail;
import com.n11.presentation.dto.Schedule;
import com.n11.presentation.dto.ScheduleDetail;
import com.n11.presentation.entity.Presentation;
import com.n11.presentation.enums.Exception;
import com.n11.presentation.repository.PresentationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Emre on 20.04.2018.
 */

@Service
public class PresentationServiceImpl implements PresentationService {


    @Value("${lightning.time}")
    private Integer lightningTime;

    private final PresentationRepository presentationRepository;

    public PresentationServiceImpl(PresentationRepository presentationRepository) {
        this.presentationRepository = presentationRepository;
    }

    @Override
    public PresentationDTO findById(Long id) {
        return new PresentationDTO(getById(id));
    }

    @Override
    public void delete(Long id) {
        presentationRepository.delete(getById(id));
    }

    @Override
    public void update(PresentationDTO dto) {
        saveOrUpdate(dto);
    }

    @Override
    public void save(PresentationDTO dto) {
        saveOrUpdate(dto);
    }

    @Override
    public List<PresentationDTO> getAll() {
        return presentationRepository.findAll().stream()
                .map(PresentationDTO::new).collect(Collectors.toList());
    }

    private void saveOrUpdate(PresentationDTO dto) {
        Presentation presentation = new Presentation(dto);
        if (dto.getLightning())
            presentation.setDuration(lightningTime);
        presentationRepository.save(presentation);
    }

    private Presentation getById(Long id) {
        return Optional.of(presentationRepository.getOne(id)).orElseThrow(() ->
                new RuntimeException(Exception.DATA_NOT_FOUND.getType()));
    }

    //data samples
    @Override
    public void addDefaultData() {
        presentationRepository.deleteAll();
        Arrays.asList(
                new Presentation("Architecting Your Codebase", 60, false),
                new Presentation("Overdoing it in Python", 45, false),
                new Presentation("Flavors of Concurrency in Java", 30, false),
                new Presentation("Ruby Errors from Mismatched Gem Versions", 45, false),
                new Presentation("JUnit 5 - Shaping the Future of Testing on the JVM", 45, false),
                new Presentation("Cloud Native Java lightning", lightningTime, true),
                new Presentation("Communicating Over Distance", 60, false),
                new Presentation("AWS Technical Essentials", 45, false),
                new Presentation("Continuous Delivery", 30, false),
                new Presentation("Monitoring Reactive Applications", 30, false),
                new Presentation("Pair Programming vs Noise", 45, false),
                new Presentation("Rails Magic", 60, false),
                new Presentation("Microservices \"Just Right\"", 60, false),
                new Presentation("Clojure Ate Scala (on my project)", 45, false),
                new Presentation("Perfect Scalability", 30, false),
                new Presentation("Apache Spark", 30, false),
                new Presentation("Async Testing on JVM", 60, false),
                new Presentation("A World Without HackerNews", 30, false),
                new Presentation("User Interface CSS in Apps", 30, false)
        ).forEach(presentation -> presentationRepository.save(presentation));
    }

    //to prepare most suitable schedule
    @Override
    public Schedule getSchedule() {
        List<PresentationDTO> presentationList = getAll();
        Schedule schedule = new Schedule();
        scheduleAccordingToTime(presentationList, schedule.getFirstMorning());
        scheduleAccordingToTime(presentationList, schedule.getSecondMorning());
        scheduleAccordingToTime(presentationList, schedule.getFirstNoon());
        scheduleAccordingToTime(presentationList, schedule.getSecondNoon());

        addNetworkingIfPossible(schedule.getFirstNoon());
        addNetworkingIfPossible(schedule.getSecondNoon());
        return schedule;
    }
    //To check if exist enough time for Networking thn put it after 16 : 00
    private void addNetworkingIfPossible(ScheduleDetail scheduleDetail) {
        if (scheduleDetail.getLastPresentationTime() < 16 * 60) {
            scheduleDetail.getPresentationDetailList().add(createNetworking(16 * 60));
        } else if (scheduleDetail.getRestOfTime() > 0) {
            scheduleDetail.getPresentationDetailList().add(createNetworking(scheduleDetail.getLastPresentationTime()));
        }
    }

    private PresentationDetail createNetworking(int beginningTime) {
        PresentationDetail presentationDetail = new PresentationDetail();
        presentationDetail.setPresentationDTO(new PresentationDTO("Networking Event"));
        presentationDetail.setBeginningTime(getStringTime(beginningTime));
        return presentationDetail;
    }

    private void scheduleAccordingToTime(List<PresentationDTO> restOfThem, ScheduleDetail scheduleDetail) {
        PresentationDTO dto = null;
        do {
            dto = getMostSuitablePresentation(restOfThem, scheduleDetail);
            if (dto == null)
                return;
            PresentationDetail presentationDetail = new PresentationDetail();
            presentationDetail.setPresentationDTO(dto);
            presentationDetail.setBeginningTime(getStringTime(scheduleDetail.getLastPresentationTime()));
            presentationDetail.setDuration(dto.getDuration() + " min");
            scheduleDetail.setLastPresentationTime(scheduleDetail.getLastPresentationTime() + dto.getDuration());
            scheduleDetail.getPresentationDetailList().add(presentationDetail);
        } while (true);

    }


    private String getStringTime(int time) {
        return new StringBuilder()
                .append(time / 60)
                .append(" : ")
                .append((time % 60) == 0 ? "00" : (time % 60)).toString();
    }

    //it's give you most suitable presentation according to rest of time.
    private PresentationDTO getMostSuitablePresentation(List<PresentationDTO> restOfThem, ScheduleDetail scheduleDetail) {
        Optional<PresentationDTO> presentationDTOOptional = restOfThem.stream()
                .filter(dto -> dto.getDuration().compareTo(scheduleDetail.getRestOfTime()) < 1)//eliminated higher than rest of time
                .sorted(Comparator.comparing(PresentationDTO::getDuration).reversed())//sorted reversely
                .findFirst();//picked up the highest one
        if (!presentationDTOOptional.isPresent())
            return null;
        PresentationDTO presentationDTO = presentationDTOOptional.get();
        restOfThem.remove(presentationDTO);//when you find it, it is removed from general list.
        scheduleDetail.setRestOfTime(scheduleDetail.getRestOfTime() - presentationDTO.getDuration());
        return presentationDTO;
    }


}
