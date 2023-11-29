import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;
public class MySQL_JDBCS_AcademicManagementSystem {
    public static void main(String[] args) throws InstantiationException,
            IllegalAccessException, FileNotFoundException {
        Connection conn_db = null;
        Connection conn_db_academic = null;
        Statement stmt = null;
        ResultSet resultSet = null;

        final String inputFileName = "/Users/baghyeong-geon/eclipse-workspace/tbl_students.txt";
        final String MySQL_JDBC_driver = "com.mysql.cj.jdbc.Driver";
        final String url_DB = "jdbc:mysql://localhost:3306/";
        final String db_acamdemic_name = "DB_ACADEMIC";
        final String url_DB_Academic = url_DB + db_acamdemic_name;
        final String user_name = "root";
        final String passwd = "tjs991101";

        String sql = null;
        try {
            System.out.println("Loading MySQL's JDBC driver...");
            Class.forName(MySQL_JDBC_driver); // load MySQL's JDBC driver
            System.out.println("Loading MySQL's JDBC driver successfully !!");
            System.out.flush();
            int result;
            try
            {
                conn_db_academic = DriverManager.getConnection(url_DB_Academic, user_name, passwd);
                System.out.printf("Database (%s) is already existing !!\n", db_acamdemic_name);
                System.out.printf("Connected to %s\n", url_DB_Academic);
            }
            catch (SQLException e)
            {
                System.out.println("SQLException - " + e.getMessage());
                System.out.printf("Try to create a database (DB_ACADEMIC) ....\n");
                conn_db = DriverManager.getConnection(url_DB, user_name, passwd);
                stmt = conn_db.createStatement();
                sql = "CREATE DATABASE " + db_acamdemic_name;
                result = stmt.executeUpdate(sql);
                System.out.printf("Database (DB_ACADEMIC) created successfully ....\n");
                conn_db_academic = DriverManager.getConnection(url_DB_Academic, user_name, passwd);
                System.out.printf("Connected to %s\n", url_DB_Academic);
            }
            String tbl_students_name = "tbl_students";
            if (tableExistsSQL(conn_db_academic, tbl_students_name))
            {
                System.out.printf("Table (%s) is already existing in Database (%s)\n",
                        tbl_students_name, db_acamdemic_name);
            }
            else
            {
                System.out.printf("Table (%s) is not existing in Database (%s)\n",
                        tbl_students_name, db_acamdemic_name);
                stmt = conn_db_academic.createStatement();
                sql = "CREATE TABLE " + tbl_students_name +
                        "(stID INTEGER not NULL, " +
                        "name VARCHAR(25), " +
                        "dept VARCHAR(8), " + "gpa DOUBLE(8, 2), " +
                        "PRIMARY KEY (stID)" +
                        ");";
                result = stmt.executeUpdate(sql);
                System.out.printf("Table (%s) is created in Database (%s)\n",
                        tbl_students_name, db_acamdemic_name);
            }
            fget_and_insert_STData(inputFileName, conn_db_academic, tbl_students_name);
            printStudentTable(conn_db_academic, tbl_students_name);
            if (conn_db_academic != null)
                conn_db_academic.close();
            if (conn_db != null)
                conn_db.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Error in loading MySQL's JDBC driver (ClassNotFoundException) !!, error message = " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error in connection to MySQL DB !!, error message =" + e.getMessage());
        }
    }
    static boolean tableExistsSQL(Connection connection, String tableName)
            throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT count(*) "
                        + "FROM information_schema.tables " + "WHERE table_name = ?"
                        + "LIMIT 1;");
        preparedStatement.setString(1, tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) != 0;
    }
    static void fget_and_insert_STData(String fname, Connection conn_stDB, String tbl_name) throws FileNotFoundException, SQLException {
        Scanner fin = new Scanner(new File(fname));
        while (fin.hasNext()) {
            int stID = fin.nextInt();
//String str_stID = fin.next();
            String str_name = fin.next();
            String str_dept = fin.next();
            double gpa = fin.nextDouble();
            Statement stmt = conn_stDB.createStatement();
            ResultSet resultSet = null;
            String sql = "INSERT INTO " + tbl_name
                    + " (stID, name, dept, gpa) "
                    + " VALUES (%d, \'%s\', \'%s\', %f)".formatted(stID, str_name, str_dept, gpa)
                    + ";";
            System.out.printf("SQL_statement = %s\n", sql);
            boolean result = stmt.execute(sql);
            System.out.printf("Inserted Student(%7d, %8s, %5s, %6.2f)\n", stID, str_name, str_dept, gpa);
        }
        fin.close();
    }
    static void printStudentTable(Connection conn_stDB, String tbl_name) throws SQLException {
        Statement stmt = conn_stDB.createStatement();
        ResultSet resultSet = null;
        String sql = "SELECT * FROM " + tbl_name + ";";
        resultSet = stmt.executeQuery(sql);
        printStudentRecords(resultSet, "stID", "name", "dept", "gpa"); }
    static void printStudentRecords(ResultSet srs, String col1, String col2,
                                    String col3, String col4) throws SQLException{
        String str_st_id = null;
        String str_name = null;
        String str_dept = null;
        String str_gpa = null;
        System.out.printf("%6s %8s %5s %7s\n", col1, col2, col3, col4);
        while (srs.next()) {
            if (col1 != "") {
                str_st_id = new String(srs.getString("stID")); }
            if (col2 != "") {
                str_name = new String(srs.getString("name")); }
            if (col3 != "") {
                str_dept = new String(srs.getString("dept")); }
            if (col4 != "") {
                str_gpa = new String(srs.getString("gpa")); }
            System.out.printf("%6s %8s %5s %7s\n", str_st_id, str_name, str_dept, str_gpa);
        }
    }
}
