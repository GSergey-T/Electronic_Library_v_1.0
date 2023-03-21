package ru.GSergey.project_1.Models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {

    private int person_id;

    @NotEmpty(message="Name не должно быть пустым")
    @Size(min = 2, max = 30, message = "Name должно быть от 2 до 30")
    private String full_name;

    @Min(value = 1900, message = "date_of_birth должен быть не менее 1900")
    private int date_of_birth;

}
