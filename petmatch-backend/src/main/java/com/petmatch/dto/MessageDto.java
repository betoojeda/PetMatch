package com.petmatch.dto;

import lombok.Data;

@Data
public class MessageDto {
    private Long matchId;
    private Long senderUserId;
    private String text;
}
