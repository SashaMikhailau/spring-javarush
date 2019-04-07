package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserExtractor implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer,User> users = new HashMap<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            users.putIfAbsent(id, new User(id, rs.getString("name"), rs.getString("email"),
                    rs.getString("password"), rs.getInt("calories_per_day"), rs.getBoolean("enabled")
                    , rs.getDate("registered"), Collections.emptySet()));
            User user = users.get(id);
            String role = rs.getString("role");
            if (role != null && !role.isEmpty()) {
                user.getRoles().add(Role.valueOf(role));
            }
        }
        List<User> unsorted = new ArrayList<>(users.values());
        unsorted.sort(Comparator.comparing(User::getName).thenComparing(User::getEmail));
        return unsorted;
    }
}
