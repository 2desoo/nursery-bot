package nursery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userChatId;
    private String name;
    private LocalDateTime reportTime;
    private byte[] photoAnimal;
    private String feed;
    private String health;
    private String changesBehavior;

    public Report() {
    }

    public Report(Long id, Long userChatId, String name, LocalDateTime reportTime, byte[] photoAnimal, String feed, String health, String changesBehavior) {
        this.id = id;
        this.userChatId = userChatId;
        this.name = name;
        this.reportTime = reportTime;
        this.photoAnimal = photoAnimal;
        this.feed = feed;
        this.health = health;
        this.changesBehavior = changesBehavior;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserChatId() {
        return userChatId;
    }

    public void setUserChatId(Long userChatId) {
        this.userChatId = userChatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getReportTime() {
        return reportTime;
    }

    public void setReportTime(LocalDateTime reportTime) {
        this.reportTime = reportTime;
    }

    public byte[] getPhotoAnimal() {
        return photoAnimal;
    }

    public void setPhotoAnimal(byte[] photoAnimal) {
        this.photoAnimal = photoAnimal;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getChangesBehavior() {
        return changesBehavior;
    }

    public void setChangesBehavior(String changesBehavior) {
        this.changesBehavior = changesBehavior;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(userChatId, report.userChatId) && Objects.equals(name, report.name) && Objects.equals(reportTime, report.reportTime) && Arrays.equals(photoAnimal, report.photoAnimal) && Objects.equals(feed, report.feed) && Objects.equals(health, report.health) && Objects.equals(changesBehavior, report.changesBehavior);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, userChatId, name, reportTime, feed, health, changesBehavior);
        result = 31 * result + Arrays.hashCode(photoAnimal);
        return result;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", userChatId=" + userChatId +
                ", name='" + name + '\'' +
                ", reportTime=" + reportTime +
                ", photoAnimal=" + Arrays.toString(photoAnimal) +
                ", feed='" + feed + '\'' +
                ", health='" + health + '\'' +
                ", changesBehavior='" + changesBehavior + '\'' +
                '}';
    }
}
