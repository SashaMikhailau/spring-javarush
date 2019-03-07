package ru.javawebinar.topjava.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;
    @Transactional
    @Override
    public Meal save(Meal meal, int userId) {

        meal.setUser(new User());
        if (meal.isNew()) {
            meal.setUser(userRepository.get(userId));
            entityManager.persist(meal);
            return meal;
        }
        int i = entityManager
                .createNamedQuery(Meal.UPDATE)
                .setParameter("calories", meal.getCalories())
                .setParameter("datetime", meal.getDateTime())
                .setParameter("description", meal.getDescription())
                .setParameter("id", meal.getId())
                .setParameter("user_id", userId).executeUpdate();
        return meal;


    }
    @Transactional
    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meals = entityManager.createNamedQuery(Meal.GET, Meal.class)
                .setParameter("id", id)
                .setParameter("user_id", userId)
                .getResultList();

        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
       return entityManager.createNamedQuery(Meal.GET_ALL,Meal.class)
                .setParameter("user_id",userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}