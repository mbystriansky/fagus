package sk.tnet.camel.moka.component;

import org.apache.camel.Component;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultPollingEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriPath;

@UriEndpoint(scheme="moka7", title="Moka7", syntax="moka7://<IP-address>", consumerOnly=true, consumerClass=Moka7Consumer.class, label="Step7")
public class Moka7Endpoint extends DefaultPollingEndpoint {

	@UriPath
	@Metadata(required = "true")
	private String address;

	private Moka7Configuration configuration;

	public Moka7Endpoint(String endpointUri, String address, Component component) {
		super(endpointUri, component);
		this.address = address;
	}

	@Override
	public Producer createProducer() throws Exception {
		throw new UnsupportedOperationException("Moka7Producer is not implemented");
	}

	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		Moka7Consumer consumer = new Moka7Consumer(this, processor);
        return consumer;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public String getAddress() {
		return address;
	}

	public Moka7Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Moka7Configuration configuration) {
		this.configuration = configuration;
	}

}
