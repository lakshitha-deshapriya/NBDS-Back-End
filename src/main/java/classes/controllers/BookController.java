package classes.controllers;

import assets.environment.Dev;
import classes.entities.BookEntity;
import classes.model.BookModel;
import classes.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookEntity> getAllBooks() {
        return this.bookService.getAllBooks().stream().peek(book -> book.setBookTitle(book.getBookTitle().substring(0, 1).toUpperCase() + book.getBookTitle().substring(1))).collect(Collectors.toList());
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String[] getAllCategories() {
        return this.bookService.getAllCategories().toArray(new String[0]);
    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadBookImage(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = Dev.imageFilePath;
        File image = new File(filePath + file.getOriginalFilename());
        image.createNewFile();
        FileOutputStream fout = new FileOutputStream(image);
        fout.write(file.getBytes());
        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public BookEntity InsertBook(@RequestBody BookModel book) {
        BookEntity bookEntity = new BookEntity(
                book.getBookTitle(),
                book.getDescription(),
                book.getAuthor(),
                (Date) book.getPublishedDate(),
                book.getCategory(),
                book.getPublisher());
        return this.bookService.InsertBook(bookEntity);
    }
}
