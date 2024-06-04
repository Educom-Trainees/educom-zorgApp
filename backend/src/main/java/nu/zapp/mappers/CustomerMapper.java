package nu.zapp.mappers;


import nu.zapp.DTO.CustomerDTO;
import nu.zapp.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface CustomerMapper extends BaseMapper {

    Customer destinationToSource(CustomerDTO destination);
    List<Customer> destinationToSource(List<CustomerDTO> destination);

    @Mapping(target = "name", expression = "java(combineName(source.getFirstName(), source.getLastName()))")
    @Mapping(target = "address", expression = "java(combineLocation(source.getAddress(), source.getPostalcode(), source.getResidence()))")
    @Mapping(target = "inactivationDate", expression="java(source.getInactivationDate())")
    CustomerDTO sourceToDestination(Customer source);
    List<CustomerDTO> sourceToDestination(List<Customer> source);
}
