package task.memo.repository;

import task.memo.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
