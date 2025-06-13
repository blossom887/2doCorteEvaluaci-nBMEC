package org.boladomiguelevelincitlali.repository.jdbc.Impl;

import org.boladomiguelevelincitlali.model.Artista;
import org.boladomiguelevelincitlali.repository.jdbc.ArtistaJdbc;
import org.boladomiguelevelincitlali.repository.jdbc.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistaJdbcImpl extends Conexion<Artista> implements ArtistaJdbc {

    private static ArtistaJdbc artistaJdbc;

    private ArtistaJdbcImpl()
    {
    }

    public static ArtistaJdbc getInstance( )
    {
        if( artistaJdbc == null )
        {
            artistaJdbc = new ArtistaJdbcImpl( );
        }
        return artistaJdbc;
    }


    public List<Artista> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Artista> artistas = null;
        Artista artista = null;
        String query = "SELECT * FROM artista";

        try
        {
            if( !openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( query );
            artistas = new ArrayList<>( );
            while( resultSet.next() )
            {
                artista = new Artista();
                artista.setId( resultSet.getInt( 1 ) );
                artista.setArtista( resultSet.getString( 2 ) );
                artistas.add( artista );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return artistas;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main( String a[] )
    {
        ArtistaJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach( System.out::println);
    }

    public boolean save(Artista artista){
        PreparedStatement preparedStatement = null;
        String query = "insert into artista (nombre) values(?)";
        int res = 0;
        try{
            if (!openConnection()){
                System.out.println("Error de conexion");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, artista.getArtista());
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
    public boolean update(Artista artista) {
        PreparedStatement preparedStatement = null;

        String query = "update artista set nombre = ? where id = ?";
        int res = 0;
        try{
            if( !openConnection( ) )
            {
                System.out.println("Error en conexión");
                return false;
            }
            preparedStatement = connection.prepareStatement( query );
            preparedStatement.setString( 1, artista.getArtista());
            preparedStatement.setInt( 2,artista.getId() );
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
    public boolean delete(Artista artista) {
        PreparedStatement preparedStatement = null;
        String query = "delete from artista where id= ?";
        int res =0;
        try {
            if( !openConnection( ) )
            {
                System.out.println("Error en conexión");
                return false;
            }
            preparedStatement = connection.prepareStatement( query );
            preparedStatement.setInt( 1,artista.getId() );
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
    public Artista findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Artista artista = null;
        String query = "SELECT * FROM artista WHERE id = ?";

        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                artista = new Artista();
                artista.setId(resultSet.getInt(1));
                artista.setArtista(resultSet.getString(2));
            }

            resultSet.close();
            preparedStatement.close();
            closeConnection();
            return artista;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }




}
