package nu.zapp.mappers;

import nu.zapp.DTO.AppointmentDetailDTO;
import nu.zapp.entities.Appointment;
import nu.zapp.models.CustomerModel;
import nu.zapp.models.EmployeeModel;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BaseMapper.class})
public interface AppointmentDetailMapper extends BaseMapper {
    AppointmentDetailMapper INSTANCE = Mappers.getMapper(AppointmentDetailMapper.class);

    @Mapping(target = "customer", expression = "java(customerIdToCustomer(destination.getCustomer_id(), customerModel))")
    @Mapping(target = "employee", expression = "java(employeeIdToEmployee(destination.getEmployee_id(), employeeModel))")
    @Mapping(target = "startTime", source = "start_time")
    @Mapping(target = "endTime", source = "end_time")
    @Mapping(target = "registerTime", source = "register_time")
    @Mapping(target = "logoutTime", source = "logout_time")
    @Mapping(target = "appointmentTasks", source = "appointment_tasks")
    Appointment destinationToSource(AppointmentDetailDTO destination, @Context CustomerModel customerModel, @Context EmployeeModel employeeModel);

    @Mapping(target = "name", expression = "java(combineName(source.getCustomer().getFirstName(), source.getCustomer().getLastName()))")
    @Mapping(target = "location",  expression = "java(combineLocation(source.getCustomer().getAddress(),source.getCustomer().getPostalcode(), " +
            "source.getCustomer().getResidence()))")
    @Mapping(target = "employee_name", expression = "java(source.getEmployee() != null ? combineName(source.getEmployee().getFirstName(), source.getEmployee().getLastName()) : null)")
    @Mapping(target = "employee_address",  expression = "java(source.getEmployee() != null ? combineLocation(source.getEmployee().getAddress(),source.getEmployee().getPostalcode(), " +
            "source.getEmployee().getResidence()) : null )")
    @Mapping(target = "customer_id", source = "customer.id")
    @Mapping(target = "employee_id", source = "employee.id")
    @Mapping(target = "customer_tasks", expression = "java(source.getCustomer().getCustomerTasks())")
    @Mapping(target = "appointment_tasks", expression = "java(source.getAppointmentTasks())")
    @Mapping(target = "start_time", source = "startTime")
    @Mapping(target = "end_time", source = "endTime")
    @Mapping(target = "register_time", source = "registerTime")
    @Mapping(target = "logout_time", source = "logoutTime")
    AppointmentDetailDTO sourceToDestination(Appointment source);

}
