package ir.bmi.api.ticketgenerator.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    @Query(value = "SELECT sequence_no FROM ticket WHERE id = 1", nativeQuery = true)
    Optional<Integer> getLastSequence();

    @Modifying
    @Transactional
    @Query(value = "UPDATE ticket SET sequence_no = sequence_no + :bunch WHERE id = 1 and sequence_no = :start", nativeQuery = true)
    Integer updateSequence(@Param("start") Integer start, @Param("bunch") Integer bunch);

}
