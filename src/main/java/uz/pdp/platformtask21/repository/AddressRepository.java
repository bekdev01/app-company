package uz.pdp.platformtask21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.platformtask21.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
