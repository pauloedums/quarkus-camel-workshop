package br.com.impacta.camel.quarkus;

import org.apache.camel.builder.RouteBuilder;

public class HelloWorldRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:example?period=1000")
            .log("Hello World");
    }
}