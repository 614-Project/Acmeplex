// package com.example.Acmeplex.entities;

// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import org.hibernate.annotations.CreationTimestamp;

// import java.sql.Date;

// @Entity
// @Table(name = "TICKETS")
// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// public class Ticket {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer ticketId;

//     private Integer totalTicketsPrice;

//     private String bookedSeats;

//     @CreationTimestamp
//     private Date bookedAt;

//     @ManyToOne
//     @JoinColumn
//     private Show showtime;

//     @ManyToOne
//     @JoinColumn
//     private User user;

// }

package com.example.Acmeplex.entities;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Ticket")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
    @SequenceGenerator(name = "ticket_id", sequenceName = "ticket_sequence", initialValue = 1000, allocationSize = 1)
    private Long id;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "Location")
    private String location;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "currency")
    private String currency = "USD";

    @Column(name = "total")
    private Long total;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "seat")
    private String seat;

    @Column(name = "show_time")
    private LocalDateTime showTime;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "expireDate")
    private LocalDateTime expireDate;

    @Column(name = "status")
    private String status = "pending";

    // @OneToOne(mappedBy = "ticket")  // This refers to the Payment entity's "ticket" field
    // private Payment payment;

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Payment payment;

}
