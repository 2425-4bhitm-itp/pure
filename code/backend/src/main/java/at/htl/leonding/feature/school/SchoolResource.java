package at.htl.leonding.feature.school;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/schools")
public class SchoolResource {
    @Inject
    SchoolRepository schoolRepository;
    @Inject SchoolMapper schoolMapper;
    
    @GET
    public Response all() {
        var schools = schoolRepository.findAll().stream().map(schoolMapper::toResource);
        return Response.ok(schools).build();
    }
}
