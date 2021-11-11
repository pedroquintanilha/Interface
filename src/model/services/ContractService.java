package model.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService  {
	
	
	private OnlinePaymentService onlinePaymentService;
	
	SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, int months) throws ParseException  {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(contract.getDate());
		int month = 2 + cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		Date dueDate;
		
		for(int i = 1; i<=months; i++) {
			double initialAmount = onlinePaymentService.interest(contract.getTotalValue()/months, i);
			double amount = onlinePaymentService.paymentFee(initialAmount);
			dueDate = sdf.parse(day+"/"+month+"/"+year);
			contract.getListOfInstallment().add(new Installment (dueDate,amount));
			month++;	
		}	
	}
}
