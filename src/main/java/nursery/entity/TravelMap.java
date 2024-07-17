package nursery.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class TravelMap {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String filePath;
    private Long fileSize;
    private String mediaType;
    private byte[] picture;
    @OneToOne
    private Shelter shelter;

    public TravelMap() {
    }

    public TravelMap(Long id, String filePath, Long fileSize, String mediaType, byte[] picture, Shelter shelter) {
        this.id = id;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.picture = picture;
        this.shelter = shelter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shelter getShelterCat() {
        return shelter;
    }

    public void setShelterCat(Shelter shelter) {
        this.shelter = shelter;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelMap travelMap = (TravelMap) o;
        return Objects.equals(id, travelMap.id) && Objects.equals(filePath, travelMap.filePath) && Objects.equals(fileSize, travelMap.fileSize) && Objects.equals(mediaType, travelMap.mediaType) && Arrays.equals(picture, travelMap.picture) && Objects.equals(shelter, travelMap.shelter);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filePath, fileSize, mediaType, shelter);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }

    @Override
    public String toString() {
        return "TravelMap{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", date=" + Arrays.toString(picture) +
                ", shelter=" + shelter +
                '}';
    }
}
