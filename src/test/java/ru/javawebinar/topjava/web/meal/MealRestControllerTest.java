package ru.javawebinar.topjava.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.web.meal.MealRestController.*;

class MealRestControllerTest extends AbstractControllerTest {
    @Autowired
    MealService mealService;
    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_MEALS + "/" + MEAL1_ID))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertJson(MEAL1));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_MEALS + "/" + MEAL1_ID))
                .andExpect(status().isNoContent());
                assertMatch(mealService.getAll(UserTestData.USER_ID),MEAL6, MEAL5, MEAL4,
                        MEAL3, MEAL2);
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_MEALS))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertJson(MealsUtil.getWithExcess(MEALS,
                        SecurityUtil.authUserCaloriesPerDay())));
    }

    @Test
    void create() throws Exception {
        Meal created = getCreated();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(REST_MEALS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated());
        Meal returned = TestUtil.readFromJson(resultActions,Meal.class);
        created.setId(returned.getId());
        assertMatch(returned,created);
        assertMatch(mealService.getAll(UserTestData.USER_ID), created, MEAL6, MEAL5, MEAL4, MEAL3,
                MEAL2, MEAL1);
    }

    @Test
    void update() throws Exception {
        Meal expected = MealTestData.getUpdated();
        mockMvc.perform(MockMvcRequestBuilders
                .put(REST_MEALS + "/" + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))).andExpect(status().isNoContent());
        Meal actual = mealService.get(expected.getId(), UserTestData.USER_ID);
        assertMatch(actual,expected);
    }

    @Test
    void getBetween() throws Exception {
        List<MealTo> expected =
                MealsUtil.getFilteredWithExcess(mealService.getBetweenDates(
                        START_DATE_TIME.toLocalDate(), END_DATE_TIME.toLocalDate(), UserTestData.USER_ID)
                        , SecurityUtil.authUserCaloriesPerDay(), START_DATE_TIME.toLocalTime(),
                        END_DATE_TIME.toLocalTime());
        mockMvc.perform(MockMvcRequestBuilders.post(REST_MEALS+"/filter")
        .param(START_DATE_PARAM, DateTimeFormatter.ISO_DATE.format(START_DATE_TIME.toLocalDate()))
        .param(START_TIME_PARAM,DateTimeFormatter.ISO_TIME.format(END_DATE_TIME.toLocalTime()))
        .param(END_DATE_PARAM,DateTimeFormatter.ISO_DATE.format(END_DATE_TIME.toLocalDate()))
        .param(END_TIME_PARAM,DateTimeFormatter.ISO_TIME.format(END_DATE_TIME.toLocalTime())))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertJson(expected));
    }

}