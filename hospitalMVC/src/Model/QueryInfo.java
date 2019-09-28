package Model;

import Data.Medicine;
import Data.PatientInfo;
import Sql.SqlCommand;
import Tools.CreateConn;
import View.DoctorDiagnosis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: hospital
 * @author: Dong Ping
 * @description:: 数据库的相关操作
 * @create: 2018-11-17 10:15
 */
public class QueryInfo {
    /**
     * @param department 病人对应的诊室
     * @param id         病人的id
     * @description: 查询对应诊室病人信息
     */
    public String GetName(String department, String id) {
        Connection conn = CreateConn.GetConnection();
        SqlCommand sqlCommand = new SqlCommand();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = sqlCommand.GetName(department);
        String name = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * @param department 病人对应的诊室
     * @param id         病人的id
     * @description: 诊断界面中的病人信息
     */
    public PatientInfo GetPatientInfo(String department, String id) {
        Connection conn = CreateConn.GetConnection();
        PatientInfo patientInfo = new PatientInfo();
        SqlCommand sqlCommand = new SqlCommand();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = sqlCommand.GetInfo(department);
        List<String> list = new ArrayList<String>();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                patientInfo.setName(rs.getString(1));
                patientInfo.setSex(rs.getString(2));
                patientInfo.setAddress(rs.getString(3));
                patientInfo.setAge(rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patientInfo;
    }

    /**
     * @param keyword 药品的关键字
     * @description: 搜索关键字显示对应药品信息
     */
    public List<Medicine> QueryKeyword(String keyword) {
        List<Medicine> result = new ArrayList<Medicine>();
        Connection conn = CreateConn.GetConnection();
        SqlCommand sqlCommand = new SqlCommand();
        Medicine medicine = new Medicine();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = sqlCommand.SelectMedicine(keyword);
        List list = new ArrayList();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                medicine.id = rs.getString(1);
                medicine.name = rs.getString(2);
                medicine.price = rs.getString(3);
                medicine.num = rs.getString(4);
                result.add(medicine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void searchKeyword(String keyword) {
        Connection conn = CreateConn.GetConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SqlCommand sqlCommand = new SqlCommand();
        String sql = sqlCommand.SelectMedicine(keyword);
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name1 = rs.getString("name");
                String price = rs.getString("price");
                String num = rs.getString("num");
                DoctorDiagnosis.model.addRow(new Object[]{id, name1, price, num});
            }
        } catch (SQLException e1) {

        }
    }

    /**
     * @param department 诊室
     * @param id         挂号的序列号
     * @description: 搜索关键字显示对应药品信息
     */
    public String[] patientInfo(String department, String id) {
        Connection conn = CreateConn.GetConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SqlCommand sqlCommand = new SqlCommand();
        String sql = sqlCommand.selectPatientInfo(department);
        String[] str = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String idcard = rs.getString("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String age = rs.getString("age");
                str = new String[]{idcard, name, sex, age};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * @param
     * @description: 查找未诊断的挂号信息
     */
    public List selectNum(String department) {
        Connection conn = CreateConn.GetConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SqlCommand sqlCommand = new SqlCommand();
        String sql = sqlCommand.selectNum(department);
        String judge = "1";
        List list = new ArrayList();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, judge);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param
     * @description: 搜索关键字显示对应药品信息
     */
    public static void main(String[] args) {
        QueryInfo queryInfo = new QueryInfo();

    }
}
