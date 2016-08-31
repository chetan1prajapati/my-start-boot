package com.av.rfid.data.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.av.rfid.data.entity.Epc;
import com.av.rfid.data.entity.RfidTransaction;

public interface RfidTransactionRepo extends CrudRepository<RfidTransaction, Long> {

	@Query(value = "SELECT * FROM RfidTransaction where Epc = epc.id ORDER BY reportDateTime DESC LIMIT 1", nativeQuery = true)
	RfidTransaction getLastTransaction(Epc epc);

	RfidTransaction findTop1ByEpcOrderByReportDateTimeDesc(Epc epc);

}
