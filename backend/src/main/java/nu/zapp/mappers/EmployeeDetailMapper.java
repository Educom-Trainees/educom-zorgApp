package nu.zapp.mappers;

import nu.zapp.DTO.EmployeeDetailDTO;
import nu.zapp.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface EmployeeDetailMapper extends BaseMapper {

    @Mapping(target="name", expression="java(source.getFirstName())")
    @Mapping(target="last_name", expression="java(source.getLastName())")
    EmployeeDetailDTO sourceToDestination(Employee source);

}
