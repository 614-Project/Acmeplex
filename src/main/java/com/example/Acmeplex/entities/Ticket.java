package com.example.Acmeplex.entities;

import java.sql.Date;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(name = "total_price")
	private Long totalTicketsPrice;

    @Column(name = "bookedSeats")
	private String bookedSeats;

	@CreationTimestamp
	private Date bookedAt;

	@ManyToOne
	@JoinColumn
	private Show show;

    @Column(name = "show_time")
    private LocalDateTime showTime;

    @Column(name = "expireDate")
    private LocalDateTime expireDate;

    @Column(name = "status")
    private String status = "PENDING";

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Payment payment;

}
