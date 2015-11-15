package fr.unice.polytech.soa1.cookbook.flows;


import fr.unice.polytech.soa1.cookbook.flows.business.BillForm;
import fr.unice.polytech.soa1.cookbook.flows.utils.RequestBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonDataFormat;

import javax.xml.transform.Source;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import static fr.unice.polytech.soa1.cookbook.flows.utils.Endpoints.GEN_SERVICE;


public class CallExternalPatternsFurniture extends RouteBuilder {

    //id product to put in endpoint (impossiple to put id with ${body or property}
    private static int idProd=0;
    @Override
    public void configure() throws Exception {

        // Consuming a rest service with a Get
        from("direct:generator")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setProperty("refId", simple("${body.refId}"))
                .setBody(constant(""))
                .to(GEN_SERVICE + "/cxf/demo/catalog/products")
                .process(readResponseStream)
                .to("direct:catalog")
        ;
        //


        // Consuming a rest service with a Post
        from("direct:cart")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                        //	.setBody(constant(""))
                .setProperty("ord_uid", body())
                .setBody(constant(""))
                .to(GEN_SERVICE + "/cxf/demo/carts/cart/"+idProd)
                .setBody(simple("${property.ord_uid}"))

        ;
        from("direct:product")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setBody(constant(""))
                .to(GEN_SERVICE + "/cxf/demo/catalog/product/0")
                .process(readResponseStream)
                .process(uuidCleaner)

        ;

        from("direct:catalog")
                .bean(RequestBuilder.class, "Json2ListOfMap(${body})")
        //.log("{{{{{{{{{{{{{{{     }}}}}}}}}}}}}}}")
			/*	.setBody(constant(""))
				.to(GEN_SERVICE + "/cxf/demo/catalog/product/1")*/

        ;


        from("direct:bill")
                .bean(RequestBuilder.class, "makeBill(${body})")
                .to("direct:generateLetterBill")

        ;

        // SOAP: Using the simple method
        from("direct:commandMethod")
                //.log("    Computing ${body.idMarket} with [price  : ${body.price} ]")
                .bean(RequestBuilder.class, "calculCommand(${body})")
        //.to(Bill_COMPUTATION_SERVICE)
        //.process(result2bill)
        ;

    }

    /**
     * Static processors used as helpers to process the retrieved data
     */

    private static JsonDataFormat buildJsonFormat() {
        JsonDataFormat format = new JsonDataFormat();
        format.setUseList(true);
        return format;
    }

    // read an InputStream completely and store it in a String
    private static Processor readResponseStream = new Processor() {
        public void process(Exchange exchange) throws Exception {
            InputStream response = (InputStream) exchange.getIn().getBody();
            BufferedReader reader = new BufferedReader(new InputStreamReader(response));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) { out.append(line); }
            reader.close();
            exchange.getIn().setBody(out.toString());
        }
    };




    // transform {xxx} into matrix  (removing starting {} and :)
    private static Processor uuidCleaner = new Processor() {
        public void process(Exchange exchange) throws Exception {
            String data = (String) exchange.getIn().getBody();
            String cleaned =data.substring(1,data.length()-1);
            Map<String , String> mapProd = null;
            String [] t = cleaned.split(",");
            String[][] res = new String[t.length][2];
            for (int i = 0; i < t.length; i++) {
                res[i] = t[i].split(":");

            }

            int i = Integer.parseInt(res[0][1]);
			/*mapProd.put(res[2][0], (int) Double.parseDouble(res[2][1]));
			mapProd.put(res[5][0], Integer.parseInt(res[5][1]));*/
            exchange.getIn().setBody(i);
        }
    };

    // Transform the response of the TaxComputation web service into a BillForm Business Object (using XPath)
    private static Processor result2bill = new Processor() {

        private XPath xpath = XPathFactory.newInstance().newXPath();    // feature:install camel-saxon

        public void process(Exchange exchange) throws Exception {
            Source response = (Source) exchange.getIn().getBody();
            BillForm result = new BillForm();
            result.setAmount(Double.parseDouble(xpath.evaluate("//amount/text()", response)));
            result.setDate(xpath.evaluate("//date/text()", response));
            exchange.getIn().setBody(result);
        }
    };

}