package sk.tnet.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FagusRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("moka7://192.168.0.122?delay=1000&rack=0&slot=2").to("log:moka7").to("paho://{{fagus.app.mqtt.topic}}");
	}

}
