package com.sunset.request;

import lombok.Data;

import java.util.List;

@Data
public class DeleteHistoryWeightRequest {
    private List<Long> ids;
}
