package com.voronin.technicaltest.entity;

import com.voronin.technicaltest.validation.UserGender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;


@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @Builder
public class User {

    @Id
    @Column(name = "user_name")
    @NotBlank(message = "Username is mandatory")
    @Size(max = 100, message = "Username is too long")
    private String userName;

    @Past(message = "You have not been born yet. Check yours date of birth ")
    @NotNull(message = "Date of birth is mandatory")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotBlank(message = "Country is mandatory")
    @Size(max = 100, message = "Country name is too long")
    private String country;

    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", message = "Phone number format is incorrect")
    @Column(name = "phone_number")
    private String phoneNumber;

    @UserGender(message = "Gender is incorrect")
    private String gender;

}
