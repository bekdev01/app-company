package uz.pdp.platformtask21.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.platformtask21.entity.Department;
import uz.pdp.platformtask21.payload.DepartmentDto;
import uz.pdp.platformtask21.payload.ApiResponse;
import uz.pdp.platformtask21.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/department")
public class DepartmentController {
    
    private final DepartmentService departmentService;

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody DepartmentDto departmentDto) {
        ApiResponse apiResponse = departmentService.add(departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> get() {
        List<Department> companies = departmentService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        Department department = departmentService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(department);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @Valid @RequestBody DepartmentDto departmentDto) {
        ApiResponse apiResponse = departmentService.edit(id, departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse apiResponse = departmentService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    
}
