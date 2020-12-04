package com.mnp.store.domain.repository;

import com.mnp.store.contracts.dtos.BestSellingBook;
import com.mnp.store.contracts.dtos.BookCategory;
import com.mnp.store.contracts.dtos.SalesPerMonth;
import com.mnp.store.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByCategory(Pageable pageable, String category);

    Optional<Book> findOneByIsbn(String isbn);

    Page<Book> findAllByTitleIgnoreCase(Pageable pageable, String search);

    Page<Book> findAllByTitleContainingIgnoreCase(Pageable pageable, String search);

    Page<Book> findAllByCategoryContainingIgnoreCase(Pageable pageable, String search);

    Page<Book> findAllByDescriptionContainingIgnoreCase(Pageable pageable, String search);

    Page<Book> findAllByAuthorContainingIgnoreCase(Pageable pageable, String search);

    @Query(value = "select book.id, book.isbn, book.title, book.author, book.imageUrl, book.price, sum(item.quantity) as quantity from Book book inner join PurchaseItem item on book.id = item.book.id group by book.id, book.title order by quantity desc")
    Page<BestSellingBook> getBestSellers(Pageable pageable);


    @Query(value = "select " +
            "ifnull(sum(if(month = 'Jan', total_price, 0)), 0) as 'january', " +
            "ifnull(sum(if(month = 'Feb', total_price, 0)), 0) as 'february'," +
            "ifnull(sum(if(month = 'Mar', total_price, 0)), 0) as 'march'," +
            "ifnull(sum(if(month = 'Apr', total_price, 0)), 0) as 'april'," +
            "ifnull(sum(if(month = 'May', total_price, 0)), 0) as 'may'," +
            "ifnull(sum(if(month = 'Jun', total_price, 0)), 0) as 'june'," +
            "ifnull(sum(if(month = 'Jul', total_price, 0)), 0) as 'july'," +
            "ifnull(sum(if(month = 'Aug', total_price, 0)), 0) as 'august'," +
            "ifnull(sum(if(month = 'Sep', total_price, 0)), 0) as 'september'," +
            "ifnull(sum(if(month = 'Oct', total_price, 0)), 0) as 'october'," +
            "ifnull(sum(if(month = 'Nov', total_price, 0)), 0) as 'november'," +
            "ifnull(sum(if(month = 'Dec', total_price, 0)), 0) as 'december'" +
            " from (" +
            "select DATE_FORMAT(purchased_date, '%b') as month, sum(total) as total_price " +
            "from purchase where purchased_date <= NOW() and purchased_date >= Date_add(Now(), interval - 12 month) " +
            "group by purchased_date, total, DATE_FORMAT(purchased_date, '%m-%Y'))  as sub",
            nativeQuery = true)
    SalesPerMonth getSalesPerMonth();

    @Query("select distinct book.category from Book book where book.category is not null and book.category <> ''")
    List<String> getAllCategories();
}