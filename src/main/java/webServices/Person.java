/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webServices;
import Credentials.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
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
      return Response.ok(getResults("SELECT * FROM person")).build();
    }
    
     public static JsonArray getResults(String sql, String... params) {
        JsonArray json = null;
        try {
            Connection conn = Connect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setString(i + 1, params[i]);
            }
            ResultSet rs = pstmt.executeQuery();

            JsonArrayBuilder array = Json.createArrayBuilder();
            while (rs.next()) {
                array.add(Json.createObjectBuilder()
                        .add("id", rs.getInt("id"))
                        .add("name", rs.getString("name"))
                );
            }
            json = array.build();
        } catch (SQLException ex) {
           // Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
