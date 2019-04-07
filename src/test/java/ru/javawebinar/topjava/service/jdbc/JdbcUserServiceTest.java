package ru.javawebinar.topjava.service.jdbc;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static ru.javawebinar.topjava.Profiles.JDBC;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {

    @Test
    public void deleteAdmin() {
        service.delete(ADMIN_ID);
        assertMatch(service.getAll(),USER);
    }

    @Test
    public void getAdmin(){
        User admin = service.get(ADMIN_ID);
        assertMatch(admin,ADMIN);
    }

    @Test
    public void getAdminByEmail(){
        User admin = service.getByEmail(ADMIN.getEmail());
        assertMatch(admin,ADMIN);
    }

    @Test
    public void updateRole(){
        User user = new User(USER);
        user.getRoles().add(Role.ROLE_ADMIN);
        service.update(user);
        assertMatch(service.get(user.getId()),user);
    }

    @Test
    public void createAdmin(){
        User newUser = new User(null, "New", "new@gmail.com", "newPass", 1555, false, new Date(),
                Arrays.asList(Role.ROLE_USER,Role.ROLE_ADMIN));
        User user = service.create(newUser);
        assertMatch(service.get(user.getId()),user);

    }
}