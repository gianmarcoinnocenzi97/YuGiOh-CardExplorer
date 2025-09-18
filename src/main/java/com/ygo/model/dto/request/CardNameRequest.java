package com.ygo.model.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CardNameRequest(
        @NotEmpty(message = "Names list cannot be empty")
        List<String> names
) {}
