//package com.example.doanjava.Config;
//
//import com.paypal.base.rest.APIContext;
//import com.paypal.base.rest.PayPalRESTException;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class PayPalConfig {
//
//    @Value("${paypal.clientId}")
//    private String clientId;
//
//    @Value("${paypal.clientSecret}")
//    private String clientSecret;
//
//    @Value("${paypal.mode}")
//    private String mode;
//
//    @Bean
//    public APIContext apiContext() throws PayPalRESTException {
//        APIContext apiContext = new APIContext(clientId, clientSecret, mode);
//        return apiContext;
//    }
//}