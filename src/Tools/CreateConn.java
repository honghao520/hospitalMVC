package Tools;

import Sql.SqlCommand;

import java.sql.*;

/**
 * @program: hospital
 * @author: Dong Ping
 * @description:: 数据库连接工作
 * @create: 2018-10-18 10:59
 */
public class CreateConn {
    private static final String url = "jdbc:mysql://localhost:3306/hospital?useUnicode=true&characterEncoding=utf8";
    private static final String user = "root";
    private static final String pass = "123456";

    private static Connection conn = null;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("连接数据库···");
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("连接数据库成功");
            PreparedStatement pstmt = null;
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动类失败");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("连接数据库失败");
            e.printStackTrace();
        }
    }

    /**
     * @return
     * @description: 对外提供一个方法获取数据库连接
     */
    public static Connection GetConnection(){
        return conn;
    }
    public static void main(String []args){
        CreateConn.GetConnection();
    }

    /**
     * @program: hospital
     * @author: Dong Ping
     * @description:: 登陆成功后，挂号页面的用户姓名
     * @create: 2018-11-05 10:06
     */
    public static class SelectName {
        public String SelectName(String id) {
            Connection conn = GetConnection();
            SqlCommand sqlCommand = new SqlCommand();
            String sql = sqlCommand.SelectName();
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String name = null;
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    name = rs.getString(1);
                }
                System.out.println(name);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return name;
        }
    }
}
