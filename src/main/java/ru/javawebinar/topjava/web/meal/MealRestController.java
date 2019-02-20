package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

public class MealRestController {
    private MealService service;

    List<MealTo> getAll() {
        return service.getAll(authUserId());
    }

    List<MealTo> getAllByDateTime(LocalDate startdate, LocalTime startTIme, LocalDate endDate,
                                  LocalTime endTime) {
        return service.getAllByDateTime(authUserId(),
                Objects.isNull(startdate) ? LocalDate.MIN : startdate,
                Objects.isNull(startTIme) ? LocalTime.MIN : startTIme,
                Objects.isNull(endDate) ? LocalDate.MAX : endDate,
                Objects.isNull(endTime) ? LocalTime.MAX : endTime);
    }

    MealTo getById(Integer mealId) {
        return service.getById(authUserId(), mealId);
    }

    void update(MealTo mealTo) {
        service.update(authUserId(), mealTo);
    }

    void delete(Integer mealId) {
        service.delete(authUserId(), mealId);
    }


}