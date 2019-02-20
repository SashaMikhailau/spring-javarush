package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    private MealRepository repository;

    @Override
    public List<MealTo> getAll(Integer userId,int calories) {
        return MealsUtil.getFilteredWithExcess(repository.getAll(userId),
               calories, LocalTime.MIN,LocalTime.MAX);
    }

    @Override
    public List<MealTo> getAllByDateTime(Integer userId, LocalDate startDate, LocalTime startTime
            , LocalDate endDate, LocalTime endTime,int calories) {
        return MealsUtil.getFilteredWithExcess(repository.getAllByDate(userId, startDate, endDate),
               calories, startTime, endTime);
    }

    @Override
    public Meal getById(Integer userId, Integer mealId) {
        return repository.get(userId, mealId);
    }

    @Override
    public void delete(Integer userId, Integer mealId) {
        repository.delete(userId, mealId);
    }

    @Override
    public void update(Integer userId, Meal meal) {
        repository.save(userId, meal);

    }
}