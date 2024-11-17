package com.oyameen.SpringBootAerospikeDB.service;

import com.oyameen.SpringBootAerospikeDB.model.Mobile;

import java.util.List;

public interface MobileService {
    Mobile saveMobile(Mobile mobile);

    Mobile updateMobile(Mobile mobile);

    List<Mobile> getMobiles();

    Mobile getMobile(String id);

    void deleteMobile(String id);
}
