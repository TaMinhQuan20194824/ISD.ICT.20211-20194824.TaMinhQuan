package entity.order;

public class RushOrder extends Order{
  
  private String earliestArrivalTime;
  private String latestArrivalTime;
  
  public String getEarliestArrivalTime() {
    return earliestArrivalTime;
  }
  public void setEarliestArrivalTime(String earliestArrivalTime) {
    this.earliestArrivalTime = earliestArrivalTime;
  }
  public String getLatestArrivalTime() {
    return latestArrivalTime;
  }
  public void setLatestArrivalTime(String latestArrivalTime) {
    this.latestArrivalTime = latestArrivalTime;
  }
  
  
}
