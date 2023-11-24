package com.servicebook.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VariableDeEntorno {

    @Value("%{DATA_BASE_URL}")
    private String url = "jdbc:mysql://aws.connect.psdb.cloud/testeando?sslMode=VERIFY_IDENTITY";
    @Value("${DATA_BASE_USERNAME}")
    private String username= "gr4x7t6qe6d0dmzbcch3";
    @Value("${DATA_BASE_PASSWORD}")
    private String password = "pscale_pw_JeqezMyGuum6g0KxV6lhNfg4zNu1vCLjlaVQFndXsgq";

}
