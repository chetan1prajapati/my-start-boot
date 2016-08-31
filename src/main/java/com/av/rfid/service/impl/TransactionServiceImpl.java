package com.av.rfid.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.av.rfid.data.entity.Epc;
import com.av.rfid.data.entity.RfidTransaction;
import com.av.rfid.data.entity.TrackedObject;
import com.av.rfid.data.repo.RfidTransactionRepo;
import com.av.rfid.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	RfidTransactionRepo rfidTransactionRepo;

	@Override
	public List<RfidTransaction> getLastTransactions(TrackedObject obj) {

		List<Epc> epcs = obj.getEpcs();
		List<RfidTransaction> result = new ArrayList<>();
		for (Epc epc : epcs) {
			RfidTransaction trans = rfidTransactionRepo.getLastTransaction(epc);
			if (trans != null) {
				result.add(trans);
			}
		}
		return result;
	}

}
