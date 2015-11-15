package fr.unice.polytech.soa1.shop3000.flows;

import fr.unice.polytech.soa1.shop3000.flows.business.OrderLine;
import fr.unice.polytech.soa1.shop3000.flows.utils.Database;
import fr.unice.polytech.soa1.shop3000.flows.utils.LetterWriter;
import fr.unice.polytech.soa1.shop3000.flows.utils.RequestBuilder;
import fr.unice.polytech.soa1.shop3000.flows.utils.Endpoints;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.ArrayList;
import java.util.Map;


/**
 * feature:install http
 */
public class HandleAOrder0 extends RouteBuilder {
	static Map<Integer, Object> input = null;
	public static ArrayList<String> myList = new ArrayList<String>();
    Database db = new Database();
	static int testval = 0;
	@Override
	public void configure() throws Exception {

		// Dead letter channel as a logger
		errorHandler(deadLetterChannel("log:deadPool"));

		// Route to handle a given Order
		from(Endpoints.HANDLE_ORDER_0)
				.log("      Storing the Order as an exchange property")
				.setProperty("orderline", body())
				.log("      Calling an existing generator")
				.setProperty("p_uuid", simple("${body.refId}"))
				.to("direct:products")
				.setProperty("price", simple("${body.get(${property.orderline.refId}).get(\"price\")}"))
				.setProperty("prod_uid", simple("${body.get(${property.orderline.refId}).get(\"id\")}"))
				.setProperty("prod_map", simple("${body.get(${property.orderline.refId})}"))
				.bean(RequestBuilder.class, "setPricebis(${property.orderline},${property.price})")
				.setBody(simple("${property.orderline}"))
				.choice()
					//if product exist
					.when(simple("${property.prod_uid} == ${body.refId}"))
						.to("direct:cart")
						.to("direct:updateCommand")
						.setProperty("bill",simple("${body}"))
						.to("direct:payment")
						.setBody(simple("${property.bill}"))
						.setProperty("bill_computation_method", constant("COMMAND"))
					.otherwise()
						.to("direct:badOrder").stop() // stopping the route for bad citizens
				.end() // End of the content-based-router
				.setHeader("orderline_idClient", simple("${property.orderline.idClient}"))
				.multicast()
				.parallelProcessing()
					.to("direct:bill")
					.to("direct:storeDB")


		;

		// bad information about a given order
		from("direct:badOrder")
				.log("   Bad information for citizen ${body.name}, ending here.")
		;
		// generate bills
		from("direct:generateLetterBill")
				.bean(LetterWriter.class, "write(${body})")
				.to(Endpoints.CSV_OUTPUT_DIRECTORY + "?fileName=${file:name}.txt")
		;


	}
		private static Processor test2orderLines = new Processor() {

			public void process(Exchange exchange) throws Exception {
				// retrieving the body of the exchanged message
				input.put(0, exchange.getIn().getBody());
				OrderLine o = (OrderLine) input.get(0);
				o.setName("Product23434");
				exchange.getIn().setBody(o);
			}

	};




}
