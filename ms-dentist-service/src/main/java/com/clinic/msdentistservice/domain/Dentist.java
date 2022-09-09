package com.clinic.msdentistservice.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "dentists")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private Integer register;


}
