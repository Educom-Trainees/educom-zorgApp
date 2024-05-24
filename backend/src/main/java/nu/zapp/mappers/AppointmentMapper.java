package nu.zapp.mappers;

import nu.zapp.DTO.AppointmentDTO;
import nu.zapp.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface AppointmentMapper extends BaseMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    Appointment destinationToSource(AppointmentDTO destination);
    List<Appointment> destinationToSource(List<AppointmentDTO> destination);

    @Mapping(target = "name", expression = "java(combineName(source.getCustomer().getFirstName(), source.getCustomer().getLastName()))")
    @Mapping(target = "location", expression = "java(combineLocation(source.getCustomer().getAddress(),source.getCustomer().getPostalcode(), " +
            "source.getCustomer().getResidence()))")
    @Mapping(target = "start_time", source = "startTime")
    @Mapping(target = "end_time", source = "endTime")
    @Mapping(target = "register_time", source = "registerTime")
    @Mapping(target = "logout_time", source = "logoutTime")
    AppointmentDTO sourceToDestination(Appointment source);
    List<AppointmentDTO> sourceToDestination(List<Appointment> source);

}
