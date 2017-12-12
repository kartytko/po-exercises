package lab7.zad4;

import java.sql.*;
import java.util.LinkedList;

import static lab7.zad4.PESEL.check;

public class DataBase {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String username;
    private String password;


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
            System.out.println("VendorError: " + ex.getErrorCode());
        }catch(Exception e){e.printStackTrace();}
    }




    public double getBrutto(String pesel_){
        try {
            connect();

            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM kadry WHERE pesel="+pesel_);
            ResultSetMetaData rsmd = rs.getMetaData();

            while(rs.next()){
                return rs.getDouble(2);
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
        return 0;
    }

    public double getNetto(String pesel_){
        try {
            connect();

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM kadry WHERE pesel="+pesel_);
            ResultSetMetaData rsmd = rs.getMetaData();


            while(rs.next()){
                if(rs.getBoolean(3)){ //if isStudent
                   Student pracownik = new Student(rs.getString(1), rs.getDouble(2));
                   return pracownik.wynagrodzenieNetto();
                }else{
                    PracownikEtatowy pracownik = new PracownikEtatowy(rs.getString(1), rs.getDouble(2));
                    return pracownik.wynagrodzenieNetto();
                }

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
        return 0;
    }



    public boolean setBrutto(String pesel_, double brutto_){
        try {
            connect();

            String zapytanie = "UPDATE kadry SET brutto = "+brutto_+" WHERE pesel = '"+pesel_+"'";
            stmt = conn.createStatement();
            stmt.executeUpdate(zapytanie);

            return true;
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
        return false;
    }



    public boolean DodajPracownika(String pesel, double brutto, boolean isStudent){
        try {
            connect();

            PESEL pesel1 = new PESEL(pesel);
            if(!pesel1.check()){
                return false;
            }

            String wyrazenie;
            if(isStudent){
                wyrazenie = "('"+pesel+"', '"+brutto+"', "+"0"+")";
            }else{
                wyrazenie = "('"+pesel+"', '"+brutto+"', "+"1"+")";

            }
            stmt = conn.createStatement();
            stmt.executeUpdate(
                    "INSERT INTO kadry VALUES" + wyrazenie);
            return true;

        }catch(SQLException e){

        }finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
        return false;
    }


    public boolean UsunPracownika(String pesel_){
        try {
            connect();

            String zapytanie = "DELETE FROM kadry WHERE pesel = "+pesel_;
            stmt = conn.createStatement();
            stmt.executeUpdate(zapytanie);

            return true;
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
        return false;
    }



}

