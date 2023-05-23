package org.example.product.resource;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.product.entity.Product;
import org.example.product.service.ProductService;

import java.util.List;

@Path("/v1/product")
public class ProductResource {

    private ProductService productService = new ProductService();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") int pId) {
        Product returnProduct = productService.getProductById(pId);
        if (returnProduct == null) {
            return Response.status(400, "Product not found.").build();
        }
        Gson gson = new Gson();
        return Response.ok(gson.toJson(returnProduct)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        List<Product> products = productService.getProducts();
        return Response.ok(products).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        if (product == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        int maxId = productService.getMaxId();
        product.setpId(maxId);
        Product returnProduct = productService.save(product);
        return Response.ok(returnProduct).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(Product product) {
        if (product == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Product returnProduct = productService.save(product);
        return Response.ok(returnProduct).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") int pId) {
        boolean status = productService.delete(pId);
        if (status) {
            return Response.ok("Product deleted.").build();
        } else {
            return Response.serverError().build();
        }
    }
}
