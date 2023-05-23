package org.example.product.resource;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.product.entity.Product;
import org.example.product.service.ProductService;

import java.util.List;

@Path("/v1/resource")
public class ProductResource {

    private ProductService productService = new ProductService();

    @GET
    @Path("/productId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProductById(Product product) {
        if (product == null || product.getpId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        int id = product.getpId();
        Product returnProduct = productService.getProductById(id);
        Gson gson = new Gson();
        return Response.ok(gson.toJson(returnProduct)).build();
    }

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        List<Product> products = productService.getProducts();
        Gson gson = new Gson();
        return Response.ok(gson.toJson(products)).build();
    }

    @POST
    @Path("/newProduct")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        if (product == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        int maxId = productService.getMaxId();
        product.setpId(maxId);
        Product returnProduct = productService.save(product);
        Gson gson = new Gson();
        return Response.ok(gson.toJson(returnProduct)).build();
    }

    @PUT
    @Path("/updateProduct")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(Product product) {
        if (product == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Product returnProduct = productService.save(product);
        Gson gson = new Gson();
        return Response.ok(gson.toJson(returnProduct)).build();
    }

    @DELETE
    @Path("/deleteProduct")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProduct(Product product) {
        if (product == null || product.getpId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean status = productService.delete(product.getpId());
        if (status) {
            return Response.ok("Product deleted.").build();
        } else {
            return Response.serverError().build();
        }
    }
}
