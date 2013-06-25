package pl.allegro.edu.tdd;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.allegro.edu.tdd.dao.FlightDao;
import pl.allegro.edu.tdd.dao.SeatDao;
import pl.allegro.edu.tdd.domain.Seat;
import pl.allegro.edu.tdd.exception.SeatAlreadyBookedException;

import com.googlecode.catchexception.CatchException;

@RunWith(MockitoJUnitRunner.class)
public class FlightManagerTest {

  @Mock
  private FlightDao flightDao;
  @Mock
  private SeatDao seatDao;

  private final static BigDecimal AVERAGE = new BigDecimal("153");
  private final static BigDecimal $10 = new BigDecimal("10");
  private final static BigDecimal $50 = new BigDecimal("50");

  @InjectMocks
  private FlightManager flightManager = new FlightManager();

  @Test
  public void shouldReturnAvailableSeats() {
    // given:
    when(seatDao.countAvailableSeats("LOT102")).thenReturn(5);

    // when:
    int seats = flightManager.getAvailableSeats("LOT102");

    // then:
    assertEquals(5, seats);
  }

  @Test
  public void shouldReturnZeroForMissingFlight() {
    // given:

    // when:
    int seats = flightManager.getAvailableSeats("LOT102");

    // then:
    assertEquals(0, seats);
  }

  @Test
  public void shouldReturnCheapestSeatForFlight() {
    // given:
    when(seatDao.findSeatByFlightNoOrderByPriceDesc("LOT102")).thenReturn(Arrays.asList(new Seat($10), new Seat($50)));

    // when:
    BigDecimal price = flightManager.getCheapestSeatPrice("LOT102");

    // then:
    assertEquals($10, price);
  }

  @Test
  public void shouldThrowExceptionForMissingFlight() {
    // given:
    when(seatDao.findSeatByFlightNoOrderByPriceDesc("LOT102")).thenReturn(new ArrayList<Seat>());

    // when:
    CatchException.catchException(flightManager).getCheapestSeatPrice("LOT102");

    // then:
    Assert.assertTrue(CatchException.caughtException() instanceof NoSeatsAvailableException);
  }

  @Test
  public void shouldThrowExceptionForBookedSeat() {
    // given:
    when(seatDao.findSeatByFlightNoAndSeatNo("LOT102", "15A")).thenReturn(new Seat($10, false));

    // when:
    CatchException.catchException(flightManager).bookSeat("LOT102", "15A");
    // then:
    Assert.assertTrue(CatchException.caughtException() instanceof SeatAlreadyBookedException);
  }

  @Test
  public void shouldThrowExceptionForMissingSeat() {
    // given:
    when(seatDao.findSeatByFlightNoAndSeatNo("LOT102", "15A")).thenReturn(null);

    // when:
    CatchException.catchException(flightManager).bookSeat("LOT102", "15A");

    // then:
    Assert.assertTrue(CatchException.caughtException() instanceof NoSeatsAvailableException);
  }

  @Test
  public void shouldBookAvailableSeat() {
    // given:
    when(seatDao.findSeatByFlightNoAndSeatNo("LOT102", "15A")).thenReturn(new Seat($10, true));
    ArgumentCaptor<Seat> seatCaptor = ArgumentCaptor.forClass(Seat.class);

    // when:
    flightManager.bookSeat("LOT102", "15A");

    // then:
    verify(seatDao).save(seatCaptor.capture());
    assertEquals(false, seatCaptor.getValue().isAvailable());
  }

  @Test
  public void shouldEvaluateAveragePriceForAvailableSeats() {
    // given:
    when(seatDao.calculateAveragePriceForAvailable("LOT102")).thenReturn(AVERAGE);

    // when:
    BigDecimal calculatedAverage = flightManager.calculateAveragePriceForAvailableSeats("LOT102");

    // then:
    assertEquals(AVERAGE, calculatedAverage);
  }
}
