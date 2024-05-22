package nu.zapp.mappers;

public interface BaseMapper {
    default String combineName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    default String combineLocation(String address, String postalcode, String residence){
        return address + ", " + postalcode + ", " + residence;
    }
}