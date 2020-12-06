package com.mnp.store.contracts;

import com.mnp.store.contracts.dtos.BestSellingBook;
import com.mnp.store.contracts.dtos.BookCategory;
import com.mnp.store.contracts.dtos.SalesPerMonth;
import com.mnp.store.domain.Book;
import com.mnp.store.domain.PurchaseItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book save(Book book);

    Page<Book> findAll(Pageable pageable);

    Optional<Book> findOne(Long id);

    void delete(Long id);

    Page<Book> findByCategory(Pageable pageable, String category);

    Optional<Book> findOneByIsbn(String isbn);

    Page<Book> searchBook(Pageable pageable, String search);

    List<BestSellingBook> getBestSellers();

    SalesPerMonth getSalesPerMonth();

    List<BookCategory> getAllCategories();

    List<Book> getByIds(List<Long> itemIds);
}

