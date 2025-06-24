package mybook_insight.io.mybook_insight.domain.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mybook_insight.io.mybook_insight.domain.common.BaseEntity;

@Entity
@Table(name = "book")
@Getter
@RequiredArgsConstructor
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name= "name", unique = true)
    private String name;

    @Column(name= "author", nullable = false)
    private String author;

    @Column(name= "publisher", nullable = false)
    private String publisher;

    @Column(name= "total_pages", nullable = false)
    private int totalPages;

    @Column(name= "detail_contents")
    private int detailContents;

    @Column(name= "thumbnail")
    private int thumbnail;

}
