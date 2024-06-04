package nu.zapp.mappers;

import nu.zapp.DTO.AppointmentDTO;
import nu.zapp.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface AppointmentMapper extends BaseMapper {

    Appointment destinationToSource(AppointmentDTO destination);
    List<Appointment> destinationToSource(List<AppointmentDTO> destination);

    @Mapping(target = "name", expression = "java(combineName(source.getCustomer().getFirstName(), source.getCustomer().getLastName()))")
    @Mapping(target = "location", expression = "java(combineLocation(source.getCustomer().getAddress(),source.getCustomer().getPostalcode(), " +
            "source.getCustomer().getResidence()))")
    AppointmentDTO sourceToDestination(Appointment source);
    List<AppointmentDTO> sourceToDestination(List<Appointment> source);

}
