package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = "Meal.UPDATE", query = "UPDATE Meal m SET m.calories = :calories, m" +
                ".dateTime " +
                "=:datetime , m.description = :description WHERE m.id = :id AND m.user" +
                ".id=:user_id"),
        @NamedQuery(name = "Meal.GET_ALL", query = "SELECT m FROM Meal m WHERE m.user.id=:user_id ORDER BY" +
                " m.dateTime DESC"),
        @NamedQuery(name = "Meal.GET", query = "SELECT m FROM Meal m WHERE m.id = :id AND m.user.id = " +
                ":user_id"),
        @NamedQuery(name = "Meal.DELETE", query = "DELETE FROM Meal m WHERE m.id = :id AND m.user.id = " +
                ":user_id"),
        @NamedQuery(name = "Meal.GET_ALL_BETWEEN", query = "SELECT m FROM Meal m WHERE m.user.id = :user_id AND m.dateTime " +
                "BETWEEN :startdate AND :enddate ORDER BY m.dateTime DESC")})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(name = "datetime_user_idx",
        columnNames = {"date_time", "user_id"})})
public class Meal extends AbstractBaseEntity {
    public static final String UPDATE = "Meal.UPDATE";
    public static final String GET_ALL = "Meal.GET_ALL";
    public static final String GET = "Meal.GET";
    public static final String GET_ALL_BETWEEN = "Meal.GET_ALL_BETWEEN";
    public static final String DELETE = "Meal.DELETE";
    @NotNull
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @NotBlank
    @Size(max = 100)
    @Column(name = "description")
    private String description;

    @Range(min = 0, max = 2000)
    @Column(name = "calories")
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
