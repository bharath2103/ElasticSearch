package com.bootelastic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bootelastic.model.Book;
import com.bootelastic.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
/*	@Autowired
	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}*/

	@Override
	public Book save(Book book) {
		return this.bookRepository.save(book);
	}

	@Override
	public void delete(Book book) {
		this.bookRepository.delete(book);
	}

	@Override
	public Optional<Book> findOne(String id) {
		return this.bookRepository.findById(id);
	}

	@Override
	public Iterable<Book> findAll() {
		return this.bookRepository.findAll();
	}

	@Override
	public Page<Book> findByAuthor(String author, PageRequest pageRequest) {
		return this.findByAuthor(author, pageRequest);
	}

	@Override
	public List<Book> findByTitle(String title) {
		/*return this.bookRepository.findByTitle(title);*/
		return null;
	}

}
