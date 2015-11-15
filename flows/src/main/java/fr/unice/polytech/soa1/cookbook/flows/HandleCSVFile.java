package fr.unice.polytech.soa1.cookbook.flows;


import fr.unice.polytech.soa1.cookbook.flows.business.OrderLine;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;

import java.util.Map;

import static fr.unice.polytech.soa1.cookbook.flows.utils.Endpoints.*;

/**
 * Route loading a given CSV file, transforming it into objects and transferring each object to another route
 *
 *   - in ServiceMix, to load the CSV transformation service:
 *     karaf@root> feature:install camel-csv
 *
 */
public class HandleCSVFile extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from(CSV_INPUT_DIRECTORY)
				.log("Processing 7 ${file:name}")
				.log("  Loading the file as a CSV document")
				.unmarshal(buildCsvFormat())  // Body is now a List(Map("navn" -> ..., ...), ...)
				.log("  Splitting the content of the file into atomic lines")
				.split(body())
				.log("  Transforming a CSV line into a Order")
				.process(csv2orderLine)
				.setHeader("idMarket", simple("${body.idMarket}"))
				.log("  Choosing the good market")
				.choice()
					.when(header("idMarket").isEqualTo(0))
						.log("  Transferring to the route that handle a given market")
						.to(HANDLE_ORDER_0)   // Async transfer with JMS ( activemq:handleAOrder0 )
					.when(header("idMarket").isEqualTo(10))
						.log("  Transferring to the route that handle a given market")
						.to(HANDLE_ORDER_1)   // Async transfer with JMS ( activemq:handleAOrder1 )
					.otherwise()
						.log("  Transferring to the route that handle a given citizen")
						.to("direct:badMarket").stop() // stopping route for bad idMarket
				//.to(HANDLE_ORDER)   // Async transfer with JMS ( activemq:... )
				//.end()
			//	.aggregate()
		;


		// bad information about a given idMarket
		from("direct:badMarket")
				.log("    Bad information market dos not exist ${body.idMarket}		")
		;


	}

	/**
	 * Helpers to support the implementation of the route
	 */

	// transform a CSV file delimited by commas, skipping the headers and producing a Map as output
	private static CsvDataFormat buildCsvFormat() {
		CsvDataFormat format = new CsvDataFormat();
		format.setDelimiter(",");
		format.setSkipHeaderRecord(true);
		format.setUseMaps(true);
		return format;
	}


	// Process a map<String -> Object> into a person
	private static Processor csv2orderLine = new Processor() {

		public void process(Exchange exchange) throws Exception {
			// retrieving the body of the exchanged message
			Map<String, Object> input = (Map<String, Object>) exchange.getIn().getBody();
			// transforming the input into a person
			OrderLine output =  builder(input);
			// Putting the person inside the body of the message
			exchange.getIn().setBody(output);
		}

		private OrderLine builder(Map<String, Object> data) {



			OrderLine o = new OrderLine();
			//idClient
			o.setIdClient(Integer.parseInt((String) data.get("idClient")));
			//idMarket
			o.setIdMarket(Integer.parseInt((String) data.get("idMarket")));
			//refProd
			o.setRef( (String) data.get("refprod"));
			o.setRefId(Integer.parseInt((String) data.get("refprod")));
			//quantity
			o.setQuantity(Integer.parseInt((String) data.get("quantity")));
			//price
			o.setPrice(2.0);
			return o;
		}

		private int getMoneyValue(Map<String, Object> data, String field) {
			String rawIncome = (String) data.get(field);
			return Integer.parseInt(rawIncome.replace(",", "").substring(0, rawIncome.length() - 3));
		}
	};


}
