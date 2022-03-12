package uz.pdp.platformtask21.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.platformtask21.entity.Company;
import uz.pdp.platformtask21.payload.CompanyDto;
import uz.pdp.platformtask21.payload.ApiResponse;
import uz.pdp.platformtask21.service.CompanyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody CompanyDto companyDto) {
        ApiResponse apiResponse = companyService.add(companyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> get() {
        List<Company> companies = companyService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        Company company = companyService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @Valid @RequestBody CompanyDto companyDto) {
        ApiResponse apiResponse = companyService.edit(id, companyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse apiResponse = companyService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

}
