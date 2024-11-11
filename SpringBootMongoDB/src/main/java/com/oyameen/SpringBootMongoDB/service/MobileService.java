package com.oyameen.SpringBootMongoDB.service;

import com.oyameen.SpringBootMongoDB.model.Mobile;

import java.util.List;

public interface MobileService {
    Mobile saveMobile(Mobile mobile);

    Mobile updateMobile(Mobile mobile);

    List<Mobile> getMobiles();

    Mobile getMobile(String id);

    void deleteMobile(String id);
}
