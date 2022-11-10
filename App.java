import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        Connection con = null;
        try {
            con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/student_records","root", ""
            );
            Statement st = con.createStatement();
            String sql = "select * from user;";
            ResultSet result = st.executeQuery(sql);
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                System.out.println("id = " + id);
                System.out.println("name = " + name);
            }
        } catch (SQLException ex) {
            System.out.println("MySQLへの接続に失敗しました。");
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
}
