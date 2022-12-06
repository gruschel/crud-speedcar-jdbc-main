package br.com.speedcar.dao;

import br.com.speedcar.model.Categoria;
import br.com.speedcar.model.History;
import br.com.speedcar.model.SpeedCar;
import java.util.List;
import java.util.Optional;

public interface IHistoryDAO {
    History save(History history);
    History update(History history);
    void delete(Long id);
    List<History> findAll(boolean showInactive);
    Optional<History> findById(Long id);
    List<History> findByCustomer(String customerFullName, boolean showInactive);
    void InitTable(boolean forceInit);

}
