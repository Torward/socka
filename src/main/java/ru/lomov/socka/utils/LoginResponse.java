package ru.lomov.socka.utils;

import ru.lomov.socka.entities.AppUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private AppUser user;
}
