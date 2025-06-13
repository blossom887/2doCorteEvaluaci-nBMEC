package org.boladomiguelevelincitlali.repository.jdbc.Impl;

import org.boladomiguelevelincitlali.model.Artista;
import org.boladomiguelevelincitlali.model.Disco;
import org.boladomiguelevelincitlali.model.Disquera;
import org.boladomiguelevelincitlali.model.Genero;
import org.boladomiguelevelincitlali.repository.jdbc.Conexion;
import org.boladomiguelevelincitlali.repository.jdbc.DiscoJdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscoJdbcImpl extends Conexion<Disco> implements DiscoJdbc {

    private static DiscoJdbc discoJdbc;

    private DiscoJdbcImpl(){}

    public static DiscoJdbc getInstance() {
        if(discoJdbc == null){
            discoJdbc = new DiscoJdbcImpl();
        }
        return discoJdbc;
    }

    public List<Disco> findAll() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Disco> discos = null;
        Disco disco = null;
        String query = "SELECT * FROM disco";

        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            discos = new ArrayList<>();

            while (resultSet.next()) {
                disco = new Disco();
                disco.setId(resultSet.getInt("id"));
                disco.setTitulo(resultSet.getString("titulo"));
                disco.setPrecio(resultSet.getFloat("precio"));
                disco.setExistencia(resultSet.getInt("existencia"));
                disco.setDescuento(resultSet.getFloat("descuento"));
                disco.setFechaLanzamiento(resultSet.getDate("fecha_lanzamiento"));
                disco.setImagen(resultSet.getString("imagen"));

                int idArtista = resultSet.getInt("id_artista");
                Artista artista = ArtistaJdbcImpl.getInstance().findById(idArtista);
                disco.setArtista(artista);

                int idDisquera = resultSet.getInt("disquera_id");
                Disquera disquera = DisqueraJdbcImpl.getInstance().findById(idDisquera);
                disco.setDisquera(disquera);

                int idGenero = resultSet.getInt("id_genero_musical");
                Genero genero = GeneroJdbcImpl.getInstance().findById(idGenero);
                disco.setGenero(genero);

                discos.add(disco);
            }

            resultSet.close();
            statement.close();
            closeConnection();
            return discos;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void main (String a[]){
        DiscoJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach(System.out::println);
    }


    public boolean save(Disco disco) {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO disco (titulo, precio, existencia, descuento, fecha_lanzamiento, imagen, id_artista, disquera_id, id_genero_musical) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int res = 0;

        try {
            if (!openConnection()) {
                System.out.println("Error de conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, disco.getTitulo());
            preparedStatement.setFloat(2, disco.getPrecio());
            preparedStatement.setInt(3, disco.getExistencia());
            preparedStatement.setFloat(4, disco.getDescuento());
            preparedStatement.setDate(5, new Date(disco.getFechaLanzamiento().getTime()));
            preparedStatement.setString(6, disco.getImagen());
            preparedStatement.setInt(7, disco.getArtista().getId());
            preparedStatement.setInt(8, disco.getDisquera().getId());
            preparedStatement.setInt(9, disco.getGenero().getId());

            res = preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();

            return res == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



    public boolean update(Disco disco) {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE disco SET titulo = ?, precio = ?, existencia = ?, descuento = ?, fecha_lanzamiento = ?, imagen = ?, id_artista = ?, disquera_id = ?, id_genero_musical = ? WHERE id = ?";
        int res = 0;
        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, disco.getTitulo());
            preparedStatement.setFloat(2, disco.getPrecio());
            preparedStatement.setInt(3, disco.getExistencia());
            preparedStatement.setFloat(4, disco.getDescuento());
            preparedStatement.setDate(5, new Date(disco.getFechaLanzamiento().getTime()));
            preparedStatement.setString(6, disco.getImagen());
            preparedStatement.setInt(7, disco.getArtista().getId());
            preparedStatement.setInt(8, disco.getDisquera().getId());
            preparedStatement.setInt(9, disco.getGenero().getId());
            preparedStatement.setInt(10, disco.getId());

            res = preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();
            return res == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean delete(Disco disco) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM disco WHERE id = ?";
        int res = 0;

        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, disco.getId());
            res = preparedStatement.executeUpdate();

            preparedStatement.close();
            closeConnection();
            return res == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Disco findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Disco disco = null;
        String query = "SELECT * FROM disco WHERE id = ?";

        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                disco = new Disco();
                disco.setId(resultSet.getInt("id"));
                disco.setTitulo(resultSet.getString("titulo"));
                disco.setPrecio(resultSet.getFloat("precio"));
                disco.setExistencia(resultSet.getInt("existencia"));
                disco.setDescuento(resultSet.getFloat("descuento"));
                disco.setFechaLanzamiento(resultSet.getDate("fecha_lanzamiento"));
                disco.setImagen(resultSet.getString("imagen"));

                int idArtista = resultSet.getInt("id_artista");
                Artista artista = ArtistaJdbcImpl.getInstance().findById(idArtista);
                disco.setArtista(artista);

                int idDisquera = resultSet.getInt("disquera_id");
                Disquera disquera = DisqueraJdbcImpl.getInstance().findById(idDisquera);
                disco.setDisquera(disquera);

                int idGenero = resultSet.getInt("id_genero_musical");
                Genero genero = GeneroJdbcImpl.getInstance().findById(idGenero);
                disco.setGenero(genero);
            }

            resultSet.close();
            preparedStatement.close();
            closeConnection();
            return disco;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}



