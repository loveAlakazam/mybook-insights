package mybook_insight.io.mybook_insight.infrastructure.persistence.book;

import mybook_insight.io.mybook_insight.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<Book, Long> {
}
