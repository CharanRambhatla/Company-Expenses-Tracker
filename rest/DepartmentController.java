package companyexpensestracker.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import companyexpensestracker.entities.Department;
import companyexpensestracker.repository.DepartmentRepo;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentRepo depRepo;

	@GetMapping("/departments")
	public List<Department> getDepartments() {
		return depRepo.findAll();
	}

	@PostMapping("/addDepartment")
	public Department addDepartment(@RequestBody Department department) {
		return depRepo.save(department);
	}

	@DeleteMapping("/deletedepartment/{deptcode}")
	public String deleteDepartment(@PathVariable("deptcode") String deptcode) {
		Optional<Department> optionalDepartment = depRepo.findById(deptcode);
		if (optionalDepartment.isEmpty()) {
			throw new RuntimeException("deptcode not found");
		}
		depRepo.deleteById(deptcode);
		return "deleted succesfully...!";
	}

	@PutMapping("/updateDepartment/{deptcode}")
	public Department updateDepartment(@RequestBody Department newDepartment,
			@PathVariable("deptcode") String deptcode) {
		Optional<Department> optionalDepartment = depRepo.findById(deptcode);
		if (optionalDepartment.isEmpty()) {
			throw new RuntimeException("deptcode not found");
		}
		Department department = optionalDepartment.get();
		department.setDeptname(newDepartment.getDeptname());
		return depRepo.save(department);
	}

}
