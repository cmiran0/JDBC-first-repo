package bbdd;

import java.sql.*;
import java.util.Properties;

public class Application {

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            showClient(connection);
            showClientStartWith(connection, "J");
            addClient(connection, "Christian", "Miranda Balladares", "24545349W");
            showClient(connection);
            updateClient(connection, 4, "Maria Isabel");
            showClient(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "admin");
        connectionProps.put("password", "S1Sjrl2c1!");
        long before = System.currentTimeMillis();
        //Esta cosa es para establecer la conexion
        conn = DriverManager.getConnection("jdbc:mysql://dam-m06.cdy301tthrim.us-east-1.rds.amazonaws.com/prova", connectionProps);
        //esto es para ver cuanto tiempo tarda
        /*  long after = System.currentTimeMillis();
        System.out.println("Connected to database " + (after - before) + " ms");*/
        return conn;
    }

    //Sobre la conexion hay que abrir un statement

    public static void addClient(Connection connection, String nom, String cognoms, String dni) {
        String query = "INSERT INTO client (nom, cognmos, dni) VALUES ('" + nom + "','" + cognoms + "','" + dni + "')";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updateClient(Connection connection, int id, String newName) {
        String query = "UPDATE client SET nom='" + newName + "' WHERE id=" + id;
        insertUpdateClient(connection, query);
    }

    public static void deleteClient(Connection connection, int id, String newName) {
        String query = "DELETE client WHERE nom='" + newName + "' WHERE id=" + id;
        insertUpdateClient(connection, query);
    }

    public static void insertUpdateClient(Connection connection, String query) {
        System.out.println("query: " + query);
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            //cuando no necesitamos nada que retorne este!
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void showClient(Connection connection) throws SQLException {
        String query = "SELECT id, nom, cognmos, dni FROM client";
        showQueryClient(connection, query);
    }

    public static void showClientStartWith(Connection connection, String startWith) throws SQLException {
        String query = "SELECT id, nom, cognmos, dni FROM client WHERE nom LIKE '" + startWith + "%'";
        showQueryClient(connection, query);
    }


    public static void showQueryClient(Connection con, String query) throws SQLException {
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            //este executeQuery devuelve un resulset
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //se identifica con el nombre del campo
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String cognoms = rs.getString("cognmos");
                String dni = rs.getString("dni");
                System.out.println("Client:\t" + id + "\tnom:\t" + nom + "\tcognom\t" + cognoms + "\ndni:\t" + dni);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
