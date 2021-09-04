package ir.bmi.api.ticketgenerator.ticket;

public interface TicketService {

    Integer generate();

    void getNextStartPointAndUpdateDB();

    void initialize();
}
