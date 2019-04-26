package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test1 {
    private static Statement stmt;

    public static void main(String[] args) {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            stmt = c.createStatement();
            String sql = "CREATE TABLE if not exists COMPANY " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT  ," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            stmt.executeUpdate(sql);
            String sql2 = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (null, 'jeff', 15, 'afracan', 15000.00 );";
            stmt.executeUpdate(sql2);
            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                int age  = rs.getInt("age");
                String  address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println( "ID = " + id +"NAME = " + name+"AGE = " + age +"ADDRESS = " + address+ "SALARY = " + salary );
            }


            stmt.close();
            c.close();


        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

}
