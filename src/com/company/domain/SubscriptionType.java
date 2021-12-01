package com.company.domain;

public enum SubscriptionType {

    YOUTH("youth"),
    ADULT("adult"),
    SENIOR("senior"),
    PASSIVE("passive");

    private String type;

    SubscriptionType(String subscriptionType){
        type = subscriptionType;
    }

    public String getType() {
        return type;
    }
}