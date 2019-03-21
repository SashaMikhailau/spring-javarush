package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int ADMIN_EMPTY_ID = START_SEQ + 2;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password",
            Role.ROLE_USER,Collections.emptyList());
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin",
            Role.ROLE_ADMIN, Arrays.asList(MealTestData.ADMIN_MEAL1, MealTestData.ADMIN_MEAL2));

    public static final User ADMIN_EMPTY = new User(ADMIN_EMPTY_ID, "AdminEmpty", "adminempty@yandex.ru", "empty",
            Role.ROLE_ADMIN, Collections.emptyList());

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles","meals");
    }
    public static void assertMatchWithMeals(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles","meals").isEqualTo(expected);
    }
}
