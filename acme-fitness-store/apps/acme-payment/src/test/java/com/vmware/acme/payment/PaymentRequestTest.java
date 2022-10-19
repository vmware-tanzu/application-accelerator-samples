package com.vmware.acme.payment;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentRequestTest {

	@Test
	void containsMissingData_shouldReturnFalseForValidData() {
		Card card = mock(Card.class);
		when(card.containsMissingInfo()).thenReturn(false);

		assertThat(new PaymentRequest(card, "12.00").containsMissingData()).isFalse();
	}

	@Test
	void containsMissingData_shouldReturnTrueForMissingNumber() {
		Card card = mock(Card.class);
		when(card.containsMissingInfo()).thenReturn(false);

		assertThat(new PaymentRequest(card, "").containsMissingData()).isTrue();
	}

	@Test
	void containsMissingData_shouldReturnTrueForMissingCardData() {
		Card card = mock(Card.class);
		when(card.containsMissingInfo()).thenReturn(true);

		assertThat(new PaymentRequest(card, "12.00").containsMissingData()).isTrue();
	}
}