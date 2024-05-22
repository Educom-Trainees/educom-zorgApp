package nu.zapp.mappers;

import nu.zapp.DTO.AppointmentDTO;
import nu.zapp.DTO.AppointmentDetailDTO;
import nu.zapp.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface AppointmentDetailMapper extends BaseMapper {

    AppointmentDetailMapper INSTANCE = Mappers.getMapper(AppointmentDetailMapper.class);

    Appointment destinationToSource(AppointmentDetailDTO destination);

    @Mapping(target = "name", expression = "java(combineName(source.getCustomer().getFirstName(), source.getCustomer().getLastName()))")
    @Mapping(target = "location",  expression = "java(combineLocation(source.getCustomer().getAddress(),source.getCustomer().getPostalcode(), " +
            "source.getCustomer().getResidence()))")
    @Mapping(target = "employee_name", expression = "java(combineName(source.getEmployee().getFirstName(), source.getEmployee().getLastName()))")
    @Mapping(target = "employee_address",  expression = "java(combineLocation(source.getCustomer().getAddress(),source.getCustomer().getPostalcode(), " +
            "source.getCustomer().getResidence()))")
    @Mapping(target = "customer_id", source = "customer.id")
    @Mapping(target = "employee_id", source = "employee.id")
    @Mapping(target = "customer_tasks", expression = "java(source.getCustomer().getCustomerTasks())")
    @Mapping(target = "appointment_tasks", expression = "java(source.getAppointmentTasks())")
    AppointmentDetailDTO sourceToDestination(Appointment source);

}
