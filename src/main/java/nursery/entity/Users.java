package nursery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long chatId;

    public Users() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users user = (Users) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(chatId, user.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, chatId);
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + id +
                ", name='" + name + '\'' +
                ", chatId=" + chatId +
                '}';
    }
}
