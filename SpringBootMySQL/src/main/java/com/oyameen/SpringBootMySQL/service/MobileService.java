package com.oyameen.SpringBootMySQL.service;

import com.oyameen.SpringBootMySQL.model.Mobile;

import java.util.List;

public interface MobileService {
    Mobile saveMobile(Mobile mobile);

    Mobile updateMobile(Mobile mobile);

    List<Mobile> getMobiles();

    Mobile getMobile(Long id);

    void deleteMobile(Long id);
}
