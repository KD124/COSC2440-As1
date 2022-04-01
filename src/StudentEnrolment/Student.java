package StudentEnrolment;

public class Student {
    private String id;
    private String name;
    private String birthday;

    public Student(){
        id = null;
        name = null;
        birthday = null;
    }
    public Student(String id, String name, String birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public String getBirthday() {return birthday;}
}
