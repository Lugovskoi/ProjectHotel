package by.lugovskoi.entity;

public class User {

    private int id;

    public User(int id, String name, String lastName, String passportId, Role role, int age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.passportId = passportId;
        this.role = role;
        this.age = age;
    }

    public User(){};

    private String name;

    private String lastName;

    private String passportId;

    private Role role;

    private int age;

    public String getRoleAsString() {
        return role.getTitle();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }
}
