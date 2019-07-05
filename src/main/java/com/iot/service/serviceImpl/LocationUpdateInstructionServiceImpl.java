package com.iot.service.serviceImpl;

import com.iot.otaBean.locationUpdateInstruction.LocationUpdateInstruction;
import com.iot.service.interfaces.LocationUpdateInstructionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LocationUpdateInstructionServiceImpl implements LocationUpdateInstructionService {

    /**
     * 获取列表
     * @return
     */
    @Override
    public List<LocationUpdateInstruction> getList() {
        return null;
    }
}
