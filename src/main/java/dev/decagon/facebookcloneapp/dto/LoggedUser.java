package dev.decagon.facebookcloneapp.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class LoggedUser {
    private String email;
    private String password;
    // checking
}
