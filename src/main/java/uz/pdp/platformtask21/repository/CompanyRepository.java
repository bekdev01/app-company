package uz.pdp.platformtask21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.platformtask21.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByCorpName(String corpName);
    boolean existsByCorpNameAndIdNot(String corpName, Long id);
}
