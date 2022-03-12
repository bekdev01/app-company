package uz.pdp.platformtask21.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.platformtask21.entity.Address;
import uz.pdp.platformtask21.entity.Department;
import uz.pdp.platformtask21.entity.Worker;
import uz.pdp.platformtask21.payload.WorkerDto;
import uz.pdp.platformtask21.payload.ApiResponse;
import uz.pdp.platformtask21.repository.AddressRepository;
import uz.pdp.platformtask21.repository.DepartmentRepository;
import uz.pdp.platformtask21.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerService implements BaseService<WorkerDto, Worker> {

    private final WorkerRepository workerRepository;
    private final AddressRepository addressRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<Worker> getAll() {
        return workerRepository.findAll();
    }

    @Override
    public Worker getById(Long id) {
        return workerRepository.findById(id).orElse(null);
    }

    @Override
    public ApiResponse add(WorkerDto workerDto) {
        if(workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber()))
            return new ApiResponse("Worker already exists", false);

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());

        if(optionalDepartment.isEmpty())
            return new ApiResponse("Department not found", false);

        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);

        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(savedAddress);
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);

        return new ApiResponse("Worker successfully added", true);
    }

    @Override
    public ApiResponse edit(Long id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);

        if(optionalWorker.isEmpty())
            return new ApiResponse("Worker not found", false);

        if(workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id))
            return new ApiResponse("Worker already exists", false);

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());

        if(optionalDepartment.isEmpty())
            return new ApiResponse("Department not found", false);

        Worker editingWorker = optionalWorker.get();
        Address editingAddress = editingWorker.getAddress();
        editingAddress.setStreet(workerDto.getStreet());
        editingAddress.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepository.save(editingAddress);

        editingWorker.setName(workerDto.getName());
        editingWorker.setPhoneNumber(workerDto.getPhoneNumber());
        editingWorker.setAddress(savedAddress);
        editingWorker.setDepartment(optionalDepartment.get());
        workerRepository.save(editingWorker);

        return new ApiResponse("Worker successfully edited", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);

        if(optionalWorker.isEmpty())
            return new ApiResponse("Worker not found", false);

        Worker worker = optionalWorker.get();
        Address address = worker.getAddress();
        workerRepository.delete(worker);
        addressRepository.delete(address);

        return new ApiResponse("Worker successfully deleted", true);
    }
}
