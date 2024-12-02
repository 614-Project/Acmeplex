package com.example.Acmeplex.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowRequest {
    private LocalTime showStartTime;
    private Date showDate;
    private Integer theaterId;
    private Integer movieId;
}
