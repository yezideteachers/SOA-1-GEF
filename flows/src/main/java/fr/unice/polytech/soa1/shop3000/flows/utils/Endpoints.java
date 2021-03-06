package fr.unice.polytech.soa1.shop3000.flows.utils;

/**
 * This file is part of the ws-flows project
 *
 * @author mosser (15/10/2015, 16:25)
 **/
public class Endpoints {


	public static final String CSV_INPUT_DIRECTORY = "file:camel/input";

	public static final String CSV_OUTPUT_DIRECTORY = "file:camel/output";


	public static final String HANDLE_ORDER = "activemq:handleAOrder";
	public static final String HANDLE_ORDER_0 = "activemq:handleAOrder0";
	public static final String HANDLE_ORDER_1 = "activemq:handleAOrder1";

	public static final String GEN_SERVICE = "http://localhost:8181";

	public static final String HANDLE_CATALOG = "activemq:handleACatalog";

	public static final String GEN_SERV_RES = "file:http://localhost:8181/cxf/demo/catalog/products";

	public static final String STORE_BILL_FORM = "activemq:storeBillForm";

	public static final String Bill_COMPUTATION_SERVICE = "spring-ws://http://localhost:8181/cxf/BillComputation";


}
