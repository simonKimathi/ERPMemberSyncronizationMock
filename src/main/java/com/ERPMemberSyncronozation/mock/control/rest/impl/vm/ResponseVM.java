package com.ERPMemberSyncronozation.mock.control.rest.impl.vm;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseVM {
    private boolean success;
    private String message;
}
