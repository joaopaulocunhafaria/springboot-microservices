package com.ms.email.dtos;

import java.util.UUID;

public record EmailRecordDto(UUID uuid, String emailTo,String subject, String text) {
    
}
