package com.ygo.model.dto.request;

public record EffectRequest(
         String condition,
         String cost,
         String resolution,
         String explanation
) {}
