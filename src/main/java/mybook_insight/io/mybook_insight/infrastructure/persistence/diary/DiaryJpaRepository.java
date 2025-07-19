package mybook_insight.io.mybook_insight.infrastructure.persistence.diary;

import mybook_insight.io.mybook_insight.domain.diary.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryJpaRepository extends JpaRepository<Diary, Long> {
}
