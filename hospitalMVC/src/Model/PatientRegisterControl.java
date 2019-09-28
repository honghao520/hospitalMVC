package Model;

import Tools.CreateConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sql.SqlCommand;

import javax.swing.*;

/**
 * @program: hospital
 * @author: Hong Hao
 * @description:: 新农合创建表
 * @create: 2018-11-07 14:05
 */
public class PatientRegisterControl {
    public void create(String name, String age, String sex, String address,String user, String password,String b,String money){

        Connection conn = CreateConn.GetConnection();
        SqlCommand sqlCommand = new SqlCommand();
        String sql= sqlCommand.rgt();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(user));
            pstmt.setString(2,name);
            pstmt.setString(3,password);
            pstmt.setString(4,b);
            pstmt.setString(5,money);
            pstmt.setString(6,sex);
            pstmt.setString(7,address);
            pstmt.setString(8,age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


