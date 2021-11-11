package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaylpalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Contract contract;
		ContractService cs;
		
		System.out.println("Enter contract data");
		System.out.print("Number: ");
		int number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		Date date = sdf.parse(sc.next());
		System.out.print("Contract value: ");
		double totalValue = sc.nextDouble();
		contract = new Contract(number, date, totalValue);
		System.out.print("Enter number of installments: ");
		int months = sc.nextInt();
		
		cs = new ContractService(new PaylpalService());
		cs.processContract(contract, months);
		
		System.out.println("Installments:");
		
		for(Installment installment : contract.getListOfInstallment()) {
			System.out.println(installment);
		}
	
		sc.close();
	}

}
