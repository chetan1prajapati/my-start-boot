package com.av.rfid.rest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.av.rfid.data.entity.Epc;

@RestController
@RequestMapping("/epc")
public class EpcController {
	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Epc showUserForm(@PathVariable("id") Epc epc) {
		return epc;
	}
}
