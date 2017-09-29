package open.cklan.com.interviewlibrary.category.day13_mvvm.entity;

import android.databinding.Bindable;
import android.databinding.Observable;

/**
 * AUTHOR：lanchuanke on 17/9/28 16:43
 */
public class User{
    private String userName;
    private String realName;
    private String mobile;
    private int age;

    public User(String realName, String mobile) {
        this.realName = realName;
        this.mobile = mobile;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;

    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
