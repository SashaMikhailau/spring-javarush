package ru.javawebinar.topjava;

import org.assertj.core.api.Assertions;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MealTestData {
    public static final LocalDateTime UNIQUE_DATE_TIME = LocalDateTime.of(2019, 02, 26, 10, 0);
    public static final LocalDateTime TEST_START_DATETIME = LocalDateTime.of(2019, 02, 26, 14, 0);
    public static final LocalDateTime TEST_END_DATETIME = LocalDateTime.of(2019, 02, 27, 12, 0);
    public static final LocalDate TEST_START_DATE = LocalDate.of(2019, 02, 27);
    public static final LocalDate TEST_END_DATE = LocalDate.of(2019, 02, 27);

    private static final List<Meal> USER_MEALS = new ArrayList<>();
    private static final List<Meal> ADMIN_MEALS = new ArrayList<>();
    private static final Meal TEST_MEAL = new Meal(LocalDateTime.of(2019, 02, 28, 19, 0),
            "VeryLate", 1000);

    static {
        USER_MEALS.add(new Meal(2, UNIQUE_DATE_TIME, "Завтрак", 500));
        USER_MEALS.add(new Meal(3, LocalDateTime.of(2019, 02, 26, 13, 0), "Обед", 400));
        USER_MEALS.add(new Meal(4, LocalDateTime.of(2019, 02, 26, 19, 0), "Ужин", 1100));
        USER_MEALS.add(new Meal(5, LocalDateTime.of(2019, 02, 27, 10, 0), "Завтрак", 500));
        USER_MEALS.add(new Meal(6, LocalDateTime.of(2019, 02, 27, 13, 0), "Обед", 500));
        USER_MEALS.add(new Meal(7, LocalDateTime.of(2019, 02, 27, 19, 0), "Ужин", 1100));
        ADMIN_MEALS.add(new Meal(8, LocalDateTime.of(2019, 02, 25, 10, 0), "Завтрак", 1000));
        ADMIN_MEALS.add(new Meal(9, LocalDateTime.of(2019, 02, 25, 13, 0), "Обед", 1000));

    }

    public static List<Meal> getUserMeals() {
        return new ArrayList<>(USER_MEALS);
    }

    public static List<Meal> getAdminMeals() {
        return new ArrayList<>(ADMIN_MEALS);
    }

    public static Meal getTestMeal() {
        return new Meal(TEST_MEAL.getDateTime(), TEST_MEAL.getDescription(), TEST_MEAL.getCalories());
    }

    public static void assertMatch(List<Meal> meals, List<Meal> expected) {
        Assertions.assertThat(meals).usingFieldByFieldElementComparator().isEqualTo(expected
                .stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList()));
    }

    public static void assertMatch(Meal current, Meal expected) {
        Assertions.assertThat(current).isEqualToComparingFieldByField(expected);
    }
}
