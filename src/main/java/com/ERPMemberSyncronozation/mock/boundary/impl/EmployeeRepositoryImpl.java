package com.ERPMemberSyncronozation.mock.boundary.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ERPMemberSyncronozation.mock.boundary.EmployeeRepository;
import com.ERPMemberSyncronozation.mock.entity.Employee;

import java.util.Optional;

import static java.util.Collections.singletonMap;

@Stateless
public class EmployeeRepositoryImpl extends AbstractBeanImpl<Employee, Long> implements EmployeeRepository {

  @PersistenceContext(unitName = "pu")
  private EntityManager em;

  public EmployeeRepositoryImpl() {
    super(Employee.class);
  }

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  @Override
  public Optional<Employee> getEmployeeByStaffNO(int staffNumber) {
    return findSingleByNamedQuery("getMemberByStaffNo", singletonMap("staffNumber", staffNumber));
  }
}