package dao;

import bean.Student;
import bean.Teacher;
import utils.ReadTxt;
import utils.WriteTxt;

import java.io.IOException;
import java.util.List;

public class SchoolPersonnelDAO {  //进行学生老师的多态父类存储的录入和查询

    WriteTxt writeTxt;
    ReadTxt readTxt;

    public void addStudent(Student student) throws IOException {  //进行学生信息的录入
        writeTxt = new WriteTxt();
        writeTxt.writeStudentTxt(student);
    }

    public void addTeacher(Teacher teacher) throws IOException {  //进行老师信息的录入
        writeTxt = new WriteTxt();
        writeTxt.writeTeacherTxt(teacher);
    }

    public String[][] getStudentList() throws IOException {  //将所有学生信息以字符串数组的方式返回
        readTxt = new ReadTxt();
        List<Student> list = readTxt.readStudentTxt();
        String[][] strings = new String[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            strings[i][0] = Integer.toString(list.get(i).getId());
            strings[i][1] = list.get(i).getName();
            strings[i][2] = list.get(i).getMajor();
        }
        return strings;
    }

    public String[][] getTeacherList() throws IOException {  //将所有老师信息以字符串数组的方式返回
        readTxt = new ReadTxt();
        List<Teacher> list = readTxt.readTeacherTxt();
        String[][] strings = new String[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            strings[i][0] = Integer.toString(list.get(i).getId());
            strings[i][1] = list.get(i).getName();
            strings[i][2] = list.get(i).getCollege();
        }
        return strings;
    }
}
