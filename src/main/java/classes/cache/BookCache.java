package classes.cache;

import classes.entities.BookEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookCache {

    private Map<Integer, BookEntity> BOOK_CACHE;
    private static final BookCache instance = new BookCache();

    public BookCache() {
    }

    public static BookCache getInstance() {
        return instance;
    }

    public List<BookEntity> getAllBooks() {
        return new ArrayList<>(this.BOOK_CACHE.values());
    }

    public void addToCache(List<BookEntity> books) {
        if (this.BOOK_CACHE == null) {
            this.BOOK_CACHE = new HashMap<>();
        }
        this.BOOK_CACHE.putAll(books.stream().collect(Collectors.toMap(BookEntity::getBookId, book -> book)));
    }

    public void removeFromCache(BookEntity book) {
        this.BOOK_CACHE.remove(book.getBookId());
    }

    public boolean isInitialized() {
        return BOOK_CACHE != null;
    }
}

