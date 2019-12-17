package bean;

public class TxtInformation {
    private String USER_PATH = "user.txt";   //登录用户存储的地址
    private String STUDENT_PATH = "student.txt";  //学生信息存储的地址
    private String TEACHER_PATH = "teacher.txt";  //老师信息存储的地址

    public String getUSER_PATH() {
        return USER_PATH;
    }

    public void setUSER_PATH(String USER_PATH) {
        this.USER_PATH = USER_PATH;
    }

    public String getSTUDENT_PATH() {
        return STUDENT_PATH;
    }

    public void setSTUDENT_PATH(String STUDENT_PATH) {
        this.STUDENT_PATH = STUDENT_PATH;
    }

    public String getTEACHER_PATH() {
        return TEACHER_PATH;
    }

    public void setTEACHER_PATH(String TEACHER_PATH) {
        this.TEACHER_PATH = TEACHER_PATH;
    }
}
