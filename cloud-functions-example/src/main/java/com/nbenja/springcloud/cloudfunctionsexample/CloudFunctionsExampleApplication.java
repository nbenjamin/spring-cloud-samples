package com.nbenja.springcloud.cloudfunctionsexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SpringBootApplication
public class CloudFunctionsExampleApplication {

  private List<Employee> employees = Arrays.asList( new Employee("Ryan", 2011), new Employee
      ("Adam", 2013), new Employee("Chris", 2009));

  public static void main(String[] args) {
    SpringApplication.run(CloudFunctionsExampleApplication.class, args);
  }

  Function<Integer, Employee> employeeByYearOfJoin = yearOfJoin -> {
    for (Employee employee : employees) {
      if (employee.getYearOfJoin().equals(yearOfJoin)) {
        return employee;
      }
    }
    return null;
  };

  @Bean
  public Function<Integer, Employee> getEmployee() {
   return year ->  employeeByYearOfJoin.apply(year);
  }
}

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
class Employee {

  private String name;
  private Integer yearOfJoin;
}