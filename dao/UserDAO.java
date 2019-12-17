package dao;

import bean.User;
import utils.ReadTxt;
import utils.WriteTxt;

import java.io.IOException;

public class UserDAO {    //进行登录用户的查询和录入

    WriteTxt writeTxt;
    ReadTxt readTxt;

    public void addUser(User user) throws IOException {  //登录用户的录入
        writeTxt = new WriteTxt();
        writeTxt.writeUserTxt(user);
    }

    public boolean verificationUser(User user) throws IOException {  //根据用户名和密码进行登录用户的验证
        readTxt = new ReadTxt();
        if (readTxt.readUserTxt(user)) {
            return true;
        }
        return false;
    }
}
