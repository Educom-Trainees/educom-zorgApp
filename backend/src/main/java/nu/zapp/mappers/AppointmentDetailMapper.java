package nu.zapp.mappers;

import nu.zapp.DTO.AppointmentDetailDTO;
import nu.zapp.entities.Appointment;
import nu.zapp.models.CustomerModel;
import nu.zapp.models.EmployeeModel;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BaseMapper.class, AppointmentTasksMapper.class})
public interface AppointmentDetailMapper extends BaseMapper {

    //Models are passed along as reference point for mapping (commented: 03/06/2024
    @Mapping(target = "customer", expression = "java(customerIdToCustomer(destination.getCustomerId(), customerModel))")
    @Mapping(target = "employee", expression = "java(employeeIdToEmployee(destination.getEmployeeId(), employeeModel))")
    Appointment destinationToSource(AppointmentDetailDTO destination, @Context CustomerModel customerModel, @Context EmployeeModel employeeModel);

    @Mapping(target = "name", expression = "java(combineName(source.getCustomer().getFirstName(), source.getCustomer().getLastName()))")
    @Mapping(target = "location",  expression = "java(combineLocation(source.getCustomer().getAddress(),source.getCustomer().getPostalcode(), " +
            "source.getCustomer().getResidence()))")
    @Mapping(target = "employeeName", expression = "java(source.getEmployee() != null ? combineName(source.getEmployee().getFirstName(), source.getEmployee().getLastName()) : null)")
    @Mapping(target = "employeeAddress",  expression = "java(source.getEmployee() != null ? combineLocation(source.getEmployee().getAddress(),source.getEmployee().getPostalcode(), " +
            "source.getEmployee().getResidence()) : null )")
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "customerTasks", expression = "java(source.getCustomer().getCustomerTasks())")
    @Mapping(target = "appointmentTasks", expression = "java(source.getAppointmentTasks())")
    AppointmentDetailDTO sourceToDestination(Appointment source);

}
