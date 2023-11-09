package companyexpensestracker.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import companyexpensestracker.entities.Category;
import companyexpensestracker.repository.CategoryRepo;

@RestController
public class CategoryController {

	@Autowired
	CategoryRepo categoryRepo;

	@CrossOrigin
	@GetMapping("/Categories") 
	public List<Category> getAllCategory() {
		return categoryRepo.findAll();
	}

	@PostMapping("/addCategory") 
	public String addCategory(@RequestBody Category category) {
		categoryRepo.save(category);
		return "Categoryaddedsuccessfully";
	}

	@DeleteMapping("/deletecategory/{code}") 
	public String deleteCategory(@PathVariable("code") String code) {
		Optional<Category> optionalCategory = categoryRepo.findById(code);
		if (optionalCategory.isEmpty()) {
			throw new RuntimeException("id not found");
		}
		categoryRepo.deleteById(code);
		return "deleted succesfully...!";
	}

	@PutMapping("/updatecatname/{code}") 
	public Category updateCategoryName(@RequestBody Category newCategory, @PathVariable("code") String code) {
		Optional<Category> optionalCategory = categoryRepo.findById(code);
		if (optionalCategory.isEmpty()) {
			throw new RuntimeException("ccode not found");
		}
		Category category = optionalCategory.get();
		category.setCategoryName(newCategory.getCategoryname());
		return categoryRepo.save(category);
	}

}
