package companyexpensestracker.repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
//import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import companyexpensestracker.entities.Expenditure;

public interface ExpenditureRepo extends JpaRepository<Expenditure, String> {

	// 5
	@Query(value = "select * from Expenditure e where e.CCode =:CCode", nativeQuery = true)
	Page<Expenditure> findByCCode(@Param("CCode") String CCode, PageRequest pageRequest);

	// 6
	@Query("select e from Expenditure e where e.ccode=:Code")
	Page<Expenditure> findExpensesByPaymentMode(@Param("Code") String Code, PageRequest pgRequest);

	// 7
	@Query("select e from Expenditure e where e.expdate between:startDate AND :endDate ORDER BY e.id DESC")
	Page<Expenditure> findByExpensesBetweenDatesSortedByDate(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate, PageRequest pageRequest);

	// 8
	@Query("select sum(e.amount) as total_amount,e.ccode from Expenditure e where month(e.expdate)=:month group by e.ccode")
	List<String> getExpensesForEachCategory(@Param("month") int month);

	// 9
	@Query(value = "SELECT SUM(e.amount) as total_amount,e.deptCode from Expenditure e WHERE e.expdate between :date1 and :date2"
			+ " GROUP BY e.deptCode\n" + "", nativeQuery = true)
	List<Map<Double, String>> getExpenditureOfDeptBetweenDates(@Param("date1") LocalDate date1,
			@Param("date2") LocalDate date2);

	// 10
	List<Expenditure> findByAuthorizedby(String authorizedby);

	// 11
	List<Expenditure> findByDescriptionContaining(String description);

	// 12
	@Query("select e from Expenditure e where e.amount between :min and :max")
	List<Expenditure> findExpensesBetweenMinandMaxAmount(@Param("min") Double min, @Param("max") Double max);

	// 14
	@Query(value = "select d.deptcode as department,sum(e.amount) as total_amount from Department d join Expenditure e on d.deptcode = e.deptcode group by d.deptcode\n ", nativeQuery = true)
	List<Map<String, Double>> getDepartmentAndTotalAmount(String deptcode);

	// 15
	@Query(value = "select c.CategoryName as category, sum(e.amount) as total_amount from Category c join Expenditure e on c.ccode = e.ccode group by c.CategoryName\n", nativeQuery = true)
	List<Map<String, Double>> getCategoryAndTotalAmount(String CCode);

}
