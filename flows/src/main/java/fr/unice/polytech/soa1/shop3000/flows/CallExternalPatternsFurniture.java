package fr.unice.polytech.soa1.shop3000.flows;


import fr.unice.polytech.soa1.shop3000.flows.utils.Endpoints;
import fr.unice.polytech.soa1.shop3000.flows.utils.RequestBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class CallExternalPatternsFurniture extends RouteBuilder {

    //id product to put in endpoint (impossiple to put id with ${body or property}

    @Override
    public void configure() throws Exception {

        // Consuming a rest service with a Get
        from("direct:products")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setProperty("refId", simple("${body.refId}"))
                .setBody(constant(""))
                .to(Endpoints.GEN_SERVICE + "/cxf/demo/catalog/products")
                .process(readResponseStream)
                .bean(RequestBuilder.class, "Json2ListOfMap(${body})")
        ;


        // Consuming a rest service with a Post
        from("direct:cart")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setProperty("ord_uid", body())
                .setBody(constant(""))
                .bean(RequestBuilder.class, "setIdProd(${property.ord_uid})")
                .log("~~~~~~~~~~~~~~  ${property.ord_uid}   ~~~~~~~~~~~~~~~~")
                .to(Endpoints.GEN_SERVICE + "/cxf/demo/carts/cart/" + RequestBuilder.idProd[0])
                .setBody(simple("${property.ord_uid}"))

        ;

        from("direct:bill")
                .bean(RequestBuilder.class, "makeBill(${body})")
                .to("direct:generateLetterBill")

        ;

        from("direct:updateCommand")
                .bean(RequestBuilder.class, "calculCommand(${body})")

        ;

        //check payment mocked
        from("direct:payment")
                .log("payment ok")
        ;

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




}