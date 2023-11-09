package companyexpensestracker.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import companyexpensestracker.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, String> {

}
