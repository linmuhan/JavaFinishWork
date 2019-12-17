package utils;

import bean.Student;
import bean.Teacher;
import bean.TxtInformation;
import bean.User;

import java.io.*;

public class WriteTxt {  //进行txt文件的写入

    TxtInformation txt = new TxtInformation();

    public void writeStudentTxt(Student student) throws IOException {  //进行学生信息txt的录入
        File file = new File(txt.getSTUDENT_PATH());
        FileWriter fw = new FileWriter(file, true);  //true表示可以进行追加，防止后面写入的用户将前面的学生覆盖掉
        BufferedWriter bw = new BufferedWriter(fw);  //用字符缓冲流进行文件的写入
        String str = student.getId() + "," + student.getName() + "," + student.getMajor();
        bw.write(str);
        bw.newLine(); //换行，这样存储的时候就是每一个学生一行信息，方便读取操作时候的方便
        bw.flush();  //将缓冲区的内容进行录入
        bw.close();
        fw.close();
    }

    public void writeUserTxt(User user) throws IOException {  //进行登录用户信息txt的录入
        File file = new File(txt.getUSER_PATH());
        FileWriter fw = new FileWriter(file, true);  //true表示可以进行追加，防止后面写入的用户将前面的用户覆盖掉
        BufferedWriter bw = new BufferedWriter(fw);  //用字符缓冲流进行文件的写入
        String str = user.getUsername() + "," + user.getPassword();
        bw.write(str);
        bw.newLine(); //换行，这样存储的时候就是每一个用户一行信息，方便读取操作时候的方便
        bw.flush();  //将缓冲区的内容进行录入
        bw.close();
        fw.close();
    }

    public void writeTeacherTxt(Teacher teacher) throws IOException {  //进行老师信息txt的录入
        File file = new File(txt.getTEACHER_PATH());
        FileWriter fw = new FileWriter(file, true);  //true表示可以进行追加，防止后面写入的用户将前面的老师覆盖掉
        BufferedWriter bw = new BufferedWriter(fw);  //用字符缓冲流进行文件的写入
        String str = teacher.getId() + "," + teacher.getName() + "," + teacher.getCollege();
        bw.write(str);
        bw.newLine(); //换行，这样存储的时候就是每一个老师一行信息，方便读取操作时候的方便
        bw.flush();  //将缓冲区的内容进行录入
        bw.close();
        fw.close();
    }
}
