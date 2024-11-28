package com.example.Acmeplex.request;

import lombok.Data;

// import jakarta.validation.constraints.DecimalMax;
// import jakarta.validation.constraints.DecimalMin;
// import jakarta.validation.constraints.Min;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.PastOrPresent;
// import jakarta.validation.constraints.Size;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// import java.time.LocalDate;
// import java.util.List;

import java.time.LocalTime;
import java.util.Date;

@Data
public class ShowRequest {
    private LocalTime showStartTime;
    private Date showDate;
    private Integer theaterId;
    private Integer movieId;
}
