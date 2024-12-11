package at.htl.leonding.boundary;

import at.htl.leonding.entity.Customer;
import at.htl.leonding.control.CustomerRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.engine.spi.Status;

import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerRepository repository;

    @POST
    @Transactional
    public Response addCustomer(Customer customer) {
        repository.persist(customer);
        return Response.status(201).build();
    }

    @GET
    @Transactional
    public Response getAll() {
        return Response.status(200).entity(repository.listAll()).build();
    }

    @GET
    @Transactional
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id){
        return Response.status(200).entity(repository.findById(id)).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id){
        repository.deleteById(id);
        return Response.status(200).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Customer customer){
        Customer c = repository.findById(id);

        if(c == null)
            return Response.status(404).build();

        c.setName(customer.getName());
        c.setEmail(customer.getEmail());
        repository.persist(c);

        return Response.status(200).build();
    }
}
