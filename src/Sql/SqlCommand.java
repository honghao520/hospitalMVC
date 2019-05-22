package Sql;

/**
 * @program: hospital
 * @author: Hong Hao
 * @description:: SQL语句
 * @create: 2018-11-07 14:51
 */
public class SqlCommand {
    /**
     * @description: 注册sql语言
     */
    public String rgt(){
        String sql="insert into patient VALUES (?,?,?,?,?,?,?,?)";
        return sql;
    }
    /**
     * @description: 医生登陆
     */
    public String logiin(){
        String sql = "Select password,name from doctor WHERE id = ? ;";
        return sql;
    }
    /**
     * @description: 选择药物信息的sql语言
     */
    public String SelectMedicine() {
        String sql = "select id,name,price,num from medicine;";
        return sql;
    }
    /**
     * @description: 查询病人id对应的密码的sql语言
     */
    public String login() {
        String sql = "Select password from patient WHERE id = ? ;";
        return sql;
    }

    /**
     * @description: 查询病人姓名的sql语言（1）
     */
    public String SelectName() {
        String sql = "SELECT name FROM patient WHERE id = ?;";
        return sql;
    }

    /**
     * @param department 对应诊室
     * @description: 挂号数字传入数据库的sql语言
     */
    public String Registered(String department) {
        String sql = "INSERT INTO " + department + "(patient_id) VALUES(?);";
        return sql;
    }

    /**
     * @description: 查询病人信息sql语言（1）
     */
    public String patient_info() {
        String sql = "SELECT id,name,sex from patient WHERE id = ?;";
        return sql;
    }

    /**
     * @param department 对应诊室
     * @description: 查询病人挂号数字的sql语言
     */
    public String Registered_num(String department) {
        String sql = "SELECT id from " + department + " WHERE patient_id = ?;";
        return sql;
    }

    /**
     * @param department 对应诊室
     * @description: 查询病人姓名的sql语言（2）
     */
    public String GetName(String department) {
        String sql = "select name from patient\n" +
                "where id = (select patient_id from " + department + " where id = ?);";
        return sql;
    }

    /**
     * @description: 查询病人信息的sql语言（2）
     */
    public String GetInfo(String department) {
        String sql = "select name,sex,address,age from patient\n" +
                "where id = (select patient_id from " + department + " where id = ?);";
        return sql;
    }

    /**
     * @description: 查询药品信息的sql语言
     */
    public String SelectMedicine(String keyword) {
        String sql = "select id,name,price,num from medicine\n" +
                "where name like '%" + keyword + "%'";
        return sql;
    }

}

