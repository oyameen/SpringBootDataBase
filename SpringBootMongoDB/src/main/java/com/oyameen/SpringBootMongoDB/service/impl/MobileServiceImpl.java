package com.oyameen.SpringBootMongoDB.service.impl;

import com.oyameen.SpringBootMongoDB.exception.EmployeeManagementException;
import com.oyameen.SpringBootMongoDB.model.Employee;
import com.oyameen.SpringBootMongoDB.model.Mobile;
import com.oyameen.SpringBootMongoDB.repository.EmployeeRepository;
import com.oyameen.SpringBootMongoDB.repository.MobileRepository;
import com.oyameen.SpringBootMongoDB.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MobileServiceImpl implements MobileService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MobileRepository mobileRepository;

    @Override
    public Mobile saveMobile(Mobile mobile) {
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
            return mobileRepository.save(mobileResult);
        }
        throw new EmployeeManagementException("Cannot update this Mobile, because it doesn't exist.");
    }

    @Override
    public List<Mobile> getMobiles() {
        return mobileRepository.findAll();
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
        if (mobile.getEmployee() != null && mobile.getEmployee().getId() != null) {
            Optional<Employee> employeeOptional = employeeRepository.findById(mobile.getEmployee().getId());
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                employee.getMobiles().remove(mobile);
                employeeRepository.save(employee);
            }
        }
        mobileRepository.deleteById(id);
    }
}
