package ir.bmi.api.ticketgenerator.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {


    @Autowired
    private TicketService ticketService;

    @GetMapping
    public String getTicket() {
        return ticketService.generate().toString();
    }
}
