package Property.Property.jpa;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Property.Property.entity.Property;
@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {
	@Query("SELECT p FROM Property p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.location) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Property> searchByKeyword(@Param("keyword") String keyword);
	List<Property> findByType(String type);

}
