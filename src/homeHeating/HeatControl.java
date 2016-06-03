
package homeHeating;
import java.util.Date;
import java.util.Calendar;

/**
 * HeatControl.java  Illustrates a home thermostat system which allows 
 * home owners to control the temperature in their entire home or by room. 
 * @author HaleySherwood
 */

interface Thermostat {   
	double getCurrentTempF();
	void setTemperatureInF(double degrees);
	void showTemperatureInF();
}

/**
 * holds room's number and temperature
 */
class Room {
	private String roomNumber;
	private double currentTemp;
	
	Room (){       
	}
	
	/**
	 * Constructor that sets the rommNumber of Room
	 * @param roomNumber - to initialize the roomNuber
	 * 	 */
	Room (String roomNumber ){
		this.roomNumber = roomNumber;
	
	}
	
	/**
	 * Getter Method for Room
	 * @return currentTemp - currentTemp of Room
	 */
   public double getCurrentTemp(){
		return currentTemp;
		}
	
	/**
	 * Setter for Room
	 * @param currentTemp - currentTemp of Room
	 */
	public void setCurrentTemp(double currentTemp) {
		this.currentTemp = currentTemp;
	}
	
	String getRoomNumber() {
		return roomNumber;
	}
	
	@ Override
	public String toString (){
		return "Room Number = " + roomNumber + 
			    " Current temp in room = " + currentTemp + " F";
	}
}	
	 
/**
 * House.java - Illustrates a house with array of Room 
 * @author RydaNofal
  */
class House {
    private Room [] rooms;                             
    
    House () {
    }
    
   /**
    * Constructor used to create an array rooms of Room
    * and initializes the roomNumbers in Room 
    * @param roomNumbers - to initialize roomNumbers in Room
    */
	public House(String[] roomNumbers){
		
		rooms = new Room [roomNumbers.length];
		for (int i = 0; i < roomNumbers.length ; i++)
			rooms[i] = new Room(roomNumbers[i]); 
		}                                  
		
	public Room[] getRooms() {
		return rooms;
	}
	
	/**
	 * Calculates the average temperature in all rooms which is 
	 * equal to currentTempF in HeatControl
	 * @return average temperature of the house 
	 */
	public double  averageTemperature(){
		double sum =0.0; 
		for (Room room : rooms )
			sum += room.getCurrentTemp();
		return sum/rooms.length;
	}
	
	/**
	 * Finds a room in house given the room number 
	 * @param roomId - roomNumber in Room
	 * @return Room - a room in rooms 
	 */
	public Room getRoomById(String roomId){
		for (Room room: rooms)
			if (room.getRoomNumber().contentEquals(roomId))
				return room;
				return null;
	}
	
	
    @Override
	public String toString() {
		String roomsString = ""; 
		for (int i = 0; i < rooms.length; i++)
			roomsString += rooms[i].toString() + "%n";
		return roomsString;
		//return Arrays.deepToString(rooms);
	}
}

/**
 * Status Represents the status of the thermostat ON or OFF
 * status is a data member in HeatControl    
 */
enum  Status {
	ON, OFF
}


/**
*Maintains a thermostat and provides its functionality that includes: 
*Chooses the temperature (in Fahrenheit ) for the entire home or by room. 
*Choose the duration (minutes) for which the temperature should be maintained.
*Displays the current temperature in Fahrenheit
*/
public class HeatControl implements Thermostat { 
    protected static double maxTemperature = 90.00;
	protected House home;
	private Status status;
	private double currentTempF;          
    private int duration;                
	private Date startTime, endTime;
	private double defaultTemperature;     // degrees F 
	
	
	/** No argument constructor that creates an instance of HeatControl
	 *and sets the status of the thermostat to OFF.
	*/
	public HeatControl(){
		setStatus(Status.OFF);
	
	}
	
	/**
	 *sets defaultTemperature data member and calls setTemperatureInF(defaultTemp) 
	 *that sets temperature in each room and the currentTemperature of house to defaultTemp.
	 *Propagates the Exception from setting the default temperature.
	 * @param house - Initializes home to house 
	 * @param defaultTemp - the value to which to set the defaultTemperature. 
	 * @throws Exception - if the status is not OFF cannot configure
	 */
	public void configure(House house, double defaultTemp ) throws Exception {
		
		if (status == Status.OFF) throw new Exception(" Can't configure - must turn on Thermostat");
		home = house;
		try 
		{
			setDefaultTemperature(defaultTemp);
			setTemperatureInF(defaultTemp);
		}
		catch (Exception e)
		{
			
			throw e ;
		}
	}
	
	Status getStatus() {
		return status;
	}
	
	void setStatus(Status status) {
		this.status = status;
	}
    
	public double getCurrentTempF() {
		return currentTempF;
	}
	
	/**
	 *  Setter method that sets the CurrentTempF 
	 * @param currentTempF the value in Fahrenheit to 
	 * @throws Exception - if currentTempf > maxTemperature  allowed
	 */
	void setCurrentTempF(double currentTempF) throws Exception {
		if (currentTempF > maxTemperature) {
            throw new Exception("CurrentTempF value is bigger than Max  " + maxTemperature + " F");
        }
		this.currentTempF = currentTempF;
	}
	
	/**
	 * Setter method for defaultTemperature 
	 * @param defaultTemp - value of default temperature in Fahrenheit
	 * @throws Exception - if defaultTemp > maxTemperature allowed
	 */
	 void setDefaultTemperature (double defaultTemp) throws Exception {
	    	if (defaultTemp > maxTemperature) {
	            throw new Exception("Default value is bigger than Max  " + maxTemperature + " F");
	        }
	    	defaultTemperature = defaultTemp;
	    }
	
	 double getDefaultTemperature() {
			return defaultTemperature;
		}
	    
    static void setMaxTemperature(double maxTemperature) {
    	HeatControl.maxTemperature = maxTemperature;
    }
    
    static double getMaxTemperature() {
    	return maxTemperature;
    }
    
    /**
     * Setter method that sets startTime  
     * @param startTime - of type Date  
     */
     void setStartTime(Date startTime) {
    	this.startTime = startTime;
    }
       
     /**
      *Setter method that's called from start() each time the thermostat is started.
      *@param endTime - type Date , tells when should the thermostat stop working 
     */
   private void setEndTime(Date endTime) {
	   this.endTime = endTime;
    }
   
   Date getEndTime() {
    	return endTime;
    }
    
   void setDuration(int duration) {
    	this.duration = duration;
    }
    
    int getDuration() {
    	return duration;
    }
    
    /**
     * Extends (increases) the duration of an already set thermostat
     * @param addedDuration - the minutes to be added to the existing duration 
     */
    void increaseDuration(int addedDuration) {
        System.out.println("add  " + addedDuration + " more minutes to the duration ");	
        setDuration(getDuration() + addedDuration);  //calculates the new duration
        Calendar calendar = Calendar.getInstance();     
    	setStartTime(calendar.getTime());
    	calendar.add(Calendar.MINUTE, duration);  
    	setEndTime(calendar.getTime());
    }
    
    /**
     * Sets CurrentTempF of HeatControl to degreesF.
     * For all rooms in home it invokes setCurrentTemp() of Room to set currentTemp of each room to degreesF
     * The method catches exception if degreesF > max temperature allowed
     * @param degreesF - degrees in Fahrenheit to set temperature  
     */
    public void setTemperatureInF(double degreesF){
    	System.out.println("Set temperature in house to " + degreesF + " F");
 	   try 
 	   {
 		  setCurrentTempF(degreesF); //checks not exceeding maxTemperature and sets the currentTempF 
 		  for (Room room : home.getRooms())    
 	 		   room.setCurrentTemp(degreesF);
 		   
 	   }
 	   catch (Exception e)
 	   {
 		   System.out.println(e);
 	   }
    }
    
    /**
     *Sets the temperature of roomId to degreesF then updates then calculates the average temperature
     *and updates the CurrentTempF 
     *@param roomId - the room number to be updated 
     *@param degreesF - the new temperature in Fahrenheit
     *@throws Exception - if degreesF > maxTemperature  allowed
     */
    public void setTemperatureInF(String roomId, double degreesF) throws Exception{
    	System.out.println("Set temperature of " + roomId);
    	if (degreesF > maxTemperature) 
            throw new Exception("the value assigned to " + roomId + "is bigger than Max" + maxTemperature);
    	Room room = home.getRoomById(roomId);
    	if (room != null) {
    		room.setCurrentTemp(degreesF);
    		setCurrentTempF(home.averageTemperature());
    	}
    	else 
    		System.out.println(roomId + "  does not exist, cannot set temperature.");
    }
    
    
    public void increaseTemperatureInF(double value) {
    	System.out.println("Increase house temperature by " + value+" F") ; 
    	double newCurrentTempF = getCurrentTempF() + value;
 	   	try 
 	   	{
 		   setCurrentTempF(newCurrentTempF);
 		   Room[] rooms = home.getRooms();
 	 	   for ( Room room :rooms)
 	 		   room.setCurrentTemp(newCurrentTempF);
 	   	}
 	   	catch(Exception e)
 	   	{
 		   System.out.println(e);
 	   	}
    }
    
    void increaseTemperatureInF(String roomId, double value) throws Exception {
    	System.out.println("Increase temperature of " + roomId +" by " + value +" F") ;
    	Room room = home.getRoomById(roomId);
    	if (room != null) {
    		double increasedRoomTemp = room.getCurrentTemp() + value;
    		if (increasedRoomTemp > maxTemperature) 
    			throw new Exception ("Can't increase room Temperature above max  "+ maxTemperature + "F");
    		room.setCurrentTemp(increasedRoomTemp);
    		setCurrentTempF(home.averageTemperature()); //CurrentTempF changes when a room temperature does 
    	}
    	else
    		System.out.println(roomId + "does not exist, cannot increase temperature");
    }               
    
    public void turnOn() {
	  if (getStatus() == Status.OFF)  status = Status.ON;
	  else System.out.println("Thermostat is already ON .");
    }
   
   public void turnOff() {
	   if (getStatus() == Status.ON) status = Status.OFF;
   }
   
   /**
    *Starts a thermostat by setting the startTime and endTime. It first checks whether the thermostat
    *has been already started and hasn't finished yet, in which case it allows to increase the duration.
    */
   public void start() {
	  //Calendar calendar = new GregorianCalendar(); Thought to use this  
	  //in the beginning since in class Calendar: abstract method add() 
	  //But I used it and it worked! How does add work without implementing? 
	  
	  Calendar calendar = Calendar.getInstance();     
	  setStartTime(calendar.getTime());
	  calendar.add(Calendar.MINUTE,getDuration());  
	  setEndTime(calendar.getTime());
   }
	
   static Date getCurrentTime() {
	   	   Calendar calendar = Calendar.getInstance();
		   return calendar.getTime();
	   //return new Date();
   }
   
   int getTimeLeft() {
	   System.out.printf("Time left in hours until the thermostat turns off is  ");
	   long timeLeftInMillis =(endTime.getTime() - getCurrentTime().getTime());
	   long timeLeftInHours = Math.round((double)timeLeftInMillis / ( 60*60*1000));
	   return (int)timeLeftInHours;
   }
   
               
   void showTemperatureInFByRoom (String roomId) {
	   Room room = home.getRoomById(roomId);
   		if (room != null) 
   			System.out.println("Temperature in " + roomId + " =  "  + room.getCurrentTemp() + " F");
   		else
   		System.out.println("Cannot show temperature, " + roomId + " does not exist.");
   }
   
   	public void showTemperatureInF() {
		System.out.println("Temperature in house is " + getCurrentTempF() + " F");
	}
}
