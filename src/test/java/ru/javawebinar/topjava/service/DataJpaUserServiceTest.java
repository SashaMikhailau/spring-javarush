package ru.javawebinar.topjava.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;

@ActiveProfiles("datajpa")
public class DataJpaUserServiceTest extends UserServiceTest {

    @Test
    public void getWithMeals(){
        User withMeals = service.getWithMeals(UserTestData.USER_ID);
        UserTestData.assertMatch(withMeals,UserTestData.USER);
        MealTestData.assertMatch(withMeals.getMeals(), MealTestData.MEALS);
    }

    @Test
    public void getEmptyAdmin(){
        User withMeals = service.getWithMeals(UserTestData.ADMIN_EMPTY_ID);
        UserTestData.assertMatch(withMeals,UserTestData.ADMIN_EMPTY);
        log.info("Size of meals = "+withMeals.getMeals().size());
        Assertions.assertThat(withMeals.getMeals()).isEmpty();

    }


}
