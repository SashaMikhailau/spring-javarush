package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkUserId;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> this.save(1, meal));
    }


    @Override
    public Meal save(Integer userId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage

        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> {
            if (checkUserId(userId, oldMeal)) {
                return meal;
            } else {
                return null;
            }
        });
    }

    @Override
    public boolean delete(Integer userId, int id) {
        if (checkUserId(userId, repository.get(id))) {
            Meal removed = repository.remove(id);
            return Objects.nonNull(removed);
        }
        return false;
    }

    @Override
    public Meal get(Integer userId, int id) {
        Meal meal = repository.get(id);
        if (Objects.isNull(meal) || !checkUserId(userId, meal)) {
            return null;
        }
        return meal;
    }



    @Override
    public Collection<Meal> getAll(Integer userId) {
        return repository.values().stream()
                .filter(meal ->
                        checkUserId(userId, meal))
                .sorted(Comparator.comparing(meal->((Meal)meal).getDateTime()).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getAllByDate(Integer userId, LocalDate startDate, LocalDate endDate) {
        return getAll(userId).stream()
                .filter(meal ->
                        DateTimeUtil.isBetween(meal.getDateTime().toLocalDate(), startDate, endDate))
                .collect(Collectors.toList());

    }
}

