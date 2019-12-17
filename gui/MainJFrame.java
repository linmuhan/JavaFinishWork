package gui;

import dao.SchoolPersonnelDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainJFrame extends JFrame {

    JPanel studentjpanel;  //用来显示学生的面板
    JPanel teacherjpanel;  //用来显示老师的面板
    JTabbedPane schoolperdonneljpanel;  //用来容纳学生与老师面板的面板容器
    JScrollPane stuentmessage; //用来显示学生信息的面板
    JPanel studentoperation;  //下方用来显示学生面板按钮的面板
    JButton btn_st_add; //用来实现录入学生信息的按钮
    JTable tablestudent; //学生信息表格
    JTable tableteacher; //老师信息表格
    TableModel tm_student; //学生表格模型
    TableModel tm_teacher; //老师表格模型
    JScrollPane teachermessage; //用来显示老师信息的面板
    JPanel teacheroperation;  //下方用来显示老师面板按钮的面板
    JButton btn_te_add; //用来实现录入老师信息的按钮
    String[] strstudent = {"ID", "姓名", "专业"};  //学生信息的名称
    String[] strteacher = {"ID", "姓名", "学院"};   //老师信息的名称
    SchoolPersonnelDAO schoolPersonnelDAO;

    public MainJFrame() {
        super();
        initialize();
    }

    //初始化一个主页面面板
    private void initialize() {
        this.setTitle("信息显示");
        this.setSize(800, 600);
        this.setLocation(550, 350);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  //点击关闭图标后将关闭
        schoolPersonnelDAO = new SchoolPersonnelDAO();
        //学生面板的设计
        studentjpanel = new JPanel();
        studentjpanel.setLayout(new BorderLayout());
        studentoperation = new JPanel();
        btn_st_add = new JButton("录入");
        studentoperation.add(btn_st_add);  //将录入按钮添加到学生按钮面板上
        tm_student = new TableModel();  //初始化一个学生信息表格模型
        tm_student.setColumnNames(strstudent);//初始化表格标题
        try {
            tm_student.setMessages(schoolPersonnelDAO.getStudentList());  //初始化表格数据
        } catch (IOException e) {
            e.printStackTrace();
        }
        tablestudent = new JTable(tm_student);  //将表格模型加入表格
        stuentmessage = new JScrollPane(tablestudent);  //将表格加入信息模板
        studentjpanel.add(stuentmessage, BorderLayout.CENTER);
        studentjpanel.add(studentoperation, BorderLayout.SOUTH);
        //老师面板的设计
        teacherjpanel = new JPanel();
        teacherjpanel.setLayout(new BorderLayout());
        teacheroperation = new JPanel();
        btn_te_add = new JButton("录入");
        teacheroperation.add(btn_te_add);  //将录入按钮添加到老师按钮面板上
        tm_teacher = new TableModel();
        tm_teacher.setColumnNames(strteacher);
        try {
            tm_teacher.setMessages(schoolPersonnelDAO.getTeacherList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableteacher = new JTable(tm_teacher);
        teachermessage = new JScrollPane(tableteacher);
        teacherjpanel.add(teachermessage, BorderLayout.CENTER);
        teacherjpanel.add(teacheroperation, BorderLayout.SOUTH);
        //选项卡的高度与宽度无法调整，待修改.表格里面的内容表格大小还没有进行修改
        schoolperdonneljpanel = new JTabbedPane();
        schoolperdonneljpanel.add(studentjpanel);
        schoolperdonneljpanel.add(teacherjpanel); //面板容器添加两个面板
        schoolperdonneljpanel.setTitleAt(0, "学生");
        schoolperdonneljpanel.setTitleAt(1, "教师");
        this.setContentPane(schoolperdonneljpanel);
        MainJFrame mainJFrame = this;   //将这个主窗口传递给一个new的新对象
        btn_te_add.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTeacherDialog addTeacherDialog = new AddTeacherDialog(mainJFrame);
                addTeacherDialog.setVisible(true);
            }
        });

        btn_st_add.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudentDialog addStudentDialog = new AddStudentDialog(mainJFrame);
                addStudentDialog.setVisible(true);
            }
        });
    }
}
