package org.boladomiguelevelincitlali.repository.jdbc.Impl;

import org.boladomiguelevelincitlali.model.Disquera;
import org.boladomiguelevelincitlali.repository.jdbc.Conexion;
import org.boladomiguelevelincitlali.repository.jdbc.DisqueraJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisqueraJdbcImpl extends Conexion<Disquera> implements DisqueraJdbc {

    private static DisqueraJdbc disqueraJdbc;

    private DisqueraJdbcImpl(){}

    public static DisqueraJdbc getInstance(){
        if (disqueraJdbc == null){
            disqueraJdbc = new DisqueraJdbcImpl();
        }
        return disqueraJdbc;
    }

    public List<Disquera> findAll(){
        Statement statement = null;
        ResultSet resultSet = null;
        List<Disquera> disqueras = null;
        Disquera disquera = null;
        String query = "SELECT * FROM disquera";

        try
        {
            if( !openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( query );
            disqueras = new ArrayList<>( );
            while( resultSet.next() )
            {
                disquera = new Disquera();
                disquera.setId( resultSet.getInt( 1 ) );
                disquera.setDisquera( resultSet.getString( 2 ) );
                disqueras.add( disquera );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return disqueras;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main (String a[]){
        DisqueraJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach(System.out::println);
    }

    @Override
    public boolean save(Disquera disquera) {
        PreparedStatement preparedStatement = null;
        String query = "insert into disquera (nombre) values(?)";
        int res = 0;
        try{
            if (!openConnection()){
                System.out.println("Error de conexion");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, disquera.getDisquera());
            res = preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();
            return res ==1;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Disquera disquera) {
        PreparedStatement preparedStatement = null;

        String query = "update disquera set nombre = ? where id = ?";
        int res = 0;
        try{
            if( !openConnection( ) )
            {
                System.out.println("Error en conexión");
                return false;
            }
            preparedStatement = connection.prepareStatement( query );
            preparedStatement.setString( 1, disquera.getDisquera());
            preparedStatement.setInt( 2,disquera.getId() );
            res = preparedStatement.executeUpdate( );
            preparedStatement.close();
            closeConnection( );
            return res == 1;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Disquera disquera) {
        PreparedStatement preparedStatement = null;
        String query = "delete from disquera where id= ?";
        int res =0;
        try {
            if( !openConnection( ) )
            {
                System.out.println("Error en conexión");
                return false;
            }
            preparedStatement = connection.prepareStatement( query );
            preparedStatement.setInt( 1, disquera.getId() );
            res = preparedStatement.executeUpdate( );
            preparedStatement.close();
            closeConnection( );
            return res == 1;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Disquera findById(Integer id){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Disquera disquera = null;
        String query = "SELECT * FROM disquera";

        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                disquera = new Disquera();
                disquera.setId(resultSet.getInt(1));
                disquera.setDisquera(resultSet.getString(2));
            }

            resultSet.close();
            preparedStatement.close();
            closeConnection();
            return disquera;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
