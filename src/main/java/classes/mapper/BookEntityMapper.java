package classes.mapper;

import classes.entities.BookEntity;
import classes.model.BookModel;

public class BookEntityMapper {

    public static BookModel getBookModelFromBookEntity(BookEntity bookEntity )
    {
        BookModel model = new BookModel();
        model.setBookCode( bookEntity.getBookCode() );
        model.setBookId( bookEntity.getBookId() );
        model.setBookTitle( bookEntity.getBookTitle() );
        model.setAuthor( bookEntity.getAuthor() );
        model.setCategory( bookEntity.getCategory() );
        model.setPublisher( bookEntity.getPublisher() );
        model.setDescription( bookEntity.getDescription() );
        model.setImageName( bookEntity.getImage() );
        model.setPublishedDate( bookEntity.getPublishedDate() );
        return model;
    }
}
