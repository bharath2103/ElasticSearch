package com.bootelastic.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "book", type = "metadata")
public class Book {

	private String id;
	private String title;
	private String author;
	private String releaseDate;
	
	public Book() {
	}
	
	public Book(String id, String title, String author, String releaseDate) {
		this.id = id;
		this.setTitle(title);
		this.author = author;
		this.releaseDate = releaseDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	@Override
	public String toString() {
		return "Book{" +
                "id='" + this.id + '\'' +
                ", title='" + this.getTitle() + '\'' +
                ", author='" + this.author + '\'' +
                ", releaseDate='" + this.releaseDate + '\'' +
                '}';
	}

}
