package com.bootelastic.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.bootelastic.BootElasticApplication;
import com.bootelastic.model.Book;
import com.bootelastic.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootElasticApplication.class)
public class BootElasticApplicationTests {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ElasticsearchTemplate esTemplate;
	
	@Before
	public void before() {
		esTemplate.deleteIndex(Book.class);
		esTemplate.createIndex(Book.class);
		esTemplate.putMapping(Book.class);
		esTemplate.refresh(Book.class);
	}
	
	@Test
	public void testSave() {
		Book book = new Book("1001", "The Greatness", "Greater", "13-May-1988");
		Book testBook = bookService.save(book);
		
		assertNotNull(testBook.getId());
		assertEquals(testBook.getTitle(), book.getTitle());
	}
	
	

}
