package tech.itpark.itparkfinalproject.util;

import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class PictureUtil {

    private static String UPLOAD_DIR = "./pictures/";

    public static void uploadPicture(MultipartFile multipartFile, String id, String fileName) throws IOException {
        String uploadDir = UPLOAD_DIR + id;
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save uploaded file: " + fileName);
        }
    }

    public static void deletePictures(String id) throws IOException {
        Path path = Paths.get(UPLOAD_DIR + id );
        FileSystemUtils.deleteRecursively(path);
    }
}
