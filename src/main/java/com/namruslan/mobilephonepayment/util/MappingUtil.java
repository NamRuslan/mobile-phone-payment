package com.namruslan.mobilephonepayment.util;

import com.namruslan.mobilephonepayment.dto.BalanceDto;
import com.namruslan.mobilephonepayment.entity.PhoneBalanceEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingUtil {

    public BalanceDto toDto(PhoneBalanceEntity entity) {
        return new BalanceDto()
                .setId(entity.getId())
                .setBalance(entity.getBalance())
                .setCustomerName(entity.getCustomerName())
                .setPhoneNumber(entity.getPhoneNumber());
    }

    public PhoneBalanceEntity toEntity(BalanceDto dto) {
        return new PhoneBalanceEntity()
                .setId(dto.getId())
                .setBalance(dto.getBalance())
                .setCustomerName(dto.getCustomerName())
                .setPhoneNumber(dto.getPhoneNumber());
    }

    public List<BalanceDto> toDtoList(List<PhoneBalanceEntity> entityList) {
        List<BalanceDto> resultList = new ArrayList<>();
        entityList.forEach(entity -> resultList.add(toDto(entity)));
        return resultList;
    }
}
