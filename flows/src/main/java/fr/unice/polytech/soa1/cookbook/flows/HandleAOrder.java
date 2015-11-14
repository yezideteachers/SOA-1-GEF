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
				.log("      Storing the Person as an exchange property")
				.setProperty("orderline", body())
				.log("      Calling an existing generator")
				.setProperty("p_uuid", simple("${body.ref}"))
				.to("direct:product")
				.to("direct:generator")
				.setProperty("price", simple("${body.get(\"price\")}"))
				.setProperty("prod_uid", simple("${body.get(\"id\")}"))
				.setProperty("prod_map", simple("${body})"))
				.bean(RequestBuilder.class, "setPricebis(${property.orderline},${property.price})")
				.setBody(simple("${property.orderline}"))
				//.log("#####################		${property.prod_uid} :::: ${property.prod_map}	#########" )
				//.process(setPrice)
				.choice()
					.when(simple("${property.prod_uid} == ${body.idMarket}"))
						//.log("___________  call services methods : ${property.prod_uid}___________ ")
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
					.to("direct:makeBill")
					.log("=============== facture : " + RequestBuilder.facture + "==================");

		;

		// bad information about a given citizen
		from("direct:badOrder")
				.log("   Bad information for citizen ${body.name}, ending here.")
		;

		from("direct:generateLetterBill")
				.bean(LetterWriter.class, "write(${property.orderline}, ${body}," +
						"${property.bill_computation_method})")
					.to(CSV_OUTPUT_DIRECTORY + "?fileName=${property.p_uuid}.txt")
		;
		from("direct:generateLetter")
				.bean(LetterWriter.class, "write2(${body})")
				.to(CSV_OUTPUT_DIRECTORY + "?fileName=file00.txt")

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
