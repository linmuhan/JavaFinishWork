package bean;

public class Teacher extends SchoolPersonnel{
    private String college; //存储学院老师的学院信息

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
