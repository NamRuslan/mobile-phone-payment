package com.namruslan.mobilephonepayment.service;

import com.namruslan.mobilephonepayment.dto.BalanceDto;
import com.namruslan.mobilephonepayment.entity.PhoneBalanceEntity;
import com.namruslan.mobilephonepayment.repository.BalanceRepository;
import com.namruslan.mobilephonepayment.util.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceService {

    private BalanceRepository balanceRepository;

    private MappingUtil mappingUtil;

    @Autowired
    public void setMappingUtil(MappingUtil mappingUtil) {
        this.mappingUtil = mappingUtil;
    }

    @Autowired
    public void setBalanceRepository(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public void create(BalanceDto dto) {
        balanceRepository.save(mappingUtil.toEntity(dto));
    }

    public boolean delete(int id) {
        if (balanceRepository.existsById(id)) {
            balanceRepository.deleteById(id);
            return true;
        } else return false;
    }

    public void createAll(List<PhoneBalanceEntity> list) {
        balanceRepository.saveAll(list);
    }

    public List<BalanceDto> getAll() {
        return mappingUtil.toDtoList(balanceRepository.findAll());
    }

    public Optional<BalanceDto> get(int id) {
        Optional<PhoneBalanceEntity> optional = balanceRepository.findById(id);
        return optional.map(entity -> mappingUtil.toDto(entity));
    }

    public Optional<PhoneBalanceEntity> getByPhoneNumber(int number) {
        return balanceRepository.findByPhoneNumber(number);
    }

    public List<BalanceDto> findAllByCustomerName(String name) {
        return mappingUtil.toDtoList(balanceRepository.findAllByCustomerName(name));
    }

    public boolean addMoneyToBalance(int number, int sum) {
        Optional<PhoneBalanceEntity> entityOptional = balanceRepository.findByPhoneNumber(number);

        if (entityOptional.isEmpty()) {
            return false;

        } else {

            PhoneBalanceEntity entity = entityOptional.get();
            entity.setBalance(entity.getBalance() + sum);
            balanceRepository.save(entity);
            return true;
        }
    }
}
