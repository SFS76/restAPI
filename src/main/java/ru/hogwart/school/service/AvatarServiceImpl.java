package ru.hogwart.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwart.school.entities.Avatar;
import ru.hogwart.school.entities.Student;
import ru.hogwart.school.repository.AvatarRepository;
import ru.hogwart.school.repository.StudentRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarServiceImpl implements AvatarService{

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private final int BUFFER_SIZE = 1024;
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Path filePath = Path.of(avatarsDir, student + "." + getExtentions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
            InputStream is = avatarFile.getInputStream();
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is, BUFFER_SIZE);
            BufferedOutputStream bos = new BufferedOutputStream(os, BUFFER_SIZE)
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(generateDataForDB(filePath));
        avatarRepository.save(avatar);
    }

    private byte[] generateDataForDB(Path filePath) throws IOException {
        try (
                InputStream is = Files.newInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(is, BUFFER_SIZE);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth()/100);
            BufferedImage preview = new BufferedImage( 100, height, image.getType());
            Graphics2D graphics2D = preview.createGraphics();
            graphics2D.drawImage(image, 0, 0, 100, height, null);
            graphics2D.dispose();

            ImageIO.write(preview, getExtentions(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }


    public Avatar findAvatar(Long studentId) {
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    private String getExtentions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public List<Avatar> getAvatarPage (int page, int size) {
        var pageRequest = PageRequest.of(page, size);
        Page<Avatar> page1 = avatarRepository.findAll(pageRequest);
        return page1.getContent();
    }
}
