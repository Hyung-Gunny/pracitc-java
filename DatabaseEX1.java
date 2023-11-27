import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;

public class ShoppingMallSystem {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        final String MySQL_JDBC_driver = "com.mysql.cj.jdbc.Driver";
        final String url_DB = "jdbc:mysql://localhost:3306/";
        final String db_name = "ShoppingMall";
        final String url_DB_ShoppingMall = url_DB + db_name;
        final String user_name = "your_username";
        final String passwd = "your_password";

        try {
            // Load MySQL's JDBC driver
            Class.forName(MySQL_JDBC_driver);
            System.out.println("Loading MySQL's JDBC driver successfully!");

            // Connect to the database
            connection = DriverManager.getConnection(url_DB_ShoppingMall, user_name, passwd);
            System.out.printf("Connected to %s\n", url_DB_ShoppingMall);

            // Create tables if not exist
            createTable(connection, "tbl_member", "name VARCHAR(50), contact_point VARCHAR(20), address VARCHAR(100)");
            createTable(connection, "tbl_product", "product_name VARCHAR(50), price DOUBLE, manufacturer VARCHAR(50)");
            createTable(connection, "tbl_order", "order_number INT, purchaser VARCHAR(50), item VARCHAR(50), total_price DOUBLE");
            createTable(connection, "tbl_cart", "customer VARCHAR(50), product VARCHAR(50)");

            // Insert data into tables
            insertDataFromFile(connection, "tbl_member", "C://MySQL//tbl_member.txt");
            insertDataFromFile(connection, "tbl_product", "C://MySQL//tbl_product.txt");
            insertDataFromFile(connection, "tbl_order", "C://MySQL//tbl_order.txt");
            insertDataFromFile(connection, "tbl_cart", "C://MySQL//tbl_cart.txt");

            // Print data from tables
            printTable(connection, "tbl_member");
            printTable(connection, "tbl_product");
            printTable(connection, "tbl_order");
            printTable(connection, "tbl_cart");

        } catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the finally block
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void createTable(Connection connection, String tableName, String columns) throws SQLException {
        if (!tableExistsSQL(connection, tableName)) {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE " + tableName + " (" + columns + ")";
            statement.executeUpdate(sql);
            System.out.printf("Table (%s) created in Database\n", tableName);
        } else {
            System.out.printf("Table (%s) already exists in Database\n", tableName);
        }
    }

    static void insertDataFromFile(Connection connection, String tableName, String fileName) throws FileNotFoundException, SQLException {
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNext()) {
            String[] data = scanner.nextLine().split(",");
            String values = String.join("', '", data);
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO " + tableName + " VALUES ('" + values + "')";
            statement.executeUpdate(sql);
        }
        scanner.close();
    }

    static void printTable(Connection connection, String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);

        System.out.printf("Table: %s\n", tableName);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-20s", metaData.getColumnName(i));
        }
        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s", resultSet.getString(i));
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean tableExistsSQL(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, tableName, null);
        return resultSet.next();
    }
}
