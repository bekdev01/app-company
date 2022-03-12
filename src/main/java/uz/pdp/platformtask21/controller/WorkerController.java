package uz.pdp.platformtask21.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.platformtask21.entity.Worker;
import uz.pdp.platformtask21.payload.WorkerDto;
import uz.pdp.platformtask21.payload.ApiResponse;
import uz.pdp.platformtask21.service.WorkerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/worker")
public class WorkerController {
    
    private final WorkerService workerService;

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody WorkerDto workerDto) {
        ApiResponse apiResponse = workerService.add(workerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> get() {
        List<Worker> companies = workerService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        Worker worker = workerService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(worker);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @Valid @RequestBody WorkerDto workerDto) {
        ApiResponse apiResponse = workerService.edit(id, workerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse apiResponse = workerService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    
}
