package com.example.learningspringboot.model;

import com.example.learningspringboot.annotation.FieldsValueMatch;
import com.example.learningspringboot.annotation.PasswordValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@FieldsValueMatch.list({
        @FieldsValueMatch(
                field = "password",
                confirmField = "confirmPassword",
                message = "Confirm password does not match!"
        ),
        @FieldsValueMatch(
                field = "email",
                confirmField = "confirmEmail",
                message = "Confirm email does not match!"
        )
})
@Table(name="person")
public class Person extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int personId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number is not valid")
    private String mobileNumber;

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Confirm email must not be blank")
    @Email(message = "Confirm email is not valid")
    @Transient
    private String confirmEmail;

    @NotBlank(message = "Password must not be blank")
    @PasswordValidation
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;

    @NotBlank(message = "Confirm password must not be blank")
    @Size(min = 5, message = "Confirm password must be at least 5 characters long")
    @Transient
    private String confirmPassword;
}
