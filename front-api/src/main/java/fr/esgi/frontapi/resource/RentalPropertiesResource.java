package fr.esgi.frontapi.resource;

import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Path("/rental-properties")
@Singleton
public class RentalPropertiesResource {

    private final String PROPERTIES_API_URL = "http://host.docker.internal:8080/rent-properties-api/rental-properties";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProperties() {
        try {
            URL url = new URL(PROPERTIES_API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            Scanner sc = new Scanner(conn.getInputStream());
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }
            sc.close();

            return Response.ok(sb.toString()).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProperty(String body) {
        try {
            URL url = new URL(PROPERTIES_API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes());
            }
            int status = conn.getResponseCode();
            return Response.status(status).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPropertyById(@PathParam("id") String id) {
        try {
            URL url = new URL(PROPERTIES_API_URL + "/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            Scanner sc = new Scanner(conn.getInputStream());
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }
            sc.close();

            return Response.ok(sb.toString()).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProperty(@PathParam("id") String id) {
        try {
            URL url = new URL(PROPERTIES_API_URL + "/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            int status = conn.getResponseCode();
            return Response.status(status).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response upsertProperty(@PathParam("id") String id, String body) {
        try {
            URL url = new URL(PROPERTIES_API_URL + "/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes());
            }
            int status = conn.getResponseCode();
            return Response.status(status).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response patchProperty(@PathParam("id") String id, String body) {
        try {
            URL url = new URL(PROPERTIES_API_URL + "/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PATCH");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes());
            }
            int status = conn.getResponseCode();
            return Response.status(status).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchProperties(String body) {
        try {
            URL url = new URL(PROPERTIES_API_URL + "/search");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes());
            }
            Scanner sc = new Scanner(conn.getInputStream());
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }
            sc.close();
            return Response.ok(sb.toString()).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }



}
