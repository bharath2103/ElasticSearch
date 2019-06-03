package com.bootelastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bootelastic.model.Book;

public interface BookRepository extends ElasticsearchRepository<Book, String> {

	/*Page<Book> findByAuthor(String author, Pageable pageable);

	List<Book> findByTitle(String title);*/

}
