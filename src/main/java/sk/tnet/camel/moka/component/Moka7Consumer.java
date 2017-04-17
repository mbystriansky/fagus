package sk.tnet.camel.moka.component;

import java.nio.ByteBuffer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Moka7.S7;
import Moka7.S7Client;

public class Moka7Consumer extends ScheduledPollConsumer {
	private static Logger LOG = LoggerFactory.getLogger(Moka7Consumer.class);
	
	private Moka7Endpoint endpoint;

	private S7Client s7Client;

	public Moka7Consumer(Moka7Endpoint endpoint, Processor processor) {
		super(endpoint, processor);
		this.endpoint = endpoint;

		connect();
	}

	private void connect() {
		String address = endpoint.getAddress();
		int rack = endpoint.getConfiguration().getRack();
		int slot = endpoint.getConfiguration().getSlot();
		LOG.info("Connecting to IP: {}, rack: {}, slot: {}", address,
				rack, slot);
		//connect
		s7Client = new S7Client();
		s7Client.SetConnectionType(S7.OP);
    	int result = s7Client.ConnectTo(address, rack, slot);
    	int delay;
		if (result == 0) {
			LOG.info("Connected to: {} (rack={}, slot={})", address, rack, slot);
			LOG.info("PDU negotiated: {} bytes", s7Client.PDULength());
			delay = endpoint.getConfiguration().getDelay();
		} else {
			LOG.error(S7Client.ErrorText(result));
			delay = endpoint.getConfiguration().getConnDelay();
		}
		LOG.info("Setting delay to {} ms", delay);
		this.setDelay(delay);
	}

	@Override
	protected int poll() throws Exception {
		if (!s7Client.Connected) {
			connect();
			if (!s7Client.Connected) {
			    s7Client.Disconnect();
				return 0;
			}
		}
		short data;
        try {
            data = dbReadShort(endpoint.getConfiguration().getDb(), endpoint.getConfiguration().getStart());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            s7Client.Disconnect();
            return 0;
        }
		Exchange exchange = endpoint.createExchange();
        exchange.getIn().setBody(String.format("%02d", data), String.class);
		getProcessor().process(exchange);
		return 1;
	}

    public short dbReadShort(int db, int start) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        int result = s7Client.ReadArea(S7.S7AreaDB, db, start, 2, byteBuffer.array());
        if (result == 0) {
            return byteBuffer.getShort();
        } else {
            throw new RuntimeException("Chyba pri citani DataBloku " + db + ", kod chyby: " + result);
        }
    }      

}
