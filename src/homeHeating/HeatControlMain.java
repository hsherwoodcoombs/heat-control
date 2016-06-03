package homeHeating;

class HeatControlMain {
	 public static void main(String [] args){
			//
			//Testing heatCntr 
			//
		 	String[] roomNumbers = {"room1", "room2", "room3"};	
			House house1 = new House(roomNumbers);
			HeatControl heatCntr = new HeatControl();
			heatCntr.turnOn();
			System.out.println(heatCntr.getStatus());
			try
			{
				heatCntr.configure(house1, 75.00);
			}
			catch (Exception e)
			{
				System.out.println(e);
				System.exit(-1);
			}
			System.out.printf(house1.toString());
			heatCntr.setDuration(90);
			heatCntr.start();
			System.out.println(HeatControl.getCurrentTime());
			System.out.println(heatCntr.getTimeLeft()); 
			heatCntr.showTemperatureInF();
			heatCntr.increaseTemperatureInF(6);
			//
			//Setting temperature to a room that doesn't exist.
			//
			try {
				heatCntr.setTemperatureInF("room8",25);     
				}		 
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
			//
			// turnOn() when it's already ON
			//
			heatCntr.turnOn();                              
			heatCntr.showTemperatureInF();
			//
			// Show temperature for room that doesn't exist
			//
			heatCntr.showTemperatureInFByRoom("room9"); 
			//
			//increase temperature by room - temperature below the MAX
			//
			try
			{
				heatCntr.increaseTemperatureInF("room1",5);
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
			//
			//increase temperature by room - temperature exceeds MAX
			//
			try
			{
				heatCntr.increaseTemperatureInF("room1",30);
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
			
			heatCntr.showTemperatureInFByRoom("room1");
			//
			//Testing Zyra2ThermostatTest
			//
			System.out.printf("Check Zyra2Thermostat:%n");
			
			String[] house2roomNumbers = {"room4", "room5", "room6"};
			House house2 = new House(house2roomNumbers);
		
	
	 }	
	
}