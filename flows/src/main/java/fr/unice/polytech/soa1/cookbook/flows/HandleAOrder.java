package fr.unice.polytech.soa1.cookbook.flows;

import fr.unice.polytech.soa1.cookbook.flows.business.OrderLine;
import fr.unice.polytech.soa1.cookbook.flows.utils.Database;
import fr.unice.polytech.soa1.cookbook.flows.utils.LetterWriter;
import fr.unice.polytech.soa1.cookbook.flows.utils.RequestBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.ArrayList;
import java.util.Map;

import static fr.unice.polytech.soa1.cookbook.flows.utils.Endpoints.CSV_OUTPUT_DIRECTORY;
import static fr.unice.polytech.soa1.cookbook.flows.utils.Endpoints.HANDLE_ORDER_0;


/**
 * feature:install http
 */
public class HandleAOrder extends RouteBuilder {
	static Map<Integer, Object> input = null;
	public static ArrayList<String> myList = new ArrayList<String>();
    Database db = new Database();
	static int testval = 0;
	@Override
	public void configure() throws Exception {

		// Dead letter channel as a logger
		errorHandler(deadLetterChannel("log:deadPool"));

		// Route to handle a given Order
		from(HANDLE_ORDER_0)
				.log("    Routing ${body.name} according to quantity ${body.quantity}")
				.log("      Storing the Order as an exchange property")
				.setProperty("orderline", body())
				.log("      Calling an existing generator")
				.setProperty("p_uuid", simple("${body.refId}"))
				//.to("direct:product")
				.to("direct:generator")
				.log("#####################		${body} 	#########")
				.setProperty("price", simple("${body.get(${property.orderline.refId}).get(\"price\")}"))
				.setProperty("prod_uid", simple("${body.get(${property.orderline.refId}).get(\"id\")}"))
				.setProperty("prod_map", simple("${body.get(${property.orderline.refId})}"))
				.bean(RequestBuilder.class, "setPricebis(${property.orderline},${property.price})")
				.setBody(simple("${property.orderline}"))
				.log("________________________________ debeug : ${body} __________________________________________________")
				//.process(setPrice)
				.choice()
					//if product exist
					.when(simple("${property.prod_uid} == ${body.refId}"))
						.log("___________  call services methods : ${body}___________ ")
						.to("direct:cart")
						.to("direct:commandMethod")
						.setProperty("bill_computation_method", constant("COMMAND"))
					.otherwise()
						.to("direct:badOrder").stop() // stopping the route for bad citizens
				.end() // End of the content-based-router
				.setHeader("orderline_idClient", simple("${property.orderline.idClient}"))
				.multicast()
				.parallelProcessing()
					//.to("direct:generateLetterBill")
						//.to("direct:generateLetter")
					.to("direct:bill")


		;

		// bad information about a given order
		from("direct:badOrder")
				.log("   Bad information for citizen ${body.name}, ending here.")
		;
		// generate bills
		from("direct:generateLetterBill")
				.log("=============== facture : ${body}  ==== ==============")
				.bean(LetterWriter.class, "write3(${body})")
				.to(CSV_OUTPUT_DIRECTORY + "?fileName=f3.txt")
		;
		from("direct:generateLetter")
				.bean(LetterWriter.class, "write2(${body})")
				.to(CSV_OUTPUT_DIRECTORY + "?fileName=${file:name}.txt")

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
