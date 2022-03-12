package uz.pdp.platformtask21.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.platformtask21.entity.Company;
import uz.pdp.platformtask21.entity.Department;
import uz.pdp.platformtask21.payload.DepartmentDto;
import uz.pdp.platformtask21.payload.ApiResponse;
import uz.pdp.platformtask21.repository.CompanyRepository;
import uz.pdp.platformtask21.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService implements BaseService<DepartmentDto, Department> {

    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public ApiResponse add(DepartmentDto departmentDto) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());

        if(optionalCompany.isEmpty())
            return new ApiResponse("Company not found", false);

        if(departmentRepository.existsByNameAndCompanyId(departmentDto.getName(), departmentDto.getCompanyId()))
            return new ApiResponse("Department already exists", false);

        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);

        return new ApiResponse("Department successfully added", true);
    }

    @Override
    public ApiResponse edit(Long id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if(optionalDepartment.isEmpty())
            return new ApiResponse("Department not found", false);

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());

        if(optionalCompany.isEmpty())
            return new ApiResponse("Company not found", false);

        if(departmentRepository.existsByNameAndCompanyIdAndIdNot(departmentDto.getName(), departmentDto.getCompanyId(), id))
            return new ApiResponse("Department already exists", false);

        Department editingDepartment = optionalDepartment.get();
        editingDepartment.setName(departmentDto.getName());
        editingDepartment.setCompany(optionalCompany.get());
        departmentRepository.save(editingDepartment);

        return new ApiResponse("Department successfully edited", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if(optionalDepartment.isEmpty())
            return new ApiResponse("Department not found", false);

        departmentRepository.deleteById(id);
        return new ApiResponse("Department successfully deleted", true);
    }
}
