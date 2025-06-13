package org.boladomiguelevelincitlali.repository.jdbc.Impl;

import org.boladomiguelevelincitlali.model.Genero;
import org.boladomiguelevelincitlali.repository.jdbc.Conexion;
import org.boladomiguelevelincitlali.repository.jdbc.GeneroJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GeneroJdbcImpl extends Conexion<Genero> implements GeneroJdbc {

    private static GeneroJdbc generoJdbc;

    public GeneroJdbcImpl() {
    }

   public  static  GeneroJdbc getInstance(){
        if (generoJdbc == null){
            generoJdbc = new GeneroJdbcImpl();
        }
        return generoJdbc;
   }

   public List<Genero> findAll(){
       Statement statement = null;
       ResultSet resultSet = null;
       List<Genero> generos = null;
       Genero genero = null;
       String query = "SELECT * FROM genero_musical";

       try
       {
           if( !openConnection() )
           {
               System.out.println("Error en conexión");
               return null;
           }
           statement = connection.createStatement( );
           resultSet = statement.executeQuery( query );
           generos = new ArrayList<>( );
           while( resultSet.next() )
           {
               genero = new Genero();
               genero.setId( resultSet.getInt( 1 ) );
               genero.setGenero( resultSet.getString( 2 ) );
               generos.add( genero );
           }
           resultSet.close();
           statement.close();
           closeConnection( );
           return generos;
       }
       catch (SQLException e)
       {
           e.printStackTrace();
       }
       return null;
   }

   public static void main( String a[]){
        GeneroJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach(System.out::println);
   }

   public  boolean save(Genero genero){
       PreparedStatement preparedStatement = null;
       String query = "insert into genero_musical (descripcion) values(?)";
       int res = 0;
       try{
           if (!openConnection()){
               System.out.println("Error de conexion");
               return false;
           }
           preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1, genero.getGenero());
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

   public boolean update(Genero genero){
       PreparedStatement preparedStatement = null;

       String query = "update genero_musical set descripcion = ? where id = ?";
       int res = 0;
       try{
           if( !openConnection( ) )
           {
               System.out.println("Error en conexión");
               return false;
           }
           preparedStatement = connection.prepareStatement( query );
           preparedStatement.setString( 1, genero.getGenero());
           preparedStatement.setInt( 2,genero.getId() );
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
    public boolean delete(Genero genero) {
        PreparedStatement preparedStatement = null;
        String query = "delete from genero_musical where id= ?";
        int res =0;
        try {
            if( !openConnection( ) )
            {
                System.out.println("Error en conexión");
                return false;
            }
            preparedStatement = connection.prepareStatement( query );
            preparedStatement.setInt( 1,genero.getId() );
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

    public Genero findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Genero genero = null;
        String query = "SELECT * FROM genero_musical";


        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                genero = new Genero();
                genero.setId(resultSet.getInt(1));
                genero.setGenero(resultSet.getString(2));
            }

            resultSet.close();
            preparedStatement.close();
            closeConnection();
            return genero;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
