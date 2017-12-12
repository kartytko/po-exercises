package lab7.zad;

//import java.sql.*;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.sql.*;
import java.util.LinkedList;


public class DB{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String username;
    private String password;

    class Rekord{
        LinkedList<String> dane = new LinkedList<>();
    }

    LinkedList<Rekord>rekordy = new LinkedList<>();

    public void SetUser(String u){
        username = u;
    }
    public void SetPass(String p){
        password=p;
    }


    public void connect(){
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/kartytko",
                            username, password);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }catch(Exception e){e.printStackTrace();}
    }



    public void getAll(){
        try {
            for(int i=0; i<3; i++){
                if(conn==null){
                    connect();
                }
            }

            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users

            rs = stmt.executeQuery("SELECT * FROM books");
            ResultSetMetaData rsmd = rs.getMetaData();
            int number_of_columns = rsmd.getColumnCount();

            while(rs.next()){
                Rekord rekord_tmp = new Rekord();
                for(int i=1; i<=number_of_columns; i++ ){
                    String name = rs.getString(i);
                    rekord_tmp.dane.add(name);
                    System.out.print(" "+name);
                }
                rekordy.add(rekord_tmp);
                System.out.println("");

            }
        }catch (SQLException ex){
            // handle any errors

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }


    public void byAuthor(String author_){
        try {
            for(int i=0; i<3; i++){
                if(conn==null){
                    connect();
                }
            }

            stmt = conn.createStatement();

            String autor=author_;
            rs = stmt.executeQuery("SELECT * FROM books WHERE author='"+autor+"'");
            ResultSetMetaData rsmd = rs.getMetaData();
            int number_of_columns = rsmd.getColumnCount();

            while(rs.next()){
                for(int i=1; i<=number_of_columns; i++ ){
                    String name = rs.getString(i);
                    System.out.print(" "+name);
                }
                System.out.println("");

            }
        }catch (SQLException ex){
            // handle any errors

        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }


        public void byISBN(String isbn_){
            try {
                for(int i=0; i<3; i++){
                    if(conn==null){
                        connect();
                    }
                }
                stmt = conn.createStatement();

                String ISBN = isbn_;
                rs = stmt.executeQuery("SELECT * FROM books WHERE isbn='"+ISBN+"'");
                ResultSetMetaData rsmd = rs.getMetaData();
                int number_of_columns = rsmd.getColumnCount();

                while(rs.next()){
                    Rekord rekord_tmp = new Rekord();
                    for(int i=1; i<=number_of_columns; i++ ){
                        String name = rs.getString(i);
                        rekord_tmp.dane.add(name);
                        System.out.print(" "+name);
                    }
                    rekordy.add(rekord_tmp);
                    System.out.println("");
                    /*for(int i=1; i<=number_of_columns; i++ ){
                        String name = rs.getString(i);
                        System.out.print(" "+name);
                    }
                    System.out.println("");*/

                }
            }catch (SQLException ex){
                // handle any errors

            }finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) { } // ignore
                    rs = null;
                }

                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException sqlEx) { } // ignore

                    stmt = null;
                }
            }
        }


    public void addBook(String isbn_, String tytul_, String autor_, String rok_){
        try {
            for(int i=0; i<3; i++){
                if(conn==null){
                    connect();
                }
            }
            System.out.println(isbn_+ tytul_+ autor_+ rok_);
            String wyrazenie = "("+isbn_+", '"+tytul_+"', '"+autor_+"', "+rok_+")";
            stmt = conn.createStatement();
            stmt.executeUpdate(
                    "INSERT INTO books VALUES" +wyrazenie);
        }catch(SQLException e){

        }finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }


}

