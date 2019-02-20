package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        return service.getAll(authUserId());
    }

    public List<MealTo> getAll(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {

        return service.getAll(authUserId(),
                Objects.isNull(startDate) ? LocalDate.MIN : startDate,
                Objects.isNull(startTime) ? LocalTime.MIN : startTime,
                Objects.isNull(endDate) ? LocalDate.MAX : endDate,
                Objects.isNull(endTime) ? LocalTime.MAX : endTime
        );
    }


    public void delete(Integer id) {
        service.delete(authUserId(), id);

    }

    public MealTo get(Integer id) {
        return service.get(authUserId(), id);
    }

    public void update(MealTo meal) {
        service.update(authUserId(), meal);
    }


}