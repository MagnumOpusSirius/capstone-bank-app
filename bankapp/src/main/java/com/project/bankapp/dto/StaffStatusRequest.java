package com.project.bankapp.dto;

import com.project.bankapp.dto.response.Status;
import lombok.Data;

@Data
public class StaffStatusRequest {
    private Long staffId;
    private Status status;

    //constructor
    public StaffStatusRequest() {
        // Default constructor
    }
}
