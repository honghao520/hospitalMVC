package Model;

import Data.Name;
import Sql.SqlCommand;
import Tools.CreateConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: hospital
 * @author: Dong Ping
 * @description:: 用户登陆系统
 * @create: 2018-11-04 17:41
 */
public class LoginSystem {
    /**
     * @description:: 病人登陆
     */
    public int patient_login(String id, String password) {
        Connection conn = CreateConn.GetConnection();
        SqlCommand sqlCommand = new SqlCommand();
        String sql = sqlCommand.login();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String pass = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                pass = rs.getString(1);
            }
            if (pass.equals(password)) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            return 2;
        } catch (NullPointerException e) {
            return 2;
        }
    }

    /**
     * @description:: 医生登陆
     */
    public int doctor_login(String id,String password){
        Connection conn = CreateConn.GetConnection();
        SqlCommand sqlCommand = new SqlCommand();
        String sql = sqlCommand.logiin();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String pass = null;
        String name = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();

            while (rs.next()){
                pass = rs.getString(1);
                name = rs.getString(2);
            }
            if (pass.equals(password)){
                Name.name = name;
                return 1;
            }else {
                return 0;
            }
        } catch (SQLException e) {
            return 2;
        } catch (NullPointerException e){
            return 2;
        }
    }
}
