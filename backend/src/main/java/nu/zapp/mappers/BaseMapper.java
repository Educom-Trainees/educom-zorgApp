package nu.zapp.mappers;

import nu.zapp.entities.Customer;
import nu.zapp.entities.Employee;
import nu.zapp.models.CustomerModel;
import nu.zapp.models.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;

public interface BaseMapper {


    default String combineName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    default String combineLocation(String address, String postalcode, String residence){
        return address + ", " + postalcode + ", " + residence;
    }

    default Customer customerIdToCustomer(int id, @Autowired CustomerModel customerModel) {
        return customerModel.findById(id);
    }

    default Employee employeeIdToEmployee(int id, @Autowired EmployeeModel employeeModel) {
        return employeeModel.findById(id);
    }
}