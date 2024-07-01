package gift.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

///Users/hansol/Desktop/temp/kakao_step2_1/kakao_step2_1_2/spring-gift-product/src/main/java/gift/imageStorage/

public class ImageStorageUtil {
    private static final String STORAGE_PATH = "/Users/hansol/Desktop/temp/kakao_step2_1/kakao_step2_1_2/spring-gift-product/src/main/java/gift/imageStorage/";

    public static String saveImage(MultipartFile imageFile, Long productId) throws IOException {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String imageName = productId + "_" + timestamp + ".jpg";


        byte[] bytes = imageFile.getBytes();
        String filePath = STORAGE_PATH + imageName;
        File outputFile = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(bytes);
        }
        return filePath;
    }

    // URL-safe Base64 Encoder 사용
    public static String encodeImagePathToBase64(String imagePath) {
        return Base64.getUrlEncoder().encodeToString(imagePath.getBytes());
    }

    public static String decodeBase64ImagePath(String base64ImagePath) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(base64ImagePath);
        return new String(decodedBytes);
    }

    public static void deleteImage(String imagePath) {
        File imageFile = new File(imagePath);
        System.out.println("Attempting to delete image at path: " + imagePath);
        if (imageFile.exists()) {
            imageFile.delete();
            System.out.println("Image deleted successfully.");
        }
    }


}
