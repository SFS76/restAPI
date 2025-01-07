package ru.hogwart.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwart.school.entities.Avatar;

import java.io.IOException;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    Avatar findAvatar(Long studentId);
}
