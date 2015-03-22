/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webServices;
import Credentials.Connect;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author c0633648
 */
@Path("/advertise")
public class Person {
    
    @GET
    @Produces("application/json")
    public Response get() {
      return Response.ok(Connect.getResults("SELECT * FROM person")).build();
    }
}
