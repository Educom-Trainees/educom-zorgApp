package nu.zapp.mappers;

import nu.zapp.DTO.CustomerDetailDTO;
import nu.zapp.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface CustomerDetailMapper extends BaseMapper{

    CustomerDetailMapper INSTANCE = Mappers.getMapper(CustomerDetailMapper.class);

    Customer destinationToSource(CustomerDetailDTO destination);

    @Mapping(target="name", expression="java(source.getFirstName())")
    @Mapping(target = "tasks", expression = "java(source.getCustomerTasks())")
    CustomerDetailDTO sourceToDestination(Customer source);

}
