package com.voronin.technicaltest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type Error response.
 * Available fields:
 * <ul>
 *   <li> {@link Date} timestamp;
 *   <li> {@link String}  status;
 *   <li> {@link String}  message;
 *   <li> {@link String}  details;
 * </ul>
 */
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private Date timestamp;
    private String status;
    private String message;
    private String details;


}
