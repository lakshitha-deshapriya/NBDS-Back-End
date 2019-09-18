package classes.services;

import classes.cache.BookCache;
import classes.entities.BookEntity;
import classes.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> getAllBooks() {
        BookCache bookCache = BookCache.getInstance();
        if ( !bookCache.isInitialized() ) {
            List<BookEntity> books = this.bookRepository.findAll();
            BookCache.getInstance().addToCache(books);
        }
        return bookCache.getAllBooks();
    }

    public List<String> getAllCategories() {
        return this.getAllBooks().stream().map(BookEntity::getCategory).distinct().collect(Collectors.toList());
    }

    public BookEntity InsertBook(BookEntity book) {
        BookEntity newBook = this.bookRepository.save(book);
        if (newBook != null) {
            BookCache bookCache = BookCache.getInstance();
            bookCache.addToCache(new ArrayList<>(Arrays.asList(newBook)));
        }
        return newBook;
    }

    public ResponseEntity<String> removeBook( int bookId )
	{
		try
		{
			this.bookRepository.deleteById( bookId );
			BookCache.getInstance().removeFromCacheById( bookId );
			return ResponseEntity.status( HttpStatus.OK ).body( "Deletion Success" );
		}
		catch ( Exception e )
		{
			return ResponseEntity.status( HttpStatus.EXPECTATION_FAILED ).body( "Delete failed: " + e.getMessage() );
		}
	}
}
