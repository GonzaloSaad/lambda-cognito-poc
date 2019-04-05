package com.birrete.function;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.InitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.InitiateAuthResult;
import com.birrete.common.Environment;
import com.birrete.model.BirreteAuthResponse;
import com.birrete.model.UserData;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component("cognitoAuthorizerFunction")
public class CognitoAuthorizerFunction implements Function<UserData, BirreteAuthResponse> {

    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    @Override
    public BirreteAuthResponse apply(UserData userData) {

        AWSCognitoIdentityProvider client = AWSCognitoIdentityProviderClient.builder()
                .withRegion(Regions.US_EAST_1).build();

        InitiateAuthRequest request = new InitiateAuthRequest()
                .withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .withClientId(Environment.getClientID())
                .addAuthParametersEntry(USERNAME, userData.getUsername())
                .addAuthParametersEntry(PASSWORD, userData.getPassword());

        try {
            Optional<InitiateAuthResult> result = Optional.ofNullable(client.initiateAuth(request));
            return result.map(r -> BirreteAuthResponse.getAuthorizedResponse(userData.getUsername(), r.getSession()))
                    .orElse(BirreteAuthResponse.getErrorResponse());
        } catch (Exception e) {
            return BirreteAuthResponse.getErrorResponse();
        }


    }
}
