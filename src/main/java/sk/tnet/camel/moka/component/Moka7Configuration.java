package sk.tnet.camel.moka.component;

import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;

@UriParams
public class Moka7Configuration {

    @UriParam(defaultValue = "1000")
    private int delay = 1000;

    @UriParam(defaultValue = "0")
    private int rack = 0;

    @UriParam(defaultValue = "2")
    private int slot = 2;

    public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getRack() {
		return rack;
	}

	public void setRack(int rack) {
		this.rack = rack;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

}
