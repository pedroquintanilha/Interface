package model.services;

public class PaylpalService implements OnlinePaymentService {
	
	private static final double PAYMENT_FEE = 0.02;
	private static final double MONTHLY_INTEREST = 0.01;
	
	@Override
	public double paymentFee(double amount) {
		return amount + (amount * PAYMENT_FEE);
	}
	
	@Override
	public double interest(double amount, int months) {
		return amount + (MONTHLY_INTEREST*amount*months);
	}

}
