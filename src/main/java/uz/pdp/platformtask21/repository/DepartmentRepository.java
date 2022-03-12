package uz.pdp.platformtask21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.platformtask21.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByNameAndCompanyId(String name, Long company_id);
    boolean existsByNameAndCompanyIdAndIdNot(String name, Long company_id, Long id);
}
