package users.entity;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String pictureExtension;

    public String getPictureExtension() {
        return pictureExtension;
    }

    public void setPictureExtension(String pictureExtension) {
        this.pictureExtension = pictureExtension;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }



    public User() {

    }

    public User(String firstName, String lastName, String login, String pictureExtension) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.pictureExtension = pictureExtension;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", pictureExtension='" + pictureExtension + '\'' +
                '}';
    }
}
