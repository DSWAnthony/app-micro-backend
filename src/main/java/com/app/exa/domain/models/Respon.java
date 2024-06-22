package com.app.exa.domain.models;

import lombok.Data;
import org.eclipse.microprofile.graphql.Type;

@Type
public class Respon {
    private String status;
    private String message;

    public Respon(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Respon{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
