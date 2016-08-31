package com.av.rfid.data.repo;

import org.springframework.data.repository.CrudRepository;

import com.av.rfid.data.entity.Epc;

public interface EpcRepo extends CrudRepository<Epc, Long> {

}
