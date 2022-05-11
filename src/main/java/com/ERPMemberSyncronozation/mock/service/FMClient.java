package com.ERPMemberSyncronozation.mock.service;

import com.ERPMemberSyncronozation.mock.control.rest.impl.vm.ResponseVM;
import com.ERPMemberSyncronozation.mock.entity.Employee;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@RequestScoped
public class FMClient {

    private WebTarget target;

    private MultivaluedMap<String, Object> myHeaders;

    private static String FM_BASE_ENDPOINT= "https://demos.systechafrica.com/WhatsappBusiness/api/";
    private static String PATH= "register";

    @PostConstruct
    public void setup() {
        Client client = ClientBuilder.newClient();
        target = client.target(FM_BASE_ENDPOINT);
    }
    public ResponseVM registerEmployeeToFundmaster(Employee employee){
        try {
            Response response = target.path(PATH)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .headers(myHeaders)
                    .get();

            return response.readEntity(ResponseVM.class);
        } catch (Exception e) {
            return ResponseVM.builder().success(false).message(e.getMessage()).build();
        }

    }
}
