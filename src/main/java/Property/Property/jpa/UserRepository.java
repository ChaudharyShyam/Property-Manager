package Property.Property.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Property.Property.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}