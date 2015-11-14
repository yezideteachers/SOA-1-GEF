package fr.unice.polytech.soa1.cookbook.flows.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;


@WebService(serviceName = "BillAccessService")
public interface BillAccessService {


	@WebMethod(operationName = "retrieveBillFromUID")
	@WebResult(name="amount")
	double getTaxForm(@WebParam(name="request") String request);

}
