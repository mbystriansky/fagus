package sk.tnet.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import sk.tnet.camel.moka.component.Moka7Consumer;

@Component
public class FagusRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("moka7://{{fagus.app.moka7.server-url}}").to("log:" + Moka7Consumer.class).to("paho://{{fagus.app.mqtt.topic}}");
	}

}
