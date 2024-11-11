package com.oyameen.SpringBootH2.service;

import com.oyameen.SpringBootH2.model.Mobile;

import java.util.List;

public interface MobileService {
    Mobile saveMobile(Mobile mobile);

    Mobile updateMobile(Mobile mobile);

    List<Mobile> getMobiles();

    Mobile getMobile(Long id);

    void deleteMobile(Long id);
}
