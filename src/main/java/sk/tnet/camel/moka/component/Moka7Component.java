package sk.tnet.camel.moka.component;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;

public class Moka7Component extends UriEndpointComponent {

	public Moka7Component(CamelContext context) {
		super(context, Moka7Endpoint.class);
	}

	public Moka7Component() {
		super(Moka7Endpoint.class);
	}

	@Override
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
		Moka7Endpoint endpoint = new Moka7Endpoint(uri, remaining, this);
		Moka7Configuration configuration = new Moka7Configuration();
		setProperties(configuration, parameters);
		endpoint.setConfiguration(configuration);
		return endpoint;
	}

}
