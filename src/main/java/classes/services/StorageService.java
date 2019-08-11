package classes.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
    private String filePath = System.getProperty("user.dir").replace("bin","") + "/src/main/java/assets/images/";

    public void store(MultipartFile file) {
        try {
            File image = new File(filePath + file.getOriginalFilename());
            image.createNewFile();
            FileOutputStream fout = new FileOutputStream(image);
            fout.write(file.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public byte[] loadImage(String filename) {
        try {
            System.out.println(filePath);
            System.out.println(System.getProperty("user.dir"));
            File file = new File(filePath + filename);
            byte[] fileContent = new byte[(int) file.length()];
            try (FileInputStream inputStream = new FileInputStream(file)) {
                inputStream.read(fileContent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return fileContent;
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }
}
