import java.sql.*;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        Connection connection = connectMysql();
        Statement statement = connection.createStatement();
        try {
            createStudents(statement, "hoge", "hoge@hoge");
            fetchStudents(statement);
        } catch (SQLException e) {
            System.out.println("Failed execute MySQL");
            System.out.println( e);
        } finally {
            if (connection != null) connection.close();
        }
    }

    private static Connection connectMysql() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                String.format("jdbc:mysql://%s/%s", System.getenv("HOST"), System.getenv("DB")),
                System.getenv("USER"),
                System.getenv("PASSWORD")
            );
        } catch (SQLException e) {
            System.out.println("Failed to connect MySQL.");
            System.out.println(e);
        }
        return connection;
    }

    private static void createStudents(
        Statement statement,
        String name,
        String email
    ) throws SQLException {
        Random rand = new Random();
        int id = rand.nextInt(10000) + 1;

        String sql = String.format(
            "INSERT INTO students VALUES(%d, %d, \'%s\', \'%s\',%d)",
            id,
            id,
            name,
            email,
            id
        );
        statement.execute(sql);
    }

    private static ResultSet fetchStudents(Statement statement) throws SQLException {
        String sql = "SELECT * FROM students";
        ResultSet result = statement.executeQuery(sql);
        while(result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");

            System.out.println("id = " + id);
            System.out.println("name = " + name);
            deleteStudentById(statement, id);
        }
        return result;
    }

    private static void deleteStudentById(Statement statement, Integer id) throws SQLException {
        String sql = String.format( "DELETE FROM students WHERE id = %d", id);
        statement.execute(sql);
    }
}
