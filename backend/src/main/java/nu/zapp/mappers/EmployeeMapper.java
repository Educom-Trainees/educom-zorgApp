package nu.zapp.mappers;


import nu.zapp.DTO.EmployeeDTO;
import nu.zapp.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface EmployeeMapper extends BaseMapper {


    Employee destinationToSource(EmployeeDTO destintation);

    List<Employee> destinationToSource(List<EmployeeDTO> destintation);

    @Mapping(target = "name", expression = "java(combineName(source.getFirstName(), source.getLastName()))")
    @Mapping(target = "address", expression = "java(combineLocation(source.getAddress(), source.getPostalcode(), source.getResidence()))")
    @Mapping(target = "active", expression = "java(source.isActive())")
    @Mapping(target = "workSchedule", expression = "java(source.getWorkSchedule())")
    EmployeeDTO sourceToDestination(Employee source);

    List<EmployeeDTO> sourceToDestination(List<Employee> source);

}
