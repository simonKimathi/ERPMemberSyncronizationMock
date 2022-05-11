package com.ERPMemberSyncronozation.mock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name="getMemberByStaffNo",query = "select e from Employee e where e.staffNumber=:staffNumber")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @JsonIgnore
    private Long id;
    @Column
    public String title;
    @Column
    public String surname;
    @Column
    public String firstName;
    @Column
    public String otherNames;
    @Column
    public String gender;
    @Column
    public String currentMaritalStatus;
    @Column
    public String nationalIdNumber;
    @Column
    public String taxPinNumber;
    @Column
    public String dateOfBirth;
    @Column
    public String dateOfEmployment;
    @Column
    public String dateOfJoiningScheme;
    @Column
    public int staffNumber;
    @Column
    public String nationalPensionNumber;
    @Column
    public double monthlySalary;
    @Column
    public String designation;
    @Column
    public String address;
    @Column
    public String town;
    @Column
    public String email;
    @Column
    public String cellPhone;
    @Column
    public String nationality;
    @Column
    public String bank;
    @Column
    public String bankBranch;
    @Column
    public String accountName;
    @Column
    public String accountNumber;
    @Column
    public String employmentStatus;


    public Employee(String project_a) {
    }
}