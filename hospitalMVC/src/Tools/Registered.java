package Tools;

import Sql.SqlCommand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: hospital
 * @author: Dong Ping
 * @description:: 挂号数据传入数据库, 以及传输信息
 * @create: 2018-11-07 14:21
 */
public class Registered {

    public void info(String department, String id) {
        Connection conn = CreateConn.GetConnection();
        SqlCommand sqlCommand = new SqlCommand();
        String sql = sqlCommand.Registered(department);
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description:: 病人信息集合
     */
    public List<String> ill_info(String name) {
        Connection conn = CreateConn.GetConnection();
        SqlCommand sqlCommand = new SqlCommand();
        String sql = sqlCommand.patient_info();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String id = null;
        String patient_name = null;
        String sex = null;
        List arr = new ArrayList();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                id = rs.getString(1);
                patient_name = rs.getString(2);
                sex = rs.getString(3);
                arr.add(id);
                arr.add(patient_name);
                arr.add(sex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public String registered(String id, String department) {
        Connection conn = CreateConn.GetConnection();
        SqlCommand sqlCommand = new SqlCommand();
        String sql = sqlCommand.Registered_num(department);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String num = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                num = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public static void main(String[] args) {
        Registered registered = new Registered();
        registered.info("surgical_department","110");
    }
}
