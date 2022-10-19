package com.vmware.acme.payment;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = PaymentController.class)
@Import(PaymentService.class)
class PaymentControllerTest {

	@Autowired
	WebTestClient webTestClient;

	@MockBean
	PaymentService paymentService;

	@Test
	void processPaymentShouldCallService() {
		Card card = new Card("1234123412341234", "2070", "12", "123");
		PaymentRequest request = new PaymentRequest(card, "12.00");
		Mockito.when(paymentService.processPayment(request)).thenReturn(new PaymentResponse(true, "transaction successful", "125.00", UUID.randomUUID().toString(), HttpStatus.OK.value()));

		webTestClient.post().uri("/pay")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(request))
				.exchange()
				.expectStatus().isOk();
	}
}