package resources;

import java.util.ResourceBundle;

public class Taal {

	private String taal;
	private ResourceBundle rb;

	public Taal(String taal) {
		setTaal(taal);
	}

	// methodes
	public String vertaal(String teVertalen) {
		return rb.getString(teVertalen);
	}

	// getters en setters
	public String getTaal() {
		return taal;
	}

	public void setTaal(String taal) {
		this.taal = taal;
		rb = ResourceBundle.getBundle(taal);
	}

}
