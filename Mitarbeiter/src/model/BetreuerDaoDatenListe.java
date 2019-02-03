package model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
  
public class BetreuerDaoDatenListe
{
	private static BetreuerDaoDatenListe handler = null;

	public static BetreuerDaoDatenListe getInstance()throws Exception
	{
		if(handler == null){
			handler = new BetreuerDaoDatenListe();
		}
		return handler;
	}
        
	public List<BetreuerKurz> getAllDAten() throws InterruptedException
	{
		List<BetreuerKurz> list = new ArrayList<BetreuerKurz>();

		BetreuerKurz betreuer1 = new BetreuerKurz(1, "Klaus", LocalDate.of(1999, 12, 1), 40000.00);
		BetreuerKurz betreuer2 = new BetreuerKurz(2, "Erwin", LocalDate.of(2012, 1, 1), 41000.00);
		BetreuerKurz betreuer3 = new BetreuerKurz(3, "Fritz", LocalDate.of(1998, 3, 1), 42000.00);
		BetreuerKurz betreuer4 = new BetreuerKurz(4, "Hans", LocalDate.of(2016, 4, 1), 43000.00);
		BetreuerKurz betreuer5 = new BetreuerKurz(5, "Willi", LocalDate.of(2011, 5, 26), 44000.00);
		BetreuerKurz betreuer6 = new BetreuerKurz(6, "Emil", LocalDate.of(2011, 5, 26), 44000.00);
		BetreuerKurz betreuer7 = new BetreuerKurz(7, "Ernst", LocalDate.of(2011, 5, 26), 44000.00);
		BetreuerKurz betreuer8 = new BetreuerKurz(8, "Franka", LocalDate.of(2011, 5, 26), 44000.00);
		BetreuerKurz betreuer9 = new BetreuerKurz(9, "Erika", LocalDate.of(2011, 5, 26), 44000.00);
		BetreuerKurz betreuer10 = new BetreuerKurz(10, "Agathe", LocalDate.of(2011, 5, 26), 44000.00);
		BetreuerKurz betreuer11 = new BetreuerKurz(11, "Jonas", LocalDate.of(2011, 5, 26), 44000.00);
		
		
		
		list.add(betreuer1);
		list.add(betreuer2);
		list.add(betreuer3);
		list.add(betreuer4);
		list.add(betreuer5);
		list.add(betreuer6);
		list.add(betreuer7);
		list.add(betreuer8);
		list.add(betreuer9);
		list.add(betreuer10);
		list.add(betreuer11);
	
		Thread.sleep(1500);
		return list;
	}

}
