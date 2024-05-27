package nu.zapp.mappers;

import nu.zapp.DTO.AppointmentTasksDTO;
import nu.zapp.entities.AppointmentTasks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentTasksMapper {
    @Mapping(target = "startTime", source = "start_time")
    @Mapping(target = "endTime", source = "end_time")
    AppointmentTasks destinationToSource(AppointmentTasksDTO dto);

    @Mapping(target = "start_time", source = "startTime")
    @Mapping(target = "end_time", source = "endTime")
    AppointmentTasksDTO sourceToDestination(AppointmentTasks entity);
}