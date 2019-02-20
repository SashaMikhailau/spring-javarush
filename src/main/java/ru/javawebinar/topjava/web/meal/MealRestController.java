package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {

    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        return service.getAll(authUserId(),authUserCaloriesPerDay());
    }

    public List<MealTo> getAllByDateTime(String startDateText, String startTimeText,
                                         String endDateText,
                                  String endTimeText) {
        LocalDate startDate = startDateText.isEmpty() ? LocalDate.MIN : LocalDate.parse(startDateText);
        LocalDate endDate = endDateText.isEmpty() ? LocalDate.MAX : LocalDate.parse(endDateText);
        LocalTime startTime = startTimeText.isEmpty() ? LocalTime.MIN: LocalTime.parse(startTimeText);
        LocalTime endTime= endTimeText.isEmpty() ? LocalTime.MAX : LocalTime.parse(endTimeText);
        return service.getAllByDateTime(authUserId(),
                startDate,
                startTime,
                endDate,
                endTime,
                authUserCaloriesPerDay());
    }

    public Meal get(Integer mealId) {
        return service.getById(authUserId(), mealId);
    }

    public void update(Meal meal) {
        service.update(authUserId(), meal);
    }

    public void delete(Integer mealId) {
        service.delete(authUserId(), mealId);
    }


}