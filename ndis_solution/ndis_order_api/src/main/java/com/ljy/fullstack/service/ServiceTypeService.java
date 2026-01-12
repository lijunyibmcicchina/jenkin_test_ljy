package com.ljy.fullstack.service;
import com.ljy.fullstack.pojo.ServiceType;
import com.ljy.fullstack.pojo.dto.ServiceTypeResponseDto;
import com.ljy.fullstack.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    @Autowired
    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository){
        this.serviceTypeRepository = serviceTypeRepository;
    }
    public List<ServiceTypeResponseDto> getAllServiceTypes() {
        List<ServiceType> serviceTypes = serviceTypeRepository.findAll();
        List<ServiceTypeResponseDto> serviceTypeResponseDtos = serviceTypes.stream()
                .map(serviceType ->
                        new ServiceTypeResponseDto(serviceType.getServiceTypeId(), serviceType.getServiceTypeName(), serviceType.getDescription()))
                .collect(Collectors.toList());

        return serviceTypeResponseDtos;
    }
}
