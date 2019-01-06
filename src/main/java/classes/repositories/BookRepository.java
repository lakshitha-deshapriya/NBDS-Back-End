package classes.repositories;

import classes.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findByBookTitle( String bookName );
}


