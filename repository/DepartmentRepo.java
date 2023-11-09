package companyexpensestracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import companyexpensestracker.entities.Department;

public interface DepartmentRepo extends JpaRepository<Department, String> {

}
