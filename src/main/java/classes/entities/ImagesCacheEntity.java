package classes.entities;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "Images_cache", schema = "nbds_library", catalog = "")
public class ImagesCacheEntity {
    private String imageName;
    private byte[] image;

    @Id
    @Column(name = "IMAGE_NAME", nullable = false, length = 400)
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Basic
    @Column(name = "IMAGE", nullable = true)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImagesCacheEntity that = (ImagesCacheEntity) o;

        if (!Objects.equals(imageName, that.imageName)) return false;
        return Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = imageName != null ? imageName.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
