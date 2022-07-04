package model;

public class Student {
    private int id;
    private String name;
    private String birth ;
    private String address;
    private String phone;
    private String email;
    private String classS;

    public Student(int id, String name, String birth, String address, String phone, String email, String classS) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.classS = classS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassS() {
        return classS;
    }

    public void setClassS(String classS) {
        this.classS = classS;
    }
}
