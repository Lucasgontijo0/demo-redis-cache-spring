package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "title.mandatory")
    private String title;

    @NotNull(message = "year.mandatory")
    @Min(message = "year.minValue", value = 1888)
    private Integer year;

    @NotBlank(message = "language.mandatory")
    private String language;

    @NotNull(message = "genre.mandatory")
    private Genre genre;

    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }

    @JsonIgnore
    public boolean isUpdate() {
        return this.id != null;
    }
}
