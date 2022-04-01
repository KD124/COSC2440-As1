package StudentEnrolment;

public class Course {
    private String id;
    private String name;
    private String creditNum;


    public Course(){
        id = null;
        name = null;
        creditNum = null;
    }
    public Course(String id, String name, String creditNum) {
        this.id = id;
        this.name = name;
        this.creditNum = creditNum;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public String getCreditNum() {return creditNum;}
}
