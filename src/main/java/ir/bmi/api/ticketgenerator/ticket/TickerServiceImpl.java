package ir.bmi.api.ticketgenerator.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TickerServiceImpl implements TicketService {

    private static Integer sequence;
    private static Integer currentSequnceEnd;

    @Autowired
    private TicketRepository ticketRepository;

    @Value("${ticket.sequence.bunch}")
    private Integer bunch;

    @Value("${ticket.sequence.init}")
    private Integer init;

    @Value("${ticket.sequence.end}")
    private Integer end;

    @Override
    public Integer generate() {

        sequence++;
        if( sequence >= currentSequnceEnd || sequence >= end)
            getNextStartPointAndUpdateDB();

        return sequence;
    }

    @Override
    @Transactional
    public void getNextStartPointAndUpdateDB() {

        if(sequence >= end)
            ticketRepository.save(Ticket.of(1, "Sequence Number", init));

        initSequence();
    }

    @Transactional
    public void initSequence() {
        Optional<Integer> seq = ticketRepository.getLastSequence();
        if(!seq.isEmpty()) {
            sequence = seq.get();
            currentSequnceEnd = seq.get() + bunch;
        }
        ticketRepository.updateSequence(sequence, bunch);
    }

    @Override
    @Transactional
    public void initialize() {

        if(ticketRepository.getById(1) == null)
            ticketRepository.save(Ticket.of(1, "Sequence Number", init));

        initSequence();
    }
}
