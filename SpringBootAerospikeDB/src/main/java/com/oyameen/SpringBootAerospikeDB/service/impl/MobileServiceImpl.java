package com.oyameen.SpringBootAerospikeDB.service.impl;

import com.oyameen.SpringBootAerospikeDB.exception.EmployeeManagementException;
import com.oyameen.SpringBootAerospikeDB.model.Mobile;
import com.oyameen.SpringBootAerospikeDB.repository.EmployeeRepository;
import com.oyameen.SpringBootAerospikeDB.repository.MobileRepository;
import com.oyameen.SpringBootAerospikeDB.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class MobileServiceImpl implements MobileService {

    @Autowired
    private MobileRepository mobileRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Mobile saveMobile(Mobile mobile) {
        mobile.setId(UUID.randomUUID().toString());
        return mobileRepository.save(mobile);
    }

    @Override
    public Mobile updateMobile(Mobile mobile) {
        Mobile mobileResult = mobileRepository.findById(mobile.getId()).orElse(null);
        if (mobileResult != null) {
            mobileResult.setBrand(mobile.getBrand());
            mobileResult.setRamSize(mobile.getRamSize());
            mobileResult.setCameraNumber(mobile.getCameraNumber());
            mobileResult.setOsType(mobile.getOsType());
            if (mobileResult.getEmployeeId() != null) {
                employeeRepository.findById(mobileResult.getEmployeeId()).ifPresent(employee -> {
                    employee.getMobiles()
                            .stream().filter(mob -> Objects.equals(mob.getId(), mobileResult.getId()))
                            .findFirst().ifPresent(toRemove -> employee.getMobiles().remove(toRemove));
                    employee.getMobiles().add(mobileResult);
                    employeeRepository.save(employee);
                });
            }
            return mobileRepository.save(mobileResult);
        }
        throw new EmployeeManagementException("Cannot update this Mobile, because it doesn't exist.");
    }

    @Override
    public List<Mobile> getMobiles() {
        List<Mobile> mobileList = new ArrayList<>();
        mobileRepository.findAll().forEach(mobileList::add);
        return mobileList;
    }

    @Override
    public Mobile getMobile(String id) {
        return mobileRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteMobile(String id) {
        Mobile mobile = null;
        if ((mobile = mobileRepository.findById(id).orElse(null)) == null) {
            throw new EmployeeManagementException("Cannot delete this Mobile, because it doesn't exist.");
        }
        if (mobile.getEmployeeId() != null) {
            Mobile finalMobile = mobile;
            employeeRepository.findById(mobile.getEmployeeId()).ifPresent(
                    employee -> {
                        employee.getMobiles().remove(finalMobile);
                        employeeRepository.save(employee);
                    });
        }
        mobileRepository.deleteById(id);
    }
}
