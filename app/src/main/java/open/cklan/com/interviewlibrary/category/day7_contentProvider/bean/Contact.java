package open.cklan.com.interviewlibrary.category.day7_contentProvider.bean;

/**
 * AUTHOR：lanchuanke on 17/9/14 16:47
 */
public class Contact extends DBBaseEntity {
    public String name;
    public String phone;

    public Contact(){}

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

}
