package org.boladomiguelevelincitlali.repository.jdbc;

import org.boladomiguelevelincitlali.model.Artista;

import java.util.List;

public interface ArtistaJdbc {
    List<Artista> findAll();
    boolean save(Artista artista);
    boolean update(Artista artista);
    boolean delete(Artista artista);
    Artista findById(Integer id);
}

