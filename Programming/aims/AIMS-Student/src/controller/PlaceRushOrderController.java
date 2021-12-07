package controller;


/**
 * this class controls the flow of place rush order usecase in our AIMS project.
 * 
 * @author taquan
 * 
 */
public class PlaceRushOrderController extends BaseController {

	/**
	 * This method validate the arrival time of media.
	 * 
	 * @param earliestArrivalTime
	 * @param latestArrivalTime
	 * @return validation result: true or false
	 */
	public boolean validateArrivalTime(String earliestArrivalTime, String latestArrivalTime) {
		// check input time are not null
		if (earliestArrivalTime == null) return false;
		if (latestArrivalTime == null) return false;
		
		// split input time 
		int[] earliestArrivalTimeToken = new int[3];
		int[] latestArrivalTimeToken = new int[3];
		
		int i = 0;
		for(String x:earliestArrivalTime.split("-")) {
			earliestArrivalTimeToken[i]= Integer.parseInt(x); 
			i++;
		}
		
		i = 0;
		for(String x:latestArrivalTime.split("-")) {
			latestArrivalTimeToken[i]= Integer.parseInt(x); 
			i++;
		}
		
		// check latest time > earliest time 
		if (earliestArrivalTimeToken[2] < latestArrivalTimeToken[2]) {
			return true;
		} else if (earliestArrivalTimeToken[2] == latestArrivalTimeToken[2]) {
			if (earliestArrivalTimeToken[1] < latestArrivalTimeToken[1]) {
				return true;
			} else if(earliestArrivalTimeToken[1] == latestArrivalTimeToken[1]) {
				if (earliestArrivalTimeToken[0] < latestArrivalTimeToken[0]) {
					return true;
				} else return false;
			} else return false;
		} else return false;	
	}
	
}
