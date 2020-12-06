package com.mnp.store.services;

import com.mnp.store.contracts.BookService;
import com.mnp.store.contracts.dtos.BestSellingBook;
import com.mnp.store.contracts.dtos.BookCategory;
import com.mnp.store.contracts.dtos.SalesPerMonth;
import com.mnp.store.domain.Book;
import com.mnp.store.domain.repository.BookRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Book}.
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findOne(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> findByCategory(Pageable pageable, String category) {
        return bookRepository.findAllByCategory(pageable, category);
    }

    @Override
    public Optional<Book> findOneByIsbn(String isbn) {
        return bookRepository.findOneByIsbn(isbn);
    }

    @Override
    public Page<Book> searchBook(Pageable pageable, String search) {

        Page<Book> result = bookRepository.findAllByTitleContainingIgnoreCase(pageable, search);

        if (result.hasContent())
            return result;

        result = bookRepository.findAllByCategoryContainingIgnoreCase(pageable, search);
        if (result.hasContent())
            return result;

        result = bookRepository.findAllByAuthorContainingIgnoreCase(pageable, search);
        if (result.hasContent())
            return result;

        result = bookRepository.findAllByDescriptionContainingIgnoreCase(pageable, search);
        if (result.hasContent())
            return result;

        return Page.empty();
    }

    @Override
    public List<BestSellingBook> getBestSellers() {
        return bookRepository.getBestSellers(PageRequest.of(0, 10)).toList();
    }

    @Override
    public SalesPerMonth getSalesPerMonth() {
        return bookRepository.getSalesPerMonth();
    }

    @Override
    public List<BookCategory> getAllCategories() {
        return bookRepository.getAllCategories()
                .stream().map(BookCategory::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getByIds(List<Long> bookIds) {
        return bookRepository.findAllById(bookIds);
    }
}


