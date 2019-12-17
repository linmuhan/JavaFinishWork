package gui;

import bean.User;
import dao.UserDAO;
import utils.WriteTxt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class RegisterJFrame extends JFrame {
    JTextField tf_username;  //用户名输入文本框
    JPasswordField pf_password; //用户密码输入文本框
    JPasswordField pfsure_password; //用户密码再次确认输入文本框
    JButton btn_save; //用于保存用户信息
    JButton btn_return;  //用于返回登录界面
    JLabel USER_TEXT=new JLabel("用户名:");//用户名文本标签
    JLabel PASS_TEXT=new JLabel("密码:");//密码文本标签
    JLabel PASSSURE_TEXT=new JLabel("确认密码:");//确认密码文本标签
    public RegisterJFrame(){
        super();
        initialize();
    }
    public void initialize(){
        this.setTitle("注册");
        this.setSize(600,400);
        this.setLocation(550,350);
        this.setLayout(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);  //点击关闭图标后将无法关闭
        tf_username=new JTextField("");
        tf_username.setBounds(180,50,200,30);
        USER_TEXT.setBounds(120,50,60,30);
        pf_password=new JPasswordField("");
        pf_password.setBounds(180,100,200,30);
        PASS_TEXT.setBounds(120,100,60,30);
        pfsure_password=new JPasswordField("");
        pfsure_password.setBounds(180,150,200,30);
        PASSSURE_TEXT.setBounds(120,150,60,30);
        btn_save=new JButton("保存");
        btn_save.setBounds(180,210,70,30);
        btn_return=new JButton("返回");
        btn_return.setBounds(310,210,70,30);
        this.add(USER_TEXT);
        this.add(PASS_TEXT);
        this.add(PASSSURE_TEXT);
        this.add(tf_username);
        this.add(pf_password);
        this.add(pfsure_password);
        this.add(btn_save);
        this.add(btn_return);
        btn_save.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {  //将注册的信息的保存起来
                if(pf_password.getPassword().length<1||tf_username.getText().isEmpty()||pfsure_password.getPassword().length<1){
                    JOptionPane.showMessageDialog(null, "输入信息不能为空");
                }else{
                    if(new String(pfsure_password.getPassword()).equals(new String(pf_password.getPassword()))){  //将获取的密码文本转换成字符串进行比较
                        User user=new User();
                        user.setUsername(tf_username.getText());
                        user.setPassword(new String(pf_password.getPassword()));
                        try{
                            new UserDAO().addUser(user);   //调用写好的方法将user对象写入txt
                        }catch (IOException e1){
                            e1.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null,"保存完成，注册成功！");
                    }else{
                        JOptionPane.showMessageDialog(null,"密码不一致");
                    }
                }
            }
        });

        btn_return.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {  //点击将返回登录界面
                dispose();
            }
        });
    }
}
