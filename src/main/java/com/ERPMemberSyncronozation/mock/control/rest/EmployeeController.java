package com.ERPMemberSyncronozation.mock.control.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.ERPMemberSyncronozation.mock.entity.Employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/employees")
@Tag(name = "employees")
public interface EmployeeController {

  @GET
  @Path("/{staffNumber}")
  @Produces(APPLICATION_JSON)
  @Operation(
          summary = "Get employee by staff number",
          responses = {
                  @ApiResponse(description = "The employee",
                          content = @Content(mediaType = "application/json",
                                  schema = @Schema(implementation = Employee.class))),
                  @ApiResponse(responseCode = "404", description = "employee not found")})
  Response getEmployeeByStaffNumber(@PathParam("staffNumber") int staffNumber);

  @GET
  @Path("")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  Response getEmployees();

  @POST
  @Path("/register")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  Response registerEmployeeAsAMemberToFundmaster(@Valid Employee employee);

  @POST
  @Path("/registerToErp")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  Response registerToErp(@Valid Employee employee);
}
