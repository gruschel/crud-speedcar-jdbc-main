package br.com.speedcar.dao;

import br.com.speedcar.model.Categoria;
import br.com.speedcar.model.SpeedCar;
import java.util.List;
import java.util.Optional;

public interface ISpeedCarDAO {

    SpeedCar save(SpeedCar speedCar);
    SpeedCar update(SpeedCar speedCar);
    void delete(Long id);
    List<SpeedCar> findAll();
    Optional<SpeedCar> findById(Long id);
    List<SpeedCar> findByCategoria(Categoria categoria);
}
