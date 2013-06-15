/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.ws;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * Jersey REST client generated for REST resource:PortfolioResourceContainer
 * [/port]<br>
 * USAGE:
 * <pre>
 *        RestClient client = new RestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Luka Rajcevic
 */
public class RestClient {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8000/lurajcevi_aplikacija_2_2/webresources";

    public RestClient() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("port");
    }

    public String popisZip(String name, String portfolio) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("p/{0}/{1}", new Object[]{name, portfolio}));
        return resource.get(String.class);
    }

    public String getHtml() throws UniformInterfaceException {
        WebResource resource = webResource;
        return resource.accept(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public String aktivniKorisnici(String name) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("p/{0}", new Object[]{name}));
        return resource.get(String.class);
    }

    public ClientResponse postHtml(Object requestEntity) throws UniformInterfaceException {
        return webResource.type(javax.ws.rs.core.MediaType.TEXT_HTML).post(ClientResponse.class, requestEntity);
    }

    public void close() {
        client.destroy();
    }
    
}
