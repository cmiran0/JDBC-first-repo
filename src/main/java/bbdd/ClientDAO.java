package bbdd;

import java.sql.Connection;

public interface ClientDAO {
    void addClient(Connection connection, String nom, String cognoms, String dni);

    void updateClient(Connection connection, int id, String newName);

    void deleteClient(Connection connection, int id, String newName);

    void insertUpdateClient(Connection connection, String query);

    void showClient(Connection connection);

    void showClientStartWith(Connection connection, String startWith);

    void showQueryClient(Connection con, String query);
}
