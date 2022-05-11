package com.ERPMemberSyncronozation.mock.boundary;

import com.ERPMemberSyncronozation.mock.entity.Employee;
import java.util.Optional;

public interface EmployeeRepository extends AbstractBean<Employee,Long> {
    Optional<Employee> getEmployeeByStaffNO(int staffNumber);
}
