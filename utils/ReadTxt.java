package utils;

import bean.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadTxt {  //进行txt文件的读取
    TxtInformation txt = new TxtInformation();

    public boolean readUserTxt(User user) throws IOException {  //进行登录用户的信息验证
        File file = new File(txt.getUSER_PATH());
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);  //采用字符缓冲流进行读取
        while (true) {
            String line = br.readLine();
            if (line == null) {   //当查询到最后没有可查的时候退出循环
                break;
            }
            String[] strs = line.split(",");  //将读取一行的进行字符串分割取出用户名和密码
            if (strs[0].equals(user.getUsername()) && strs[1].equals(user.getPassword())) {
                br.close();
                fr.close();
                return true;
            }
        }
        return false;
    }

    public List<Student> readStudentTxt() throws IOException {  //将所有学生的信息以一个学生对象的集合返回
        List<Student> students = new ArrayList<>();
        Student student;
        File file = new File(txt.getSTUDENT_PATH());
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            if (!line.equals("") && line != null) {
                String[] strs = line.split(",");  //将读取一行的进行字符串分割取出id和姓名和专业
                student = new Student();
                student.setId(Integer.parseInt(strs[0]));
                student.setName(strs[1]);
                student.setMajor(strs[2]);
                students.add(student);
            }

        }
        return students;
    }

    public List<Teacher> readTeacherTxt() throws IOException {  //将所有老师的信息以一个老师对象的集合返回
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher;
        File file = new File(txt.getTEACHER_PATH());
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            if (!line.equals("") && line != null) {
                teacher = new Teacher();
                String[] strs = line.split(",");  //将读取一行的进行字符串分割取出id和姓名和学院
                teacher.setId(Integer.parseInt(strs[0]));
                teacher.setName(strs[1]);
                teacher.setCollege(strs[2]);
                teachers.add(teacher);
            }

        }
        return teachers;
    }
}
