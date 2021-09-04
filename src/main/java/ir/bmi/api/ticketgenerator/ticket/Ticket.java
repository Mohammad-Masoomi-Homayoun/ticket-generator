package ir.bmi.api.ticketgenerator.ticket;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Entity(name = "Ticket")
@Table(name = "Ticket")
public class Ticket {

    @Id
    private Integer id;
    private String name;
    private Integer sequenceNo;
}
