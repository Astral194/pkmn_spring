package ru.mirea.Pegov.pkmn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentEntityRepository extends JpaRepository<StudentEntity, UUID> {

    List<StudentEntity> getStudentByGroup(String group);

    Optional<StudentEntity> findByFirstNameAndSurNameAndFamilyNameAndGroup(String firstName, String surName, String familyName, String group);

    StudentEntity findByFirstNameAndSurNameAndFamilyName(String firstName, String surName, String familyName);

    List<StudentEntity> findAll();

    boolean existsByFirstNameAndSurNameAndFamilyName(String firstName, String surName, String familyName);
}
