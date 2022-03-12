package uz.pdp.platformtask21.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.platformtask21.entity.Address;
import uz.pdp.platformtask21.entity.Company;
import uz.pdp.platformtask21.payload.CompanyDto;
import uz.pdp.platformtask21.payload.ApiResponse;
import uz.pdp.platformtask21.repository.AddressRepository;
import uz.pdp.platformtask21.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService implements BaseService<CompanyDto, Company>{

    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company getById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public ApiResponse add(CompanyDto companyDto) {
        if(companyRepository.existsByCorpName(companyDto.getCorpName()))
            return new ApiResponse("Company already exists", false);
        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);

        Company company = new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(savedAddress);
        companyRepository.save(company);

        return new ApiResponse("Company successfully added", true);
    }

    @Override
    public ApiResponse edit(Long id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isEmpty())
            return new ApiResponse("Company not found", false);

        if(companyRepository.existsByCorpNameAndIdNot(companyDto.getCorpName(), id))
            return new ApiResponse("corpName already exists", false);

        Company editingCompany = optionalCompany.get();
        Address editingAddress = editingCompany.getAddress();

        editingAddress.setStreet(companyDto.getStreet());
        editingAddress.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepository.save(editingAddress);

        editingCompany.setCorpName(companyDto.getCorpName());
        editingCompany.setDirectorName(companyDto.getDirectorName());
        editingCompany.setAddress(savedAddress);
        companyRepository.save(editingCompany);

        return new ApiResponse("Company successfully edited", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);

        if(optionalCompany.isEmpty())
            return new ApiResponse("Company not found", false);

        Company company = optionalCompany.get();
        Address address = company.getAddress();
        companyRepository.delete(company);
        addressRepository.delete(address);
        return new ApiResponse("Company successfully deleted", true);
    }
}
