package classes.repositories;

import classes.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
    UsersEntity findByUserName(String username);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
}
