package com.ljy.fullstack.service;

import com.ljy.fullstack.pojo.NDISService;
import com.ljy.fullstack.pojo.ServiceType;
import com.ljy.fullstack.pojo.User;
import com.ljy.fullstack.pojo.dto.CreateServiceRequestDto;
import com.ljy.fullstack.pojo.dto.ServiceResponseDto;
import com.ljy.fullstack.pojo.exception.ResourceNotFoundException;
import com.ljy.fullstack.pojo.notification.Notification;
import com.ljy.fullstack.pojo.notification.NotificationFactory;
import com.ljy.fullstack.repository.NDISServiceRepository;
import com.ljy.fullstack.repository.ServiceTypeRepository;
import com.ljy.fullstack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NDISServiceService {
    private final ServiceTypeRepository serviceTypeRepository;
    private final UserRepository userRepository;
    private final NDISServiceRepository ndisServiceRepository;
//    private OrderEventService orderEventService;

    @Autowired
    public NDISServiceService(ServiceTypeRepository serviceTypeRepository, UserRepository userRepository, NDISServiceRepository ndisServiceRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
        this.userRepository = userRepository;
        this.ndisServiceRepository = ndisServiceRepository;
//        this.orderEventService = orderEventService;
    }

    public ServiceResponseDto createNDISService(CreateServiceRequestDto createServiceRequestDto) {
        NDISService ndisService = new NDISService();

        // get ServiceType and assign to NDISService entity
        ServiceType serviceType = serviceTypeRepository.findById(createServiceRequestDto.getServiceTypeId()).orElseThrow(() -> new ResourceNotFoundException("ServiceType not found"));
        User user = userRepository.findById(createServiceRequestDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        ndisService.setServiceName(createServiceRequestDto.getServiceName());
        ndisService.setServiceType(serviceType);
        ndisService.setDescription(createServiceRequestDto.getDescription());
        ndisService.setPrice(createServiceRequestDto.getPrice());
        ndisService.setProvider(user);

        NDISService newService = ndisServiceRepository.save(ndisService);

        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        serviceResponseDto.setServiceId(newService.getServiceId());
        serviceResponseDto.setServiceName(newService.getServiceName());
        serviceResponseDto.setDescription(newService.getDescription());
        serviceResponseDto.setPrice(newService.getPrice());
        serviceResponseDto.setServiceTypeName(serviceType.getServiceTypeName());
        serviceResponseDto.setProviderName(newService.getProvider().getFullName());

        Notification notification = NotificationFactory.createNotification("SMS");
        notification.send("test@example.com", "New Service");

//        orderEventService.recordOrderEvent("aaa", "BBB", "ccc");

        return serviceResponseDto;
    }
}
