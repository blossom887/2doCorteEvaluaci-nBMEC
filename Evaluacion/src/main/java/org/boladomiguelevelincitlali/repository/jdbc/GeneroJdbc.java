package org.boladomiguelevelincitlali.repository.jdbc;

import org.boladomiguelevelincitlali.model.Genero;

import java.util.List;

public interface GeneroJdbc {
    List<Genero> findAll();
    boolean save(Genero genero);
    boolean update(Genero genero);
    boolean delete(Genero genero);
    Genero findById(Integer id);
}
