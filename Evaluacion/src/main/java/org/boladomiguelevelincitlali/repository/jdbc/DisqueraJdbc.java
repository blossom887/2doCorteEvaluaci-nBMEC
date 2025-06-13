package org.boladomiguelevelincitlali.repository.jdbc;

import org.boladomiguelevelincitlali.model.Disquera;

import java.util.List;

public interface DisqueraJdbc {
    List<Disquera> findAll();
    boolean save(Disquera disquera);
    boolean update(Disquera disquera);
    boolean delete(Disquera disquera);
    Disquera findById(Integer id);
}
