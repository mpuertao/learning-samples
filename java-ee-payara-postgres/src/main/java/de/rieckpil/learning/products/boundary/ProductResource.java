package de.rieckpil.learning.products.boundary;

import de.rieckpil.learning.products.entity.Product;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Stateless
@Path("products")
public class ProductResource {

    @PersistenceContext(unitName = "db-1")
    private EntityManager em1;

    @PersistenceContext(unitName = "db-2")
    private EntityManager em2;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        JsonObject json = Json.createObjectBuilder().add("name", "coffee").add("price", 42.0).build();

        List<Product> listEm1 = em1.createQuery("SELECT p FROM Product p", Product.class).getResultList();

        List<Product> listEm2 = em2.createQuery("SELECT p FROM Product p", Product.class).getResultList();

        listEm1.addAll(listEm2);

        return Response.ok(listEm1).build();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewProduct(@PathParam("name") String name) {

        Product product = new Product();
        product.setName(name);
        product.setPrice(ThreadLocalRandom.current().nextDouble(100));

        if (Math.random() < 0.5) {
            em1.persist(product);
        } else {
            em2.persist(product);
        }

        return Response.ok(product).build();
    }
}
