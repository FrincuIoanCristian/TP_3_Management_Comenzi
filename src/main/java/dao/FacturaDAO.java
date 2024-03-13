package dao;

import connection.ConnectionFactory;
import model.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Clasa ce extinde AbstractDAO pentru insert si am implementat metoda de findAll
 * @Author: Frincu Ioan-Cristian
 * @Since: Mai 2023
 */
public class FacturaDAO extends AbstractDAO<Factura>{
    /**
     * Metoda ce creaza o lista cu facturile din tabele
     * @return  lista de facturi
     */
    @Override
    public List<Factura> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Factura";
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            List<Factura> lista = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String nume = resultSet.getString(2);
                String produs = resultSet.getString(3);
                int cantitate = resultSet.getInt(4);
                int pret = resultSet.getInt(5);
                int suma = resultSet.getInt(6);
                lista.add(new Factura(id, nume, produs,cantitate,pret,suma));
            }
            return lista;
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, "FacturaDAO:findAll " + e.getMessage());
        }finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
