package com.example.demo;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    static Map<Long, Employee> employees = Collections.synchronizedMap(new HashMap<Long, Employee>());

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getEmployeeList() {

        List<Employee> result = new ArrayList<Employee>(employees.values());

        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute Employee employee) {

        employees.put(employee.getId(), employee);

        return "Added Employee";
    }

}
