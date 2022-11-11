package dev.decagon.facebookcloneapp.model;

import dev.decagon.facebookcloneapp.enums.Gender;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    private String name;
    @Column(unique=true)
    private String email;
    private Gender gender;
}


