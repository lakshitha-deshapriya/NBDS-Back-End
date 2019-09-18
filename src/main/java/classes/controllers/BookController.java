package classes.controllers;

import classes.entities.BookEntity;
import classes.entities.ImagesCacheEntity;
import classes.mapper.BookEntityMapper;
import classes.model.BookModel;
import classes.services.BookService;
import classes.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("books")
@CrossOrigin(origins = "**")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private StorageService storageService;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookModel> getAllBooks() {
        return this.bookService.getAllBooks().stream()
                .map(BookEntityMapper::getBookModelFromBookEntity)
                .peek(book -> book.setBookTitle(book.getBookTitle().substring(0, 1).toUpperCase() + book.getBookTitle().substring(1)))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String[] getAllCategories() {
        return this.bookService.getAllCategories().toArray(new String[0]);
    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message;
        try {
            storageService.store(file);
            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @RequestMapping(value = "/uploadImageToDb", method = RequestMethod.POST)
    public ResponseEntity<String> handleFileUploadToDb(@RequestParam("file") MultipartFile file) {
        String message;
        try {
            ImagesCacheEntity image = storageService.storeInDb(file);
            if ( image != null )
            {
                message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            }
            else
            {
                message = "FAIL to upload " + file.getOriginalFilename() + "!";
            }
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> InsertBook(@RequestBody BookModel book) {
        BookEntity bookEntity = new BookEntity(
                book.getBookCode(),
                book.getBookTitle(),
                book.getDescription(),
                book.getAuthor(),
                book.getPublishedDate(),
                book.getCategory(),
                book.getPublisher(),
                book.getImageName());
        if (book.getBookId() != -1)
        {
            bookEntity.setBookId( book.getBookId() );
        }
        BookEntity savedBook = this.bookService.InsertBook(bookEntity);
        if (savedBook != null)
        {
            return ResponseEntity.ok()
                    .body( BookEntityMapper.getBookModelFromBookEntity( savedBook ) );
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body( "Saving failed" );
        }
    }

    @RequestMapping(value = "/loadImage", method = RequestMethod.GET)
    public ResponseEntity<Object> loadImage(@RequestParam String imageName) {
        try {
            byte[] image = storageService.loadImage(imageName);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageName + "\"")
                    .body(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Not Found");
    }

    @RequestMapping(value = "/loadImageDb", method = RequestMethod.GET)
    public ResponseEntity<Object> loadImageFromDb(@RequestParam String imageName) {
        try {
            Object image = storageService.loadImageFromDb(imageName);
            if (image != null) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageName + "\"")
                        .body(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Not Found");
    }

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseEntity<String> removeBook( @RequestBody int bookId )
	{
		return this.bookService.removeBook( bookId );
	}
}
