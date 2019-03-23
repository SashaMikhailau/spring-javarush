package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles("datajpa")
public class DataJpaMealServiceTest extends MealServiceTest {

    @Test
    public void getWithUser(){
        Meal withUser = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        assertMatch(withUser, ADMIN_MEAL1);
        assertMatch(withUser.getUser(),ADMIN);
    }

}
