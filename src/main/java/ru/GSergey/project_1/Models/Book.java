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
public class Book {

    int book_id;

    @NotEmpty(message="Name не должно быть пустым")
    @Size(min = 2, max = 30, message = "Name должно быть от 2 до 30")
    String name;

    @NotEmpty(message="Autor не должно быть пустым")
    @Size(min = 2, max = 30, message = "Name должно быть от 2 до 30")
    String autor;

    @Min(value = 0, message = "print_date должен быть не менее 0")
    int print_date;

    int person_id;
}
