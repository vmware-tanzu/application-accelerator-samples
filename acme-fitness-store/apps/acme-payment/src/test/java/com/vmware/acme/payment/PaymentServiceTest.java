package com.vmware.acme.payment;

import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentServiceTest {

	private final PaymentService paymentService = new PaymentService();

	@Test
	void processPayment_shouldBeSuccessfulForValidRequest() {
		Card card = mock(Card.class);
		when(card.isValidCardNumber()).thenReturn(true);
		when(card.isExpired()).thenReturn(false);

		PaymentRequest paymentRequest = mock(PaymentRequest.class);
		when(paymentRequest.getCard()).thenReturn(card);
		when(paymentRequest.containsMissingData()).thenReturn(false);
		when(paymentRequest.getTotal()).thenReturn("120.00");

		PaymentResponse response = paymentService.processPayment(paymentRequest);

		assertThat(response.getSuccess()).isTrue();
		assertThat(response.getAmount()).isEqualTo(paymentRequest.getTotal());
		assertThat(response.getTransactionID()).isNotBlank();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void processPayment_shouldBeUnsuccessfulForMissingCard() {
		PaymentRequest paymentRequest = mock(PaymentRequest.class);
		when(paymentRequest.getCard()).thenReturn(null);

		PaymentResponse response = paymentService.processPayment(paymentRequest);

		assertThat(response.getSuccess()).isFalse();
		assertThat(response.getAmount()).isEqualTo("0");
		assertThat(response.getTransactionID()).isEqualTo("-1");
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	void processPayment_shouldBeUnsuccessfulForMissingRequestData() {
		Card card = mock(Card.class);

		PaymentRequest paymentRequest = mock(PaymentRequest.class);
		when(paymentRequest.getCard()).thenReturn(card);
		when(paymentRequest.containsMissingData()).thenReturn(true);

		PaymentResponse response = paymentService.processPayment(paymentRequest);

		assertThat(response.getSuccess()).isFalse();
		assertThat(response.getAmount()).isEqualTo("0");
		assertThat(response.getTransactionID()).isEqualTo("-1");
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	void processPayment_shouldBeUnsuccessfulForInvalidCard() {
		Card card = mock(Card.class);
		when(card.isValidCardNumber()).thenReturn(false);
		when(card.isExpired()).thenReturn(false);

		PaymentRequest paymentRequest = mock(PaymentRequest.class);
		when(paymentRequest.getCard()).thenReturn(card);
		when(paymentRequest.containsMissingData()).thenReturn(false);

		PaymentResponse response = paymentService.processPayment(paymentRequest);

		assertThat(response.getSuccess()).isFalse();
		assertThat(response.getAmount()).isEqualTo("0");
		assertThat(response.getTransactionID()).isEqualTo("-2");
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	void processPayment_shouldBeUnsuccessfulForExpiredCard() {
		Card card = mock(Card.class);
		when(card.isValidCardNumber()).thenReturn(true);
		when(card.isExpired()).thenReturn(true);

		PaymentRequest paymentRequest = mock(PaymentRequest.class);
		when(paymentRequest.getCard()).thenReturn(card);
		when(paymentRequest.containsMissingData()).thenReturn(false);

		PaymentResponse response = paymentService.processPayment(paymentRequest);

		assertThat(response.getSuccess()).isFalse();
		assertThat(response.getAmount()).isEqualTo("0");
		assertThat(response.getTransactionID()).isEqualTo("-3");
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}
}