package com.nearcodeconsulting.exercise.domain.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "car")
public class Car extends AbstractModel {

    @ManyToOne
    private User user;

    @NotBlank
    @Size(max = 255)
    private String brand;

    @NotBlank
    @Size(max = 255)
    private String model;

    @NotBlank
    @Pattern(regexp="^(([A-Z]{2}-\\d{2}-(\\d{2}|[A-Z]{2}))|(\\d{2}-(\\d{2}-[A-Z]{2}|[A-Z]{2}-\\d{2})))$",
            message ="Insert a valid license plate format: AA-00-00, 00-00-AA, 00-AA-00 or AA-00-AA")
    @Size(min = 8, max = 8)
    private String licensePlate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

}
