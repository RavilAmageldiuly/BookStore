package kz.halykacademy.bookstore.web.authors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveAuthor {
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthday;
}
