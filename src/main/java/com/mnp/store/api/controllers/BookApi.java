package com.mnp.store.api.controllers;

import com.github.slugify.Slugify;
import com.mnp.store.contracts.BookService;
import com.mnp.store.contracts.dtos.BestSellingBook;
import com.mnp.store.contracts.dtos.BookCategory;
import com.mnp.store.contracts.dtos.SalesPerMonth;
import com.mnp.store.domain.Book;
import com.mnp.store.domain.exceptions.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookApi {

    private static final String ENTITY_NAME = "book";

    private final BookService bookService;

    public BookApi(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) throws URISyntaxException {
        if (book.getId() != null) {
            throw new BadRequestException("A new book cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Book result = bookService.save(book);
        return ResponseEntity.created(new URI("/api/books/" + result.getId()))
                .body(result);
    }

    @PutMapping("/books")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book) {
        if (book.getId() == null) {
            throw new BadRequestException("Invalid id", ENTITY_NAME, "idnull");
        }
        Book result = bookService.save(book);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/books")
    public ResponseEntity<Page<Book>> getAllBooks(Pageable pageable) {
        Page<Book> page = bookService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/books/categories/{category}")
    public ResponseEntity<Page<Book>> getBookByCategory(Pageable pageable, @PathVariable String category) {
        Page<Book> page = bookService.findByCategory(pageable, category);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/books/categories")
    public ResponseEntity<List<BookCategory>> getBookByCategory() {
        List<BookCategory> categories = bookService.getAllCategories();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping(value = "/books/search/{term}")
    public ResponseEntity<Page<Book>> searchBook(Pageable pageable, @PathVariable String term) {
        Page<Book> page = bookService.searchBook(pageable, term);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/books/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@Valid @Size(min = 10, max = 13) @PathVariable String isbn) {
        Optional<Book> book = bookService.findOneByIsbn(isbn);
        return book.map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/books/id/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Optional<Book> book = bookService.findOne(id);
        return book.map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/books/id/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/books/bestsellers")
    public ResponseEntity<List<BestSellingBook>> getBestSellers() {
        List<BestSellingBook> books = bookService.getBestSellers();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/books/sales-per-month")
    public ResponseEntity<SalesPerMonth> getSalesPerMonth() {
        SalesPerMonth salesPerMonth = bookService.getSalesPerMonth();
        return ResponseEntity.ok().body(salesPerMonth);
    }
}


