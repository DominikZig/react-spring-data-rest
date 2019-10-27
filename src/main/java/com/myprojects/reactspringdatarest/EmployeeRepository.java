package com.myprojects.reactspringdatarest;

import org.springframework.data.repository.PagingAndSortingRepository;

//Extending PagingAndSortingRepository which has all CRUD methods already, as well as paging support
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>
{
}
