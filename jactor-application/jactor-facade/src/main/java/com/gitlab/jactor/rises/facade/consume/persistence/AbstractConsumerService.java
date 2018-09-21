package com.gitlab.jactor.rises.facade.consume.persistence;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Arrays.asList;

class AbstractConsumerService {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    AbstractConsumerService(RestTemplate restTemplate, String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    <T> ResponseEntity<T> getForEntity(String url, Class<T> requestedClass) {
        return RethrowHttpClientError.tryExecution(url, executionUrl -> restTemplate.getForEntity(executionUrl, requestedClass));
    }

    <T> ResponseEntity<T> exchangePost(String url, HttpEntity<T> httpEntity, Class<T> requstedClass) {
        return RethrowHttpClientError.tryExecution(url, executionUrl -> restTemplate.exchange(executionUrl, HttpMethod.POST, httpEntity, requstedClass));
    }

    <T> T bodyOf(ResponseEntity<T> responseEntity) {
        if (responseEntity == null) {
            throw new IllegalStateException("No response from REST service");
        }

        if (isNot2xxSuccessful(responseEntity.getStatusCode())) {
            String badConfiguredResponseMesssage = String.format("Bad configuration of REST service! ResponseCode: %s(%d)",
                    responseEntity.getStatusCode().name(),
                    responseEntity.getStatusCodeValue()
            );

            throw new IllegalStateException(badConfiguredResponseMesssage);
        }

        return responseEntity.getBody();
    }

    private boolean isNot2xxSuccessful(HttpStatus statusCode) {
        return !statusCode.is2xxSuccessful();
    }

    String fullUrl(String url) {
        return baseUrl + url;
    }

    @SuppressWarnings("ConstantConditions") <T> List<T> fetchBodyAsList(ResponseEntity<T[]> responseEntity) {
        return asList(responseEntity.getBody());
    }

    @FunctionalInterface
    private interface RethrowHttpClientError<T> {
        ResponseEntity<T> execute(String url);

        static <E> ResponseEntity<E> tryExecution(String url, RethrowHttpClientError<E> rethrowHttpClientError) {
            try {
                return rethrowHttpClientError.execute(url);
            } catch (RestClientException e) {
                throw new IllegalStateException("Unable to execute: " + url, e);
            }
        }
    }
}
