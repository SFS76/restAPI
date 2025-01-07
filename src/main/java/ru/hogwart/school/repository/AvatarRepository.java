package ru.hogwart.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwart.school.entities.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(Long studentId);
}
