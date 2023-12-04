package fr.efrei.repository;
import fr.efrei.domain.Rental;
import java.util.ArrayList;
import java.util.List;

public class RentalRepository implements IRentalRepository{

    private static RentalRepository repository = null;

    private List<Rental> rentals = null;

    private RentalRepository(){
        this.rentals = new ArrayList<Rental>();
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
        boolean success = rentals.add(rental);
        if (success){
            return rental;
        }
        else{
            return null;
        }
    }

    @Override
    public Rental read(Long rentalId){
        for(Rental rental : rentals){
            if(rental.getRentalId() == rentalId){
                return rental;
            }
        }
        return null;
    }

    @Override
    public Rental update(Rental rental){
        for(Rental r : rentals){
            if(r.getRentalId() == rental.getRentalId()){
                rentals.remove(r);
                rentals.add(rental);
                return rental;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long rentalId){
        Rental rentalToDelete = read(rentalId);
        boolean success = rentals.remove(rentalToDelete);
        return success;
    }

    @Override
    public List<Rental> getAll(){
        return rentals;
    }
}
