package gui;

import bean.Teacher;
import dao.SchoolPersonnelDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class AddTeacherDialog extends JDialog { //录入按钮点击后的老师录入窗口
    JButton btn_add;  //确认增加按钮
    JButton btn_return;  //返回主面板的按钮
    JLabel teacher_id; //老师id标签
    JLabel teacher_name; //老师名字标签
    JLabel teacher_major;  //老师专业标签
    JPanel jinput;  //输入信息面板
    JTextField idtext;  //输入id框
    JTextField nametext;  //输入名字框
    JTextField collegetext; //输入专业框

    AddTeacherDialog(JFrame f) {
        super(f);
        this.setSize(400, 300);
        this.setModal(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);  //点击右上角关闭按钮无反应
        jinput = new JPanel();
        jinput.setSize(400, 300);
        jinput.setLayout(null);
        idtext = new JTextField();  //初始化老师id输入框
        nametext = new JTextField();  //初始化老师姓名输入框
        collegetext = new JTextField();  //初始化老师学院输入框
        teacher_id = new JLabel("ID");
        teacher_id.setBounds(50, 50, 50, 30);
        idtext.setBounds(100, 50, 180, 30);
        teacher_name = new JLabel("名字");
        teacher_name.setBounds(50, 100, 50, 30);
        nametext.setBounds(100, 100, 180, 30);
        teacher_major = new JLabel("学院");
        teacher_major.setBounds(50, 150, 50, 30);
        collegetext.setBounds(100, 150, 180, 30);
        btn_add = new JButton("提交");
        btn_add.setBounds(100, 200, 70, 30);
        btn_return = new JButton("返回");
        btn_return.setBounds(210, 200, 70, 30);
        jinput.add(teacher_name);
        jinput.add(teacher_id);
        jinput.add(teacher_major);
        jinput.add(idtext);
        jinput.add(nametext);
        jinput.add(collegetext);
        jinput.add(btn_add);
        jinput.add(btn_return);
        this.add(jinput);
        this.setLocationRelativeTo(f);  //设置窗口相对于指定组件的位置。
        btn_add.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idtext.getText().isEmpty() || nametext.getText().isEmpty() || collegetext.getText().isEmpty()) {  //进行判空操作，为空不写入
                    JOptionPane.showMessageDialog(null, "录入信息不能为空！");
                } else {
                    Teacher teacher = new Teacher();
                    //将文本信息存入老师对象里面
                    teacher.setId(Integer.parseInt(idtext.getText()));
                    teacher.setName(nametext.getText());
                    teacher.setCollege(collegetext.getText());
                    SchoolPersonnelDAO schoolPersonnelDAO = new SchoolPersonnelDAO();   //new一个文件操作对象
                    try {
                        schoolPersonnelDAO.addTeacher(teacher); //写入信息
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "录入成功");
                    AddTeacherDialog.this.setVisible(false);  //添加成功后将添加框隐藏
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
            f.tm_teacher.setMessages(schoolPersonnelDAO.getTeacherList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.tableteacher.setModel(f.tm_teacher);   //将更新后的表格模型存入
        f.tableteacher.updateUI();  //更新表格模型
    }
}
