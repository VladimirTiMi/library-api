package com.example.library_api.service;

import com.example.library_api.exception.BookNotFoundException;
import com.example.library_api.model.Book;
import com.example.library_api.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public Book save(Book book) {
        if (book.getId() != null && bookRepository.existsById(book.getId())) {
            log.warn("Book with id {} already exists. Skipping save.", book.getId());
            throw new IllegalStateException("Book with this id already exists");
        }

        log.info("Saving book: {}", book);
        return bookRepository.save(book);

    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        log.info("Finding book by id: {}", id);
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public void delete(Long id) {
        log.info("Deleting book by id: {}", id);

        if (!bookRepository.existsById(id)) {
            log.warn("Book with id {} not found, cannot delete", id);
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
        log.info("Book with id {} deleted", id);
    }

    public List<Book> searchByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            log.warn("Search by author skipped because author parameter is null or empty");
            return List.of();
        }
        log.info("Searching books by author: {}", author);
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> searchByGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            log.warn("Search by genre skipped because genre parameter is null or empty");
            return List.of();
        }
        log.info("Searching books by genre: {}", genre);
        return bookRepository.findByGenreContainingIgnoreCase(genre);
    }

    public List<Book> searchByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            log.warn("Search by title skipped because title parameter is null or empty");
            return List.of();
        }
        log.info("Searching books by title: {}", title);
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
}

