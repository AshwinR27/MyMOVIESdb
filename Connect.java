
import java.sql.*;

public class Connect {

// connect method to establish connection to db
    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:./moviesdb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            // System.out.println("Connection to SQLite has been established.");
            return conn;

        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

// create method to create table MOVIES
    public static void create(Connection conn) throws SQLException {
        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE MOVIES " +
                    "(movie_id INTEGER PRIMARY KEY," +
                    " name TEXT, " +
                    " actor TEXT, " +
                    " actress TEXT, " +
                    " director TEXT, " +
                    " year INTEGER);";
            stmt.executeUpdate(sql);
            stmt.close();

        } 
        catch (SQLException e) {
            return;
        }
    }

// insert method to insert records in MOVIES table
    public static void insert(Connection conn) throws SQLException {
        try{
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO MOVIES VALUES(101,'Strange','Benedict','RachelM','Scott',2016);"+
                    "INSERT INTO MOVIES VALUES(102,'Ironman','RobertJR','Gwyneth','JonF',2008);"+
                    "INSERT INTO MOVIES VALUES(103,'Batman1','RobertPt','ZoeKrav','Matt',2022);"+
                    "INSERT INTO MOVIES VALUES(104,'Clicktm','AdamSand','KateBek','Frank',2006);" +
                    "INSERT INTO MOVIES VALUES(105,'Citizen','OrsonWel','Dorothy','Orson',1941);"+
                    "INSERT INTO MOVIES VALUES(106,'Interst','Benedict','AnneHat','Chris',2014);";
            stmt.executeUpdate(sql);
            stmt.close();
        } 
        catch (SQLException e) {
            return;
        }
    }

// select method to query the table MOVIES
    public static void select(Connection conn) throws SQLException {
        int id,year;
        String name,actor,actress,director;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MOVIES;");
            System.out.println("\n\nSELECT * FROM MOVIES;\n\n"+
                "Mov_ID\tName\t\tActor\t\tActress\t\tDirector\tYear");
            while (rs.next()) {

                id = rs.getInt("movie_id");
                name = rs.getString("name");
                actor = rs.getString("actor");
                actress = rs.getString("actress");
                director = rs.getString("director");
                year = rs.getInt("year");
                System.out.println(id + "\t" + name + "\t\t" + actor + "\t" + actress  + "\t\t" + director  + "\t\t" + year);

            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            return;
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT movie_id,name from MOVIES where actor = 'Benedict';");
            System.out.println("\n\nSELECT movie_id,name from MOVIES where actor = 'Benedict';\n\n"+
                "Mov_ID\tName");
            while (rs.next()) {

                id = rs.getInt("movie_id");
                name = rs.getString("name");
                System.out.println(id + "\t" + name );

            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            return;
        }
    }

// main to call all methods
    public static void main(String[] args) throws SQLException {
        Connection conn = connect();
        create(conn);
        insert(conn);
        select(conn);
        conn.close();
    }
}