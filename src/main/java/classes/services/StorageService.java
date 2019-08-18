package classes.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Optional;

import classes.entities.ImagesCacheEntity;
import classes.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

    @Autowired
    private ImageRepository imageRepository;

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

    public ImagesCacheEntity storeInDb(MultipartFile file) {
        try {
            ImagesCacheEntity image = new ImagesCacheEntity();
            image.setImageName(file.getOriginalFilename());
            image.setImage( file.getBytes() );

            return imageRepository.save(image);
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public byte[] loadImage(String filename) {
        try {
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

    public Object loadImageFromDb(String filename) {
        try {
            Optional<ImagesCacheEntity> imageOptional = imageRepository.findById( filename );
            return imageOptional.<Object>map(ImagesCacheEntity::getImage).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }
}
