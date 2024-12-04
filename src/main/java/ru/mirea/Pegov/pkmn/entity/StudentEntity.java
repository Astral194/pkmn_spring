package ru.mirea.Pegov.pkmn.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import ru.mirea.Pegov.pkmn.models.Student;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "students")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "sur_name")
    private String surName;

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "\"group\"")
    private String group;

    public static StudentEntity fromStudentToEntity(Student entity) {
        return StudentEntity.builder()
                .firstName(entity.getFirstName())
                .surName(entity.getSurName())
                .familyName(entity.getFamilyName())
                .group(entity.getGroup())
                .build();
    }


    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

}