package companyexpensestracker.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import companyexpensestracker.entities.Expenditure;
import companyexpensestracker.repository.ExpenditureRepo;

@RestController
public class ExpenditureController {

	@Autowired
	ExpenditureRepo expRepo;


	@GetMapping("/expenditures") 
	public List<Expenditure> getAllExpenditure() {
		return expRepo.findAll();
	}

	@PostMapping("/addExpenditure") 
	public String addExpenditure(@RequestBody Expenditure expenditure) {
		expRepo.save(expenditure);
		return "Expenditure added successfully";
	}

	@DeleteMapping("/deleteexp/{id}")
	public String deleteExpenditure(@PathVariable("id") String id) {
		Optional<Expenditure> optionalExpenditure = expRepo.findById(id);
		if (optionalExpenditure.isPresent()) {
			expRepo.deleteById(id);
			return "Expenditure deleted";
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expenditure not found");
	}

	@PutMapping("/updateexp/{id}")
	public Expenditure updateName(@RequestBody Expenditure newExpenditure, @PathVariable("id") String id) {
		Optional<Expenditure> optionalExpenditure = expRepo.findById(id);
		if (optionalExpenditure.isEmpty()) {
			throw new RuntimeException("Id not found");
		}
		Expenditure expenditure = optionalExpenditure.get();
		expenditure.setCCode(newExpenditure.getCCode());
		return expRepo.save(expenditure);
	}

	// 10 
	@GetMapping("/getExpensesByAuthorizedBy/{empName}")
	public List<Expenditure> getExpensesByAuthorizedBy(@PathVariable("empName") String empName) {
		List<Expenditure> expenditures = expRepo.findByAuthorizedby(empName);
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found....!");
		else
			return expenditures;

	}

	// 11 
	@GetMapping("/getExpensesByDescofGivenString/{name}")
	public List<Expenditure> getExpensesByDescOfGivenString(@PathVariable("name") String name) {
		List<Expenditure> expenditures = expRepo.findByDescriptionContaining(name);
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found...!");
		else
			return expenditures;
	}

	// 12 
	@GetMapping("/getExpensesBetweenMinandMaxAmount/{min}/{max}")
	public List<Expenditure> getExpensesBetweenMinandMaxAmount(@PathVariable("min") Double min,
			@PathVariable("max") Double max) {
		List<Expenditure> expenditures = expRepo.findExpensesBetweenMinandMaxAmount(min, max);
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found....!");
		else
			return expenditures;
	}

	// 9
	@GetMapping("/getExpensesofDeptBetweenDates/{date1}/{date2}")
	public List<Map<Double, String>> getExpensesOfDeptBetweenDates(@PathVariable("date1") LocalDate date1,
			@PathVariable("date2") LocalDate date2) {
		List<Map<Double, String>> expenditures = expRepo.getExpenditureOfDeptBetweenDates(date1, date2);
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found....!");
		else
			return expenditures;
	}

	// 15 
	@GetMapping("/getCategoryAndTotalAmount/{CCode}")
	public List<Map<String, Double>> getCategoriesAndTotalAmount(@PathVariable("CCode") String CCode) {
		List<Map<String, Double>> expenditures = expRepo.getCategoryAndTotalAmount(CCode);
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found...!");
		else
			return expenditures;
	}

	// 14 
	@GetMapping("/getDepartmentAndTotalAmount/{deptcode}")
	public List<Map<String, Double>> getDepartmentAndTotalAmount(@PathVariable("deptcode") String deptcode) {
		List<Map<String, Double>> expenditures = expRepo.getDepartmentAndTotalAmount(deptcode);
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found...!");
		else
			return expenditures;
	}

	// 5  
	@GetMapping("/findByCCode/{CCode}")
	public Page<Expenditure> findByCCode(@PathVariable("CCode") String CCode ) {
		Page<Expenditure> expenditures = expRepo.findByCCode(CCode,PageRequest.of(0, 5,Sort.by("id").ascending()));
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found...!");
		else
			return expenditures;
	}

	// 6 
	@GetMapping("/findExpensesByPaymentMode/{Code}")
	public Page<Expenditure> findExpenses(@PathVariable("Code") String Code) {
		Page<Expenditure> expenditures = expRepo.findExpensesByPaymentMode(Code, PageRequest.of(0, 5,Sort.by("id").ascending()));
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found...!");
		else
			return expenditures;
	}

	// 7
	@GetMapping("findByExpensesBetweenDatesSortedByDate/{startDate}/{endDate}")
	public Page<Expenditure> findByExpensesBetweenDatesSortedByDate(@PathVariable("startDate") LocalDate startDate,
			@PathVariable("endDate") LocalDate endDate) {
		Page<Expenditure> expenditures = expRepo.findByExpensesBetweenDatesSortedByDate(startDate, endDate,PageRequest.of(0, 5,Sort.by("expdate")));
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found...!");
		else
			return expenditures;
	}

	//8 
	@GetMapping("/getExpensesForEachCategory/{month}")
	public List<String> getExpensesForEachCategory(@PathVariable("month") int month) {
		var expenditures = expRepo.getExpensesForEachCategory(month);
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found...!");
		else
			return expenditures;

	}

}
