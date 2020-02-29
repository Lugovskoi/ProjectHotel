package by.lugovskoi.entity;

public enum Role {

    CLIENT("CLIENT"), ADMIN("ADMIN"), SERVICE_ADMIN("SERVICE_ADMIN");
    private Role(String title) {
        this.title = title;
    }
    private String title;

    public String getTitle() {
        return title;
    }
}
