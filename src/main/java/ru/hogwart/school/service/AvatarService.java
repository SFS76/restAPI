package ru.hogwart.school.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwart.school.entities.Avatar;

import java.io.IOException;
import java.util.List;

@Service
public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    Avatar findAvatar(Long studentId);

    List<Avatar> getAvatarPage (int page, int size);
}
