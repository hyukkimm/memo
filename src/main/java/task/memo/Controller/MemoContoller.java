package task.memo.Controller;

import task.memo.domain.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import task.memo.service.MemoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/memo")
public class MemoContoller {

    private final MemoService Memoservice;

    public MemoContoller(MemoService Memoservice) {
        this.Memoservice = Memoservice;
    }
    //Get,Post는 웹에서도 가능 Put,Delete는 포스트맨같은 테스트로
    @PostMapping
    public Memo createMemo(@RequestBody Memo memo) {
        return Memoservice.save(memo);
    }
    @GetMapping
    public List<Memo> getMemos() {
        return Memoservice.findAll();
    }
    //Get /memo/id 인 메모를 조회, 존재하지 않을시 오류 던지기
    @GetMapping("/{id}")
    public Memo getMemoById(@PathVariable Long id) {
        Optional<Memo> memo = Memoservice.findById(id);
        if(memo.isPresent()) {
            return memo.get();
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 메모를 찾을 수 없습니다.");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id) {
        if (Memoservice.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 메모를 찾을 수 없습니다.");
        }
        else{
            Memoservice.deleteById(id);
        }
    }
    @PutMapping("/{id}")
    public Memo updateMemo(@PathVariable Long id, @RequestBody Memo memo) {

        Optional<Memo> PreviousMemo = Memoservice.findById(id);

        if (PreviousMemo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 메모를 찾을 수 없습니다.");
        }
        else {
            Memo toUpdate = PreviousMemo.get();
            toUpdate.setTitle(memo.getTitle());
            toUpdate.setContent(memo.getContent());
            return Memoservice.save(toUpdate);
        }
    }


}
