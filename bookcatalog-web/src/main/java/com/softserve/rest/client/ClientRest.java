package com.softserve.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public abstract class ClientRest {

	protected Client CLIENT;
	protected String TARGET_URL;

	public ClientRest() {
		TARGET_URL = "http://localhost:8080/BookCatalog/rest/";
		CLIENT = ClientBuilder.newClient();
	}

}
