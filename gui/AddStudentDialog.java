package gui;

import bean.Student;
import dao.SchoolPersonnelDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class AddStudentDialog extends JDialog { //录入按钮点击后的学生录入窗口
    JButton btn_add;  //确认增加按钮
    JButton btn_return;  //返回主面板的按钮
    JLabel student_id; //学生id标签
    JLabel student_name; //学生名字标签
    JLabel student_major;  //学生专业标签
    JPanel jinput;  //输入信息面板
    JTextField idtext;  //输入id框
    JTextField nametext;  //输入名字框
    JTextField majortext; //输入专业框

    AddStudentDialog(JFrame f) {
        super(f);
        this.setSize(400, 300);
        this.setModal(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        jinput = new JPanel();
        jinput.setSize(400, 300);
        jinput.setLayout(null);
        idtext = new JTextField();  //初始化学生id输入框
        nametext = new JTextField();  //初始化学生姓名输入框
        majortext = new JTextField();  //初始化学生专业输入框
        student_id = new JLabel("ID");
        student_id.setBounds(50, 50, 50, 30);
        idtext.setBounds(100, 50, 180, 30);
        student_name = new JLabel("名字");
        student_name.setBounds(50, 100, 50, 30);
        nametext.setBounds(100, 100, 180, 30);
        student_major = new JLabel("专业");
        student_major.setBounds(50, 150, 50, 30);
        majortext.setBounds(100, 150, 180, 30);
        btn_add = new JButton("提交");
        btn_add.setBounds(100, 200, 70, 30);
        btn_return = new JButton("返回");
        btn_return.setBounds(210, 200, 70, 30);
        jinput.add(student_name);
        jinput.add(student_id);
        jinput.add(student_major);
        jinput.add(idtext);
        jinput.add(nametext);
        jinput.add(majortext);
        jinput.add(btn_add);
        jinput.add(btn_return);
        this.add(jinput);
        this.setLocationRelativeTo(f);  //设置窗口相对于指定组件的位置。
        btn_add.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idtext.getText().isEmpty() || nametext.getText().isEmpty() || majortext.getText().isEmpty()) {  //进行判空操作，为空不写入
                    JOptionPane.showMessageDialog(null, "录入信息不能为空！");
                } else {
                    Student student = new Student();
                    //将文本信息存入学生对象里面
                    student.setId(Integer.parseInt(idtext.getText()));
                    student.setName(nametext.getText());
                    student.setMajor(majortext.getText());
                    SchoolPersonnelDAO schoolPersonnelDAO = new SchoolPersonnelDAO();   //new一个文件操作对象
                    try {
                        schoolPersonnelDAO.addStudent(student); //写入信息
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "录入成功");
                    AddStudentDialog.this.setVisible(false);  //添加成功后将添加框隐藏
                    updatetable((MainJFrame) f); //调用表格更新方法
                }
            }
        });

        btn_return.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    //表格更新方法
    static void updatetable(MainJFrame f) {
        SchoolPersonnelDAO schoolPersonnelDAO = new SchoolPersonnelDAO();
        try {
            f.tm_student.setMessages(schoolPersonnelDAO.getStudentList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.tablestudent.setModel(f.tm_student); //将更新后的表格模型存入
        f.tablestudent.updateUI();  //更新表格模型
    }
}
