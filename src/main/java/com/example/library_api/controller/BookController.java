package com.example.library_api.controller;

import com.example.library_api.exception.BookNotFoundException;
import com.example.library_api.model.Book;
import com.example.library_api.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> search(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String title
    ) {
        if (author != null) return ResponseEntity.ok(bookService.searchByAuthor(author));
        if (genre != null) return ResponseEntity.ok(bookService.searchByGenre(genre));
        if (title != null) return ResponseEntity.ok(bookService.searchByTitle(title));
        return ResponseEntity.ok(bookService.findAll());
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(BookNotFoundException.class)
        public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception ex) {
            return ResponseEntity.status(500).body("An error occured: " + ex.getMessage());
        }
    }

}

