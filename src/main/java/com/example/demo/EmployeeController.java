package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    static Map<Long, Employee> employees = Collections.synchronizedMap(new HashMap<Long, Employee>());

    // Retrieve list of all employees
    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getEmployeeList() {

        List<Employee> result = new ArrayList<Employee>(employees.values());

        return result;
    }

    // Add a new employee
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute Employee employee) {

        employees.put(employee.getId(), employee);

        return "Employee with ID: " + employee.getId() + " is added to the system.";
    }

    // Delete an employee
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable Long id) {

        employees.remove(id);

        return "Employee with ID: " + id + " is deleted from the system.";
    }

    // Update an employee
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee) {

        Employee original = employees.get(id);

        if (employee.getName() != null) {
            original.setName(employee.getName());
        }

        if (employee.getAge() != null) {
            original.setAge(employee.getAge());
        }

        if (employee.getGender() != null) {
            original.setGender(employee.getGender());
        }

        employees.put(id, original);

        return "Employee with ID: " + id + " is updated.";
    }

}
