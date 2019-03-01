package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.Util;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({"/spring/spring-app.xml", "/spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "/db/populateDB.sql",config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    private MealService mealService;

    @Autowired
    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotExistingUserId() {
        mealService.delete(2, INVALID_ID);
    }

    @Test
    public void deleteRightUserId() {
        int testId = 2;
        mealService.delete(testId,USER_ID);
        List<Meal> all = mealService.getAll(USER_ID);
        List<Meal> expected = getUserMeals();
        expected.stream().filter(meal->meal.getId()==testId).findFirst().ifPresent(expected::remove);
        MealTestData.assertMatch(all,expected);
    }

    @Test(expected = NotFoundException.class)
    public void deleteAnotherUserId() {
        mealService.delete(2, ADMIN_ID);

    }

    @Test
    public void getAllNotExisitngId(){
        List<Meal> mealList = mealService.getAll(INVALID_ID);
        MealTestData.assertMatch(mealList, Collections.emptyList());
    }

    @Test
    public void getAllExistingId() {
        List<Meal> meals = mealService.getAll(USER_ID);
        MealTestData.assertMatch(meals, getUserMeals());
        meals = mealService.getAll(ADMIN_ID);
        MealTestData.assertMatch(meals, MealTestData.getAdminMeals());
    }

    @Test(expected = NotFoundException.class)
    public void getAnotherMeal() {
        mealService.get(2, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getNotExistingUser() {
        mealService.get(2, INVALID_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getWrongMeal() {
        mealService.get(1000, USER_ID);
    }

    @Test
    public void getRightMeal() {
        int testId = 2;
        Meal currentMeal = mealService.get(testId, USER_ID);
        List<Meal> expected = getUserMeals();
        Meal expectedMeal = expected.stream().filter(meal -> meal.getId() == testId).findFirst().orElse(null);
        MealTestData.assertMatch(currentMeal,expectedMeal);
    }

    @Test
    public void createMeal() {
        Meal createdMeal = new Meal(LocalDateTime.of(2019, 02, 28, 21, 0), "Late Supper", 2000);
        Meal insertedMeal = mealService.create(createdMeal, USER_ID);
        createdMeal.setId(insertedMeal.getId());
        List<Meal> testMeals = getUserMeals();
        testMeals.add(createdMeal);
        MealTestData.assertMatch(mealService.getAll(USER_ID),testMeals);
    }

    @Test(expected = DataAccessException.class)
    public void createMealWithDuplicateDateTime() {
        Meal createdMeal = new Meal(UNIQUE_DATE_TIME, "Late Supper", 2000);
        Meal insertedMeal = mealService.create(createdMeal, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateAnotherUserMeal() {
        Meal testMeal = getTestMeal();
        testMeal.setId(2);
        mealService.update(testMeal,ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotExistingMeal() {
        Meal testMeal = getTestMeal();
        testMeal.setId(100);
        mealService.update(testMeal,USER_ID);
    }

    @Test(expected = DataAccessException.class)
    public void updateMealWithDuplicateDateTime() {
        Meal testMeal= getTestMeal();
        testMeal.setId(3);
        testMeal.setDateTime(UNIQUE_DATE_TIME);
        Meal insertedMeal = mealService.create(testMeal, USER_ID);
    }

    @Test
    public void update(){
        int testId = 2;
        Meal testMeal = getTestMeal();
        testMeal.setId(testId);
        mealService.update(testMeal,USER_ID);
        List<Meal> testMeals = getUserMeals();
        for (int i = 0; i < testMeals.size(); i++) {
            if (testMeals.get(i).getId() == testId) {
                testMeals.set(i, testMeal);
                break;
            }
        }
        testMeals.sort(Comparator.comparing(Meal::getDateTime).reversed());
        assertMatch(mealService.getAll(USER_ID),testMeals);
    }

    @Test
    public void getBetweenDateTimeNotExistingUser() {
        List<Meal> meals = mealService.getBetweenDateTimes(TEST_START_DATETIME, TEST_END_DATETIME, INVALID_ID);
        assertMatch(meals,Collections.emptyList());
    }

    @Test
    public void getBetweenDateTime() {
        List<Meal> meals = mealService.getBetweenDateTimes(TEST_START_DATETIME, TEST_END_DATETIME
                , USER_ID);
        List<Meal> expected = getUserMeals().stream().filter(meal -> Util.isBetween(meal.getDateTime(), TEST_START_DATETIME,
                TEST_END_DATETIME)).collect(Collectors.toList());
        assertMatch(meals,expected);
    }
    @Test
    public void getBetweenDates() {
        List<Meal> meals = mealService.getBetweenDates(TEST_START_DATE, TEST_END_DATE,USER_ID);
        List<Meal> expected = getUserMeals().stream().filter(meal -> Util.isBetween(meal.getDate(), TEST_START_DATE,
                TEST_END_DATE)).collect(Collectors.toList());
        assertMatch(meals,expected);
    }

}