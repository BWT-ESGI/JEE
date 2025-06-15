package fr.esgi.frontapi.resource;

import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Path("/rental-cars")
@Singleton
public class RentalCarsResource {

    private final String CARS_API_URL = "http://host.docker.internal:8082/rent-cars-api/rental-cars";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCars() {
        try {
            URL url = new URL(CARS_API_URL);
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
    public Response createCar(String body) {
        try {
            URL url = new URL(CARS_API_URL);
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
    public Response getCarById(@PathParam("id") String id) {
        try {
            URL url = new URL(CARS_API_URL + "/" + id);
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

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCar(@PathParam("id") String id, String body) {
        try {
            URL url = new URL(CARS_API_URL + "/" + id);
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
    public Response partialUpdateCar(@PathParam("id") String id, String body) {
        try {
            URL url = new URL(CARS_API_URL + "/" + id);
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


    @DELETE
    @Path("/{id}")
    public Response deleteCar(@PathParam("id") String id) {
        try {
            URL url = new URL(CARS_API_URL + "/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            int status = conn.getResponseCode();
            return Response.status(status).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }


}
