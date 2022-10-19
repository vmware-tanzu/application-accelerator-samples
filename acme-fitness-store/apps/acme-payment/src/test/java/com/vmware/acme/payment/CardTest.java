package com.vmware.acme.payment;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {


	@Test
	void containsMissingInfo_shouldReturnTrueForMissingNumber() {
		assertThat(new Card("", "2070", "12", "123").containsMissingInfo()).isTrue();
	}

	@Test
	void containsMissingInfo_shouldReturnTrueForMissingExpirationYear() {
		assertThat(new Card("1234", "", "12", "123").containsMissingInfo()).isTrue();
	}

	@Test
	void containsMissingInfo_shouldReturnTrueForMissingCCV() {
		assertThat(new Card("1234", "2070", "12", "").containsMissingInfo()).isTrue();
	}

	@Test
	void containsMissingInfo_shouldReturnTrueForMissingExpirationMonth() {
		assertThat(new Card("1234", "2070", "", "123").containsMissingInfo()).isTrue();
	}

	@Test
	void containsMissingInfo_shouldReturnFalseForCompleteData() {
		assertThat(new Card("1234", "2070", "12", "123").containsMissingInfo()).isFalse();
	}

	@Test
	void isExpired_shouldReturnTrueForExpiredCard() {
		String expirationYear = String.valueOf(LocalDate.now().getYear() - 1);
		assertThat(new Card("1234", expirationYear, "12", "123").isExpired()).isTrue();
	}

	@Test
	void isExpired_shouldReturnFalseForCurrentCard() {
		String expirationYear = String.valueOf(LocalDate.now().getYear() + 5);
		assertThat(new Card("1234", expirationYear, "12", "123").isExpired()).isFalse();
	}

	@Test
	void isValidCardNumber_ShouldReturnTrueForCardNumberDivisibleByFour() {
		assertThat(new Card("1234", "2070", "12", "123").isValidCardNumber()).isTrue();
		assertThat(new Card("12341234", "2070", "12", "123").isValidCardNumber()).isTrue();
		assertThat(new Card("123412341234", "2070", "12", "123").isValidCardNumber()).isTrue();
		assertThat(new Card("1234123412341234", "2070", "12", "123").isValidCardNumber()).isTrue();
	}

	@Test
	void isValidCardNumber_ShouldReturnTrueForCardNumberNotDivisibleByFour() {
		assertThat(new Card("123", "2070", "12", "123").isValidCardNumber()).isFalse();
	}
}