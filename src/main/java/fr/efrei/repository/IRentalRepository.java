package fr.efrei.repository;

import fr.efrei.domain.Rental;
import java.util.List;

public interface IRentalRepository extends IRepository<Rental,Long> {

    public List<Rental> getAll();

}
