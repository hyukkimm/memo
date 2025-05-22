package task.memo.service;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import task.memo.domain.Memo;
import org.springframework.stereotype.Service;
import task.memo.repository.MemoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemoService {
    private final MemoRepository memoRepository;


    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }
    //Create , Update
    @Transactional
    public Memo save(Memo memo) {
        return memoRepository.save(memo);
    }
    @Transactional
    public List<Memo> findAll() {
        return memoRepository.findAll();
    }
    @Transactional
    public Optional<Memo> findById(Long id) {
        return memoRepository.findById(id);
    }
    @Transactional
    public void deleteById(Long id) {
        memoRepository.deleteById(id);
    }


}
