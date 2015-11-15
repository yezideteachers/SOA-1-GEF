package fr.unice.polytech.soa1.shop3000.flows;

import fr.unice.polytech.soa1.shop3000.flows.utils.Database;
import org.apache.camel.builder.RouteBuilder;

public class BillFormAccessRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {


		/****************************************************************
		 ** Internal Routes available to access the computed tax forms **
		 ****************************************************************/

		// Store a Bill form in the database, using an ActiveMQ channel for asynchronous processing
		from("direct:storeDB")
				.bean(Database.class, "setData(${header.orderline_idClient}, ${body})")
		;

		// Retrieve a Bill form in the database, internal route calling the database bean.
		from("direct:getTaxForm")
				.bean(Database.class, "getData(${body})") // Body is the UID of the taxpayer
			//	.setBody(simple("${body.amount}"))
		;

		/***********************************************************
		 ** Exposing how to retrieve a tax form as a REST service **
		 ***********************************************************/

		// Binding the REST domain specific language to the Servlet component
		restConfiguration().component("servlet"); // feature:install camel-servlet + edit in the OSGi blueprint

		// Defining the resource to expose, and the used verb
		rest("/billForm/{uid}")
				.get()
				.to("direct:getTaxFormFromREST")
		;

		// local route, collecting URL arguments and transferring it to the concrete route
		from("direct:getTaxFormFromREST")
				.setBody(simple("${header.uid}"))
				.to("direct:getTaxForm")
		;


		/***********************************************************
		 ** Exposing how to retrieve a tax form as a SOAP service **
		 ***********************************************************/

/*
		from("cxf:/TaxAccessService?serviceClass=BillAccessService")
				.filter(simple("${in.headers.operationName} == 'retrieveBillFormFromUID'"))
				.to("direct:getTaxForm")
		;
*/
	}

}