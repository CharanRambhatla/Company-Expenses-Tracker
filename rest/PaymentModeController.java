package companyexpensestracker.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import companyexpensestracker.entities.Category;
import companyexpensestracker.entities.Department;
import companyexpensestracker.entities.PaymentMode;
import companyexpensestracker.repository.PaymentModeRepo;

@RestController
public class PaymentModeController {

	@Autowired
	private PaymentModeRepo paymrepo;

	@GetMapping("/paymentmodes")
	public List<PaymentMode> getAllPaymentmodes() {
		return paymrepo.findAll();
	}

	@PostMapping("/addpayment")
	public String addPaymentMode(@RequestBody PaymentMode newPaymentMode) {
		paymrepo.save(newPaymentMode);
		return "PaymentMode added successfully";
	}

	@PutMapping("/update/{code}")
	public PaymentMode updateName(@RequestBody PaymentMode newPaymentmode, @PathVariable("code") String code) {
		Optional<PaymentMode> optionalPaymentMode = paymrepo.findById(code);
		if (optionalPaymentMode.isEmpty()) {
			throw new RuntimeException("Code not found");
		}
		PaymentMode paymentmode = optionalPaymentMode.get();
		paymentmode.setName(newPaymentmode.getName());
		return paymrepo.save(paymentmode);

	}

	@DeleteMapping("/delete/{Code}")
	public String deletePaymentMode(@PathVariable("Code") String Code) {
		Optional<PaymentMode> optionalPaymentMode = paymrepo.findById(Code);
		if (optionalPaymentMode.isPresent()) {
			paymrepo.deleteById(Code);
			return "PaymentMode deleted";
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "paymentmode not found");
	}

}
