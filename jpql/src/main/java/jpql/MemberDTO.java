package jpql;

public class MemberDTO {

    private String username;
    private int age;

    public MemberDTO(int age, String username) {
        this.age = age;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
