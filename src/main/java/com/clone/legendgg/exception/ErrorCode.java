package com.clone.legendgg.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    SUMMONER_NOT_FOUNDED(HttpStatus.NOT_FOUND, "해당 소환사는 없습니다.");

    private HttpStatus status;
    private String message;
}
