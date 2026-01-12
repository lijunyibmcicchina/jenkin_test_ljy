package com.ljy.fullstack.controller;

import com.liy.fullstack.CustomLogger;
import com.ljy.fullstack.pojo.dto.CreateServiceRequestDto;
import com.ljy.fullstack.pojo.dto.ServiceResponseDto;
import com.ljy.fullstack.pojo.http.ResponseMessage;
import com.ljy.fullstack.service.NDISServiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ndis-service")
public class NDISServiceController {
    private final NDISServiceService ndisServiceService;
    public NDISServiceController(NDISServiceService ndisServiceService) {
        this.ndisServiceService = ndisServiceService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<ServiceResponseDto>> createNDISService(@RequestBody @Valid CreateServiceRequestDto createServiceRequestDto) {
        CustomLogger logger = CustomLogger.getInstance();
        logger.info("Initialized Create NDIS Service request");

        ServiceResponseDto serviceResponseDto = ndisServiceService.createNDISService(createServiceRequestDto);
        ResponseMessage<ServiceResponseDto> responseMessage = ResponseMessage.created(serviceResponseDto);

        logger.info("NDIS Service created");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
}
