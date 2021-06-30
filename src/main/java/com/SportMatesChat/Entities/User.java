package com.SportMatesChat.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class User {

    private int id;

    private String name;

    private String surnames;

    private String email;

    private String username;

    private String password;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate bornDate;

    private String avatar;
}
