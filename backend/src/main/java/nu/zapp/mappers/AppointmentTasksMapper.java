package nu.zapp.mappers;

import nu.zapp.DTO.AppointmentTasksDTO;
import nu.zapp.entities.AppointmentTasks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentTasksMapper {

    AppointmentTasks destinationToSource(AppointmentTasksDTO dto);

    AppointmentTasksDTO sourceToDestination(AppointmentTasks entity);
}