package chap11_Homework1_22112204_박형건;



import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class MySQL_JDBCS_ShoppingmallSystem {
	public static void main(String[] args) throws InstantiationException,
    IllegalAccessException, FileNotFoundException {
		Connection conn_db = null;
		Connection conn_db_academic = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		final String inputFileName1 = "/Users/baghyeong-geon/eclipse-workspace/tbl_member.txt";
		final String inputFileName2= "/Users/baghyeong-geon/eclipse-workspace/tbl_product.txt";
		final String inputFileName3="/Users/baghyeong-geon/eclipse-workspace/tbl_order.txt";
		final String inputFileName4="/Users/baghyeong-geon/eclipse-workspace/tbl_cart.txt";
		final String MySQL_JDBC_driver = "com.mysql.cj.jdbc.Driver";
		final String url_DB = "jdbc:mysql://localhost:3306/";
		final String db_acamdemic_name = "DB_ShoppingMall";
		final String url_DB_Academic = url_DB + db_acamdemic_name;
		final String user_name = "phg";
		final String passwd = "Hyung3059@";

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
				
				
				String tbl_member_name="tbl_member";
				if(tableExistsSQL(conn_db_academic, tbl_member_name))
				{
					System.out.printf("Table (%s) is already existing in Database (%s)\n",
							tbl_member_name, db_acamdemic_name);
				}
				else {
					System.out.printf("Table (%s) is not existing in Database (%s)\n",tbl_member_name, db_acamdemic_name);
					stmt = conn_db_academic.createStatement();	
					sql = "CREATE TABLE " + tbl_member_name + "(name VARCHAR(25), " + "adress VARCHAR(45) "  +");";
					result = stmt.executeUpdate(sql);
					System.out.printf("Table (%s) is created in Database (%s)\n", tbl_member_name, db_acamdemic_name);
					
				}
				fget_and_insert_STData(inputFileName1, conn_db_academic, tbl_member_name);
				printMemberTable(conn_db_academic, tbl_member_name);
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				String tbl_product_name="tbl_product";
				
				if(tableExistsSQL(conn_db_academic, tbl_product_name))
				{
				    System.out.printf("Table (%s) is already existing in Database (%s)\n", tbl_product_name, db_acamdemic_name);
				}
				else {
				    System.out.printf("Table (%s) is not existing in Database (%s)\n",tbl_product_name, db_acamdemic_name);
				    stmt = conn_db_academic.createStatement();    
				    sql = "CREATE TABLE " + tbl_product_name + "(product_name VARCHAR(45), " + "price VARCHAR(45), " + "manufacture VARCHAR(45)" + ");";
				    result = stmt.executeUpdate(sql);
				    System.out.printf("Table (%s) is created in Database (%s)\n", tbl_product_name, db_acamdemic_name);
				}

				fget_and_insert_STData2(inputFileName2, conn_db_academic, tbl_product_name);
				printProductTable(conn_db_academic, tbl_product_name);
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				String tbl_order_name="tbl_order";
				if(tableExistsSQL(conn_db_academic, tbl_order_name))
				{
				    System.out.printf("Table (%s) is already existing in Database (%s)\n", tbl_order_name, db_acamdemic_name);
				}
				else 
				{
				    System.out.printf("Table (%s) is not existing in Database (%s)\n",tbl_order_name, db_acamdemic_name);
				    stmt = conn_db_academic.createStatement();    
				    sql = "CREATE TABLE " + tbl_order_name + "(order_number VARCHAR(45), " + "purchaser VARCHAR(45), " + "item VARCHAR(45)," + "total_price VARCHAR(45)"+");";
				    result = stmt.executeUpdate(sql);
				    System.out.printf("Table (%s) is created in Database (%s)\n", tbl_order_name, db_acamdemic_name);
				}
				fget_and_insert_STData3(inputFileName3, conn_db_academic, tbl_order_name);
				printOrderTable(conn_db_academic, tbl_order_name);
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				String tbl_cart_name="tbl_cart";
				if(tableExistsSQL(conn_db_academic, tbl_cart_name))
				{
				    System.out.printf("Table (%s) is already existing in Database (%s)\n", tbl_cart_name, db_acamdemic_name);

				}
				else
				{
					System.out.printf("Table (%s) is not existing in Database (%s)\n",tbl_cart_name, db_acamdemic_name);
				    stmt = conn_db_academic.createStatement();    
				    sql = "CREATE TABLE " + tbl_cart_name + "(customer VARCHAR(45), " + "product VARCHAR(45) " +");";
				    result = stmt.executeUpdate(sql);
				    System.out.printf("Table (%s) is created in Database (%s)\n", tbl_cart_name, db_acamdemic_name);
				}
				fget_and_insert_STData4(inputFileName4, conn_db_academic, tbl_cart_name);
				printCartTable(conn_db_academic, tbl_cart_name);
				
				
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
	static boolean tableExistsSQL(Connection connection, String tableName)throws SQLException 
	{
		PreparedStatement preparedStatement =
        connection.prepareStatement("SELECT count(*) " + "FROM information_schema.tables " + "WHERE table_name = ?"  + "LIMIT 1;");
		preparedStatement.setString(1, tableName);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		return resultSet.getInt(1) != 0;
	}
	static void fget_and_insert_STData(String fname, Connection conn_stDB, String tbl_name) throws FileNotFoundException, SQLException 
	{
		Scanner fin = new Scanner(new File(fname));
		while (fin.hasNext()) {
			String name = fin.next();
			//String str_stID = fin.next();
			String adress = fin.next();
			Statement stmt = conn_stDB.createStatement();
			ResultSet resultSet = null;
			String sql = "INSERT INTO " + tbl_name + " (name, adress) " + " VALUES ('%s\', \'%s\')".formatted(name, adress)+ ";";
			System.out.printf("SQL_statement = %s\n", sql);
			boolean result = stmt.execute(sql);
			System.out.printf("Inserted member(%s, %s)\n", name, adress);
		}
		fin.close();
	}
	static void fget_and_insert_STData2(String fname, Connection conn_stDB, String tbl_name) throws FileNotFoundException, SQLException 
	{
		Scanner fin = new Scanner(new File(fname));
		while (fin.hasNext()) {
			String product_name = fin.next();
			//String str_stID = fin.next();
			String price = fin.next();
			String manufacture =fin.next();			
			Statement stmt = conn_stDB.createStatement();
			ResultSet resultSet = null;
			String sql = "INSERT INTO " + tbl_name + " (product_name, price, manufacture) " + " VALUES ('%s\', \'%s\', \'%s\')".formatted(product_name, price, manufacture)+ ";";
			System.out.printf("SQL_statement = %s\n", sql);
			boolean result = stmt.execute(sql);
			System.out.printf("Inserted product(%s, %s, %s)\n", product_name, price, manufacture);
		}
		fin.close();
	}
	static void fget_and_insert_STData3(String fname, Connection conn_stDB, String tbl_name) throws FileNotFoundException, SQLException 
	{
		//order_number VARCHAR(45), " + "purchaser VARCHAR(45), " + "item VARCHAR(45)," + "total_price VARCHAR(45
		Scanner fin = new Scanner(new File(fname));
		while (fin.hasNext()) {
			String order_number = fin.next();
			String purchaser = fin.next();
			String item =fin.next();
			String total_price=fin.next();
			Statement stmt = conn_stDB.createStatement();
			ResultSet resultSet = null;
			String sql = "INSERT INTO " + tbl_name + " (order_number, purchaser, item, total_price) " + " VALUES ('%s\', \'%s\', \'%s\', \'%s\')".formatted(order_number, purchaser, item, total_price)+ ";";
			System.out.printf("SQL_statement = %s\n", sql);
			boolean result = stmt.execute(sql);
			System.out.printf("Inserted product(%s, %s, %s, %s)\n", order_number, purchaser, item, total_price);
		}
		fin.close();
	}
	static void fget_and_insert_STData4(String fname, Connection conn_stDB, String tbl_name) throws FileNotFoundException, SQLException 
	{
	    // Corrected column names for tbl_cart table
	    Scanner fin = new Scanner(new File(fname));
	    while (fin.hasNext()) {
	        String customer = fin.next();
	        String product = fin.next();
	        Statement stmt = conn_stDB.createStatement();
	        ResultSet resultSet = null;
	        // Corrected column names in the INSERT INTO statement
	        String sql = "INSERT INTO " + tbl_name + " (customer, product) " + " VALUES ('%s\', \'%s\')".formatted(customer, product) + ";";
	        System.out.printf("SQL_statement = %s\n", sql);
	        boolean result = stmt.execute(sql);
	        System.out.printf("Inserted member(%s, %s)\n", customer, product);
	    }
	    fin.close();
	}

	static void printMemberTable(Connection conn_stDB, String tbl_name) throws SQLException {
		Statement stmt = conn_stDB.createStatement();
		ResultSet resultSet = null;
		String sql = "SELECT * FROM " + tbl_name + ";";
		resultSet = stmt.executeQuery(sql);
		printMemberRecords(resultSet, "name", "adress"); 
		
	}
	static void printProductTable(Connection conn_stDB, String tbl_name) throws SQLException 
	{
		Statement stmt = conn_stDB.createStatement();
		ResultSet resultSet = null;
		String sql = "SELECT * FROM " + tbl_name + ";";
		resultSet = stmt.executeQuery(sql);
		printProductRecords(resultSet, "product_name", "price","manufacture"); 
		
	}
	static void printOrderTable(Connection conn_stDB, String tbl_name) throws SQLException 
	{
		Statement stmt = conn_stDB.createStatement();
		ResultSet resultSet = null;
		String sql = "SELECT * FROM " + tbl_name + ";";
		resultSet = stmt.executeQuery(sql);
		printOrderRecords(resultSet, "order_number", "purchaser","item", "total_price"); 
		
	}
	static void printCartTable(Connection conn_stDB, String tbl_name) throws SQLException {
		Statement stmt = conn_stDB.createStatement();
		ResultSet resultSet = null;
		String sql = "SELECT * FROM " + tbl_name + ";";
		resultSet = stmt.executeQuery(sql);
		printCartRecords(resultSet, "customer", "product");
	}
		
	static void printMemberRecords(ResultSet srs, String col1, String col2) throws SQLException
	{
		String str_adress = null;
		String str_name = null;
		
		System.out.printf("%6s %8s\n", col1, col2);
		while (srs.next()) {
			if (col1 != "") {
				str_name = new String(srs.getString("name")); }
			if (col2 != "") {
				str_adress = new String(srs.getString("adress")); }
		
			System.out.printf("%6s %8s\n",  str_name, str_adress);
		}
	}
	static void printProductRecords(ResultSet srs, String col1, String col2, String col3) throws SQLException
	{
		String str_product = null;
		String str_price = null;
		String str_manufacture=null;
		System.out.printf("%6s %4s %8s\n", col1, col2,col3);
		while (srs.next()) {
			if (col1 != "") {
				str_product = new String(srs.getString("product_name")); }
			if (col2 != "") {
				str_price = new String(srs.getString("price")); }
			if(col3!="")
			{
				str_manufacture=new String(srs.getString("manufacture"));
			}
		
			System.out.printf("%6s %8s %8s\n",  str_product, str_price, str_manufacture);
		}
	}
	static void printOrderRecords(ResultSet srs, String col1, String col2, String col3, String col4) throws SQLException
	{
		String str_number = null;
		String str_purchaser = null;
		String str_item=null;
		String str_total=null;
		System.out.printf("%6s %4s %8s %14s\n", col1, col2,col3,col4);
		while (srs.next()) {
			if (col1 != "") {
				str_number = new String(srs.getString("order_number")); }
			if (col2 != "") {
				str_purchaser = new String(srs.getString("purchaser")); }
			if(col3!="")
			{
				str_item=new String(srs.getString("item"));
			}
			if(col4!="")
			{
				str_total=new String(srs.getString("total_price"));
			}
		
			System.out.printf("%4s %16s %12s %8s\n", str_number, str_purchaser, str_item, str_total);
		}
	}
	static void printCartRecords(ResultSet srs, String col1, String col2) throws SQLException
	{
		String str_customer = null;
		String str_product = null;
		
		System.out.printf("%6s %8s\n", col1, col2);
		while (srs.next()) {
			if (col1 != "") {
				str_customer = new String(srs.getString("customer")); }
			if (col2 != "") {
				str_product = new String(srs.getString("product")); }
		
			System.out.printf("%6s %10s\n",  str_customer, str_product);
		}
	}

}
