package fr.efrei.repository;

import fr.efrei.domain.Rental;

import java.util.ArrayList;

public interface IRentalRepository extends IRepository<Rental> {

    public ArrayList<Rental> getAll();

}
