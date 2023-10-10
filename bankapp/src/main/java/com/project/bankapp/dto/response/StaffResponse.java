package com.project.bankapp.dto.response;

import lombok.Data;

@Data
public class StaffResponse {
    private Long staffId;
    private String staffUserName;
    private Status status;

    //constructor
    public StaffResponse() {
        // Default constructor
    }

}
