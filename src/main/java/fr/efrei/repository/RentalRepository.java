package fr.efrei.repository;

import fr.efrei.domain.Rental;

import java.util.ArrayList;

public class RentalRepository implements IRentalRepository{

    private static RentalRepository repository = null;

    private ArrayList<Rental> rentalDB = null;

    private RentalRepository(){
        this.rentalDB = new ArrayList<Rental>();
    }

    // Singleton !
    public static RentalRepository getRepository(){
        if(repository == null){
            repository = new RentalRepository();
        }
        return repository;
    }

    @Override
    public Rental create(Rental rental){
        boolean s = rentalDB.add(rental);
        if (s){
            return rental;
        }
        else{
            return null;
        }
    }

    @Override
    public Rental read(Rental rental){
        for(Rental o : rentalDB){
            if(o.getRentalId() == rental.getRentalId()){
                return o;
            }
        }
        return null;
    }

    @Override
    public Rental update(Rental newRental){
        Rental oldRental = read(newRental);
        if (oldRental == null){
            return null;
        }
        boolean s = delete(newRental);
        if(s){
            boolean addingS = rentalDB.add(newRental);
            if(addingS){
                return newRental;
            }
            else{
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Rental rental){
        Rental deletedRental = read(rental);
        boolean s = rentalDB.remove(deletedRental);
        return s;
    }

    @Override
    public ArrayList<Rental> getAll(){
        return rentalDB;
    }
}
