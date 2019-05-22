package Data;

/**
 * @program: hospital
 * @author: Dong Ping
 * @description:: 用于存取查询到的病人信息
 * @create: 2018-11-17 11:14
 */
public class PatientInfo {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    private String name;
    private String sex;
    private String address;
    private String age;

}
