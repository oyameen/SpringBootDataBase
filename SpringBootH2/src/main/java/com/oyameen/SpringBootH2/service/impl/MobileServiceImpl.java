package com.oyameen.SpringBootH2.service.impl;

import com.oyameen.SpringBootH2.exception.EmployeeManagementException;
import com.oyameen.SpringBootH2.model.Mobile;
import com.oyameen.SpringBootH2.repository.MobileRepository;
import com.oyameen.SpringBootH2.service.MobileService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MobileServiceImpl implements MobileService {

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
    public Mobile getMobile(Long id) {
        return mobileRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteMobile(Long id) {
        if (mobileRepository.findById(id).isEmpty()) {
            throw new EmployeeManagementException("Cannot delete this Mobile, because it doesn't exist.");
        }
        mobileRepository.deleteById(id);
    }
}
