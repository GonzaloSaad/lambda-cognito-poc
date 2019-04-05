package com.birrete.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import org.springframework.cloud.function.adapter.aws.SpringBootApiGatewayRequestHandler;

public class AuthorizerHandler extends SpringBootApiGatewayRequestHandler {


    @Override
    public Object handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        System.out.println("In the handler...");
        return super.handleRequest(event, context);
    }
}
