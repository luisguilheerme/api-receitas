package com.luisguilherme.apireceitas.models.dto;

import java.time.Instant;

import com.luisguilherme.apireceitas.models.embedded.Author;
import com.luisguilherme.apireceitas.models.embedded.Comment;

public class CommentDTO {

	private String text;
	private Instant moment;

	private Author author;

	public CommentDTO() {

	}

	public CommentDTO(String text, Instant moment, Author author) {
		this.text = text;
		this.moment = moment;
		this.author = author;
	}

	public CommentDTO(Comment entity) {
		text = entity.getText();
		moment = entity.getMoment();
		author = entity.getAuthor();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

}
