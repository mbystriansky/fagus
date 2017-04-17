package sk.tnet.camel.moka.component;

import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;

@UriParams
public class Moka7Configuration {

    @UriParam(defaultValue = "1000")
    private int delay = 1000;

    @UriParam(defaultValue = "5000")
    private int connDelay = 5000;

    @UriParam(defaultValue = "0")
    private int rack = 0;

    @UriParam(defaultValue = "1")
    private int slot = 1;

    @UriParam(defaultValue = "1020")
    private int db = 1020;

    @UriParam(defaultValue = "0")
    private int start = 0;

    public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getConnDelay() {
        return connDelay;
    }

    public void setConnDelay(int connDelay) {
        this.connDelay = connDelay;
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

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

}
