package com.exampleapi.service;

import com.exampleapi.entity.Registration;
import com.exampleapi.exception.ResourceNotFound;
import com.exampleapi.payload.RegistrationDto;
import com.exampleapi.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    private RegistrationRepository registrationRepository;

    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    public RegistrationDto saveRegistration(RegistrationDto registrationDto){

        //Converting Entities to Dto
        Registration registrations = convertDtoToEntity(registrationDto);
//        Registration registration = new Registration();
//        registration.setName(registrationDto.getName());
//        registration.setEmailId(registrationDto.getEmailId());
//        registration.setMobile(registrationDto.getMobile());
        Registration saveReg = registrationRepository.save(registrations);

        //Converting Dto to Entities
//        RegistrationDto savedRegDto = new RegistrationDto();
//        savedRegDto.setId(saveReg.getId());
//        savedRegDto.setName(saveReg.getName());
//        savedRegDto.setEmailId(saveReg.getEmailId());
//        savedRegDto.setMobile(saveReg.getMobile());
        RegistrationDto savedRegDto = convertEntityToDto(saveReg);
        return savedRegDto;

    }

    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }

    public void updateRegistration(Long id, Registration registration) {
        //Actual record fetched from database
        Optional<Registration> opReg = registrationRepository.findById(id);
        Registration reg =  opReg.get();
        reg.setName(registration.getName());
        reg.setEmailId(registration.getEmailId());
        reg.setMobile(registration.getMobile());
        registrationRepository.save(reg);
    }

    public List<RegistrationDto> getAllRegistrations(
            int pageNo,
            int pageSize,
            String sortBy,
            String sortDir) {
        //use ternary operator for Creating sort object
        Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNo, pageSize, sort); //Sort.by is used to convert the String into sort type
        Page<Registration> records = registrationRepository.findAll(p);
        List<Registration> registrations = records.getContent();
        List<RegistrationDto> collect = registrations.stream().map(r -> convertEntityToDto(r)).collect(Collectors.toList());

//        System.out.println(p.getPageNumber());
//        System.out.println(p.getPageSize());
//        System.out.println(records.getTotalPages());
//        System.out.println(records.getTotalElements());
//        System.out.println(records.isFirst());
//        System.out.println(records.isLast());
//        System.out.println(records.getNumber());
        return collect;
    }

    public Registration getRegistrationById(Long id) {
//        Optional<Registration> opReg = registrationRepository.findById(id);
//        Registration reg  =  opReg.get();
//        return reg;
        Registration registration =registrationRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFound("Record not found with id: "+id)
                );
        return registration;
    }

    Registration convertDtoToEntity(
            RegistrationDto registrationDto
    ){
        Registration registration = modelMapper.map(registrationDto, Registration.class);
        return registration;
    }

    RegistrationDto convertEntityToDto(
            Registration registration
    ){
        RegistrationDto reg = modelMapper.map(registration, RegistrationDto.class);
        return reg;
    }
}
