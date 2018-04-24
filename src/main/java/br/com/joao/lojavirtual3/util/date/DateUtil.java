package br.com.joao.lojavirtual3.util.date;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static boolean dataFinalEAntesDaDataInicial(Date dataInicial, Date dataFinal) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataInicial);
		return calendar.after(dataFinal);
	}

}
