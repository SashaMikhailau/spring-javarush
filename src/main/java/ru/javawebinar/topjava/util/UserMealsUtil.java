package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,00), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        Random rand = new Random();
        List<UserMeal> userMeals = Stream.generate(() ->
                mealList.get(rand.nextInt(mealList.size())))
                .limit(1000).collect(Collectors.toList());
        List<UserMeal> userMeals2 = Stream.generate(() ->
                mealList.get(rand.nextInt(mealList.size())))
                .limit(1000000).collect(Collectors.toList());
        System.out.println(getFilteredWithExceeded(mealList, LocalTime.of(7,0),LocalTime.of(13,0), 2000));
        //getFilteredWithExceeded(userMeals, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        //getFilteredWithExceeded(userMeals2, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);*/

//        .toLocalDate();
//        .toLocalTime();
    }


    /**
     * @param mealList
     * @param startTime - start time to filter meals
     * @param endTime - endtime to filter meals
     * @param caloriesPerDay
     * @return list of meals filtered to be between (inclusive) start and endtime. Meals are mapped to contains parameter 'exceed',
     * which indicates if it was exceed by calories in day, when this meal occured.
     *
     *
     */
    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        /*Checks for null*/
        final LocalTime innerStartTime = startTime == null ? LocalTime.of(00, 00) : startTime;
        final LocalTime innerEndTime = endTime == null ? LocalTime.of(23, 59) : endTime;
        if(mealList==null){
            mealList = Collections.emptyList();
        }
        long start = System.nanoTime();
        Map<LocalDate, Integer> statisticsMap = mealList.stream()
                .collect(
                        Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));

        List<UserMealWithExceed> result = mealList
                .stream()
                .filter(userMeal -> {
            LocalTime time = userMeal.getDateTime().toLocalTime();
            return TimeUtil.isBetween(time,innerStartTime,innerEndTime);
        })
                .map(meal -> {
            boolean exceed = statisticsMap.get(meal.getDateTime().toLocalDate()) > caloriesPerDay;
            return new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed);
        })
                .collect(Collectors.toList());
        long end = System.nanoTime();
        //System.out.println(String.format("Времени затрачено на %d элементов %d нано",mealList.size(),end-start));
        return result;
    }
}
