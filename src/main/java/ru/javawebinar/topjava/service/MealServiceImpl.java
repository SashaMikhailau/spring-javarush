package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MealTo> getAll(Integer userId) {
        return null;
    }

    @Override
    public List<MealTo> getAll(Integer userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return null;
    }

    @Override
    public void delete(Integer userId, Integer mealId) {

    }

    @Override
    public MealTo get(Integer userId, Integer mealId) {
        return null;
    }

    @Override
    public void update(Integer userId, MealTo mealId) {

    }
}