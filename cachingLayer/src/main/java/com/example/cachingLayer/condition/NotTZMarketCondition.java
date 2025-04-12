package com.example.cachingLayer.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class NotTZMarketCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String market = context.getEnvironment().getProperty("market");
        return !market.equalsIgnoreCase("tanzania");
    }
}
