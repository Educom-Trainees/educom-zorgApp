package nu.zapp.mappers;

import nu.zapp.DTO.EmployeeDetailDTO;
import nu.zapp.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface EmployeeDetailMapper extends BaseMapper {

    @Mapping(target="firstName", source = "destination.name")
    @Mapping(target="workSchedule", source ="destination.workSchedule")
    Employee destinationToSource(EmployeeDetailDTO destination);

    @Mapping(target="name", expression="java(source.getFirstName())")
    @Mapping(target="lastName", expression="java(source.getLastName())")
    @Mapping(target="workSchedule", expression = "java(source.getWorkSchedule())")
    EmployeeDetailDTO sourceToDestination(Employee source);

}
