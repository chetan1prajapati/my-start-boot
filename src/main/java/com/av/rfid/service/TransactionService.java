package com.av.rfid.service;

import java.util.List;

import com.av.rfid.data.entity.RfidTransaction;
import com.av.rfid.data.entity.TrackedObject;

public interface TransactionService {

	List<RfidTransaction> getLastTransactions(TrackedObject obj);

}
