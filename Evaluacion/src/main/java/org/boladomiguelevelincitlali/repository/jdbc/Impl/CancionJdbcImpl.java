package org.boladomiguelevelincitlali.repository.jdbc.Impl;

import org.boladomiguelevelincitlali.model.Cancion;
import org.boladomiguelevelincitlali.model.Disco;
import org.eve.pixup.model.*;
import org.boladomiguelevelincitlali.repository.jdbc.CancionJdbc;
import org.boladomiguelevelincitlali.repository.jdbc.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CancionJdbcImpl extends Conexion<Cancion> implements CancionJdbc {

    private static CancionJdbc cancionJdbc;

    private CancionJdbcImpl(){}

    public static CancionJdbc getInstance(){
        if (cancionJdbc == null){
            cancionJdbc = new CancionJdbcImpl();
        }
        return cancionJdbc;
    }

    @Override
    public List<Cancion> findAll() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Cancion> cancions= null;
        Cancion cancion = null;
        String query = "SELECT * FROM cancion";

        try{
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            cancions = new ArrayList<>();

            while (resultSet.next()) {
                cancion = new Cancion();
                cancion.setId(resultSet.getInt("id"));
                cancion.setTitulo(resultSet.getString("titulo"));
                cancion.setDuracion(resultSet.getTime("duracion"));

                int idDisco = resultSet.getInt("id_disco");
                Disco disco = DiscoJdbcImpl.getInstance().findById(idDisco);
                cancion.setDisco(disco);

                cancions.add(cancion);
            }

            resultSet.close();
            statement.close();
            closeConnection();
            return cancions;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void main(String a[]){
        CancionJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach(System.out::println);
    }

    public boolean save(Cancion cancion){
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO cancion (titulo, duracion, id_disco) " +
                "VALUES (?, ?, ?)";
        int res = 0;

        try{
            if (!openConnection()) {
                System.out.println("Error de conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cancion.getTitulo());
            preparedStatement.setTime(2, cancion.getDuracion());
            preparedStatement.setInt(3, cancion.getDisco().getId());

            res = preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();

            return res == 1;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Cancion cancion) {
        PreparedStatement preparedStatement = null;
        String query ="UPDATE cancion SET titulo = ?, duracion = ?, id_disco = ?";
        int res = 0;
        try{
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cancion.getTitulo());
            preparedStatement.setTime(2, cancion.getDuracion());
            preparedStatement.setInt(3, cancion.getDisco().getId());

            res = preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();
            return res == 1;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Cancion cancion) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM cancion WHERE id = ?";
        int res = 0;

        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cancion.getId());
            res = preparedStatement.executeUpdate();

            preparedStatement.close();
            closeConnection();
            return res == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Cancion findById(Integer id){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Cancion cancion = null;
        String query = "SELECT * FROM cancion WHERE id = ?";

        try{
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                cancion = new Cancion();
                cancion.setId(resultSet.getInt("id"));
                cancion.setTitulo(resultSet.getString("titulo"));
                cancion.setDuracion(resultSet.getTime("duracion"));

                int idDisco = resultSet.getInt("id_disco");
                Disco disco = DiscoJdbcImpl.getInstance().findById(idDisco);
                cancion.setDisco(disco);
            }

            resultSet.close();
            preparedStatement.close();
            closeConnection();
            return cancion;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
