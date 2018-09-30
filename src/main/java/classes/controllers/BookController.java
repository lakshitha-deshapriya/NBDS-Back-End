package classes.controllers;

import classes.entities.BookEntity;
import classes.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("books")
@CrossOrigin(origins = "*")
public class BookController
{

	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public List<BookEntity> getAllBooks()
	{
		return this.bookService.getAllBooks().stream().peek(book -> book.setBookTitle(book.getBookTitle().substring(0,1).toUpperCase() + book.getBookTitle().substring(1))).collect(Collectors.toList());
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public void InsertingCity( @RequestBody LdCityEntity ldCityEntity )
//	{
//		this.cityServices.InsertCity( ldCityEntity );
//	}
}
