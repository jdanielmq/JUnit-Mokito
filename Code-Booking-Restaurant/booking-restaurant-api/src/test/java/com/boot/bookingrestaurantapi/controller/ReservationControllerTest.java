package com.boot.bookingrestaurantapi.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.controllers.ReservationController;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.jsons.ReservationRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.ReservationService;

public class ReservationControllerTest {
	
	private static final String SUCCES_STATUS = "Succes";
	private static final String SUCCES_CODE = "200 OK";
	private static final String OK = "OK";
	
	CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();
	
	private static final Long RESERVATION_ID = 1L;
	private static final Long TURN_ID = 1L;
	private static final Long PERSON_ID = 1L;
	private static final Date DATE = new Date();
	private static final String LOCATOR = "BURGER 2";
	
	
	private static final ReservationRest RESERVATION_REST = new ReservationRest();
	
		
	
	@Mock
	ReservationService reservationService;
	
	@InjectMocks
	ReservationController reservationController;
	
	@Before
	public void init() throws BookingException {
		
		MockitoAnnotations.initMocks(this);

		CREATE_RESERVATION_REST.setDate(DATE);
		CREATE_RESERVATION_REST.setPerson(PERSON_ID);
		CREATE_RESERVATION_REST.setRestaurantId(RESERVATION_ID);
		CREATE_RESERVATION_REST.setTurnId(TURN_ID);
		
		RESERVATION_REST.setDate(DATE);
		RESERVATION_REST.setLocator(LOCATOR);
		RESERVATION_REST.setPerson(PERSON_ID);
		RESERVATION_REST.setRestaurantId(RESERVATION_ID);
		RESERVATION_REST.setTurnId(TURN_ID);
		
		when(reservationService.getReservation(RESERVATION_ID)).thenReturn(RESERVATION_REST);
				
		Mockito.when(reservationService.createReservation(CREATE_RESERVATION_REST)).thenReturn(LOCATOR);
	}
	
	
	@Test
	public void getReservationByIdTest() throws BookingException {
		final BookingResponse<ReservationRest> response = reservationController.getReservationById(RESERVATION_ID);
		
		assertEquals(response.getStatus(), SUCCES_STATUS); // respuestas de estatus
		assertEquals(response.getCode(), SUCCES_CODE); // codigo de la respuesta
		assertEquals(response.getMessage(), OK); // codigo por si salio todo bien
		assertEquals(response.getData(), RESERVATION_REST); // falta el objetos	
		
	}
	
	@Test
	public void createReservationTest() throws BookingException{
		BookingResponse<String> response = reservationController.createReservation(CREATE_RESERVATION_REST);
		
		assertEquals(response.getStatus(), SUCCES_STATUS); // respuestas de estatus
		assertEquals(response.getCode(), SUCCES_CODE); // codigo de la respuesta
		assertEquals(response.getMessage(), OK); // codigo por si salio todo bien
		assertEquals(response.getData(), LOCATOR); // falta el objetos, Nota Solo cuando es create es se retorna un locator en string		
		
	}
	
	

}
