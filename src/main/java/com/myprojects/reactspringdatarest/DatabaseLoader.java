package com.myprojects.reactspringdatarest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner
{
    private final EmployeeRepository employees;
    private final ManagerRepository managers;

    @Autowired
    public DatabaseLoader(EmployeeRepository employeeRepository, ManagerRepository managerRepository)
    {
        this.employees = employeeRepository;
        this.managers = managerRepository;
    }

    @Override
    public void run(String... strings) throws Exception
    {
        Manager tony = this.managers.save(new Manager("tony", "luciani", "ROLE_MANAGER"));
        Manager luka = this.managers.save(new Manager("luka", "modric", "ROLE_MANAGER"));

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("tony",
                                                            "doesn't matter", AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

        this.employees.save(new Employee("Frodo", "Baggins", "ring bearer", tony));
        this.employees.save(new Employee("Bilbo", "Baggins", "burglar", tony));
        this.employees.save(new Employee("Gandalf", "the Grey", "wizard", tony));

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("luka",
                "doesn't matter", AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

        this.employees.save(new Employee("Samwise", "Gamgee", "gardener", luka));
        this.employees.save(new Employee("Meriadoc", "Brandybuck", "pony rider", luka));
        this.employees.save(new Employee("Peregrin", "Took", "pipe smoker", luka));

        SecurityContextHolder.clearContext();
    }
}
