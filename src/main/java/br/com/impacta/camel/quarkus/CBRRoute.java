package br.com.impacta.camel.quarkus;

import org.apache.camel.builder.RouteBuilder;

public class CBRRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:/tmp/input/pedidos")
            .choice()
              .when(xpath("/Order/Country='USA'"))
                .log("Pedido USA Found")
                .log("FileName: ${in.header.CamelFileName} Content: ${body}")
                .to("rabbitmq://localhost:5672/quarkus-orders-us.exchange?queue=quarkus-orders-us")
            .when(xpath("/Order/Country='UK'"))
                .log("Pedido UK Found")
                .log("FileName: ${in.header.CamelFileName} Content: ${body}")
                .to("rabbitmq://localhost:5672/quarkus-orders-uk.exchange?queue=quarkus-orders-uk")
            .otherwise()
                .log("Pedido Default")
                .log("FileName: ${in.header.CamelFileName} Content: ${body}")
                .to("rabbitmq://localhost:5672/quarkus-orders.exchange?queue=quarkus-orders")
            .end();
    }
}