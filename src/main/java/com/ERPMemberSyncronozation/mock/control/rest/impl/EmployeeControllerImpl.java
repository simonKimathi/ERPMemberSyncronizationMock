package com.ERPMemberSyncronozation.mock.control.rest.impl;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;
import com.ERPMemberSyncronozation.mock.boundary.EmployeeRepository;
import com.ERPMemberSyncronozation.mock.control.rest.EmployeeController;
import com.ERPMemberSyncronozation.mock.control.rest.impl.vm.ResponseVM;
import com.ERPMemberSyncronozation.mock.entity.Employee;
import com.ERPMemberSyncronozation.mock.service.FMClient;

@Stateless
public class EmployeeControllerImpl implements EmployeeController {

  @Inject
  private EmployeeRepository employeeRepository;

  @Inject
  private FMClient fmClient;

  @Override
  public Response getEmployeeByStaffNumber(int staffNumber) {
    Optional<Employee> employeeByStaffNO = employeeRepository.getEmployeeByStaffNO(staffNumber);
    if(employeeByStaffNO.isPresent()){
      return Response.status(Response.Status.OK)
              .entity(employeeByStaffNO.get())
              .build();
    }
    return Response.status(Response.Status.NOT_FOUND)
            .build();
  }

  @Override
  public Response getEmployees() {
    List<Employee> employees = employeeRepository.findAll();
    if(!employees.isEmpty()){
      return Response.status(Response.Status.OK)
              .entity(employees)
              .build();
    }
    return Response.status(Response.Status.NOT_FOUND)
            .build();
  }

  @Override
  public Response registerEmployeeAsAMemberToFundmaster(Employee employee) {
    ResponseVM responseVM = fmClient.registerEmployeeToFundmaster(employee);
    if(responseVM.isSuccess()){
      return Response.status(Response.Status.OK)
              .entity(responseVM)
              .build();
    }
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(responseVM)
            .build();
  }

  @Override
  public Response registerToErp(Employee employee) {
    Employee employee1 = employeeRepository.create(employee);
    if(employee!=null){
      return Response.status(Response.Status.OK)
              .entity(employee1)
              .build();
    }
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .build();
  }
}
