package com.myprojects.reactspringdatarest;

import org.springframework.data.repository.CrudRepository;

//Extending CrudRepository which has all CRUD methods already
public interface EmployeeRepository extends CrudRepository<Employee, Long>
{
}
