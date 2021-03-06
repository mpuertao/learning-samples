package de.rieckpil.learning.order.boundary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import de.rieckpil.learning.order.control.LegacyAuthenticator;
import de.rieckpil.learning.order.control.OrderHistory;
import de.rieckpil.learning.order.control.PaymentProcessor;

public class OrderProcessorTest {

	private OrderProcessor cut;

	@Before
	public void init() {
		this.cut = new OrderProcessor();
		this.cut.authenticator = mock(LegacyAuthenticator.class);
		this.cut.paymentProcessor = mock(PaymentProcessor.class);
		this.cut.orderHistory = mock(OrderHistory.class);
	}

	@Test
	public void testSuccessfulOrder() {
		when(this.cut.authenticator.authenticate()).thenReturn(true);
		this.cut.order();
		verify(this.cut.paymentProcessor).pay();
		verify(this.cut.orderHistory).save(Matchers.anyObject());
	}

	@Test(expected = IllegalStateException.class)
	public void testInvalidUser() {
		when(this.cut.authenticator.authenticate()).thenReturn(false);
		this.cut.order();
	}

}
