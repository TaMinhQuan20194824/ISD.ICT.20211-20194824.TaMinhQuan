package controller;

import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * this class controls the flow of place rush order usecase in our AIMS project.
 * 
 * @author taquan
 * @version 1.1
 */
public class PlaceRushOrderController extends PlaceOrderController {

  /**
   * Just for logging purpose
   */
  private static Logger LOGGER = utils.Utils.getLogger(PlaceRushOrderController.class.getName());

  public void placeRushOrder() {

  }

  private void changeFormula() {
  };

  /**
   * This method validate the arrival time of media.
   * 
   * @param earliestArrivalTime - the earliest time for medias to arrive
   * @param latestArrivalTime   - the latest time for medias to arrive
   * @return validation result: true or false
   */
  public boolean validateArrivalTime(String earliestArrivalTime, String latestArrivalTime) {
    // check input time are not null
    if (earliestArrivalTime == null) {
      return false;
    }
    if (latestArrivalTime == null) {
      return false;
    }

    // split input time
    int[] earliestArrivalTimeToken = new int[3];
    int[] latestArrivalTimeToken = new int[3];

    int i = 0;
    for (String x : earliestArrivalTime.split("-")) {
      earliestArrivalTimeToken[i] = Integer.parseInt(x);
      i++;
    }

    i = 0;
    for (String x : latestArrivalTime.split("-")) {
      latestArrivalTimeToken[i] = Integer.parseInt(x);
      i++;
    }

    // check latest time > earliest time
    if (earliestArrivalTimeToken[2] < latestArrivalTimeToken[2]) {
      return true;
    } else if (earliestArrivalTimeToken[2] == latestArrivalTimeToken[2]) {
      if (earliestArrivalTimeToken[1] < latestArrivalTimeToken[1]) {
        return true;
      } else if (earliestArrivalTimeToken[1] == latestArrivalTimeToken[1]) {
        if (earliestArrivalTimeToken[0] < latestArrivalTimeToken[0]) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

}
