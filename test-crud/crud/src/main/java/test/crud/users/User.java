package test.crud.users;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class User {
    @Id
    private int id;
    private String nickname;
    private int age;
    private String email;
    public User() {

    }
    public User(int id, String nickname, int age, String email) {
        super();
        this.id = id;
        this.nickname = nickname;
        this.age = age;
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", nickname=" + nickname + ", age=" + age + ", email=" + email + "]";
    }
}
