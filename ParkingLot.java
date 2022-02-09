
/**
 * Parking lot object to be used in the hashtable for the ParkLotHashTable class
 * @author ljogal
 *
 */
public class ParkingLot {
	
	private String addres; // Address of where the lot is located
	private int lotNum; // lot number of the parking lot, also the key for Parking lot hashtable
	final private int LOT_CAP; // lot capacity for amount of cars in lot
	private int occu; // number of occupied spots
	private int[] nearLots; // nearby lots contains the 3 closest lots
	
	
	/**
	 * Parameter to make a new parking lot object
	 * @param addres
	 * @param lotNum
	 * @param lotCap
	 * @param occu
	 * @param arr
	 */
	public ParkingLot(String addres, int lotNum, int lotCap, int occu, int[] arr) {
		this.addres = addres;
		this.lotNum = lotNum;
		this.LOT_CAP = lotCap;
		this.occu = occu;
		this.nearLots = arr;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getAddress() {
		return this.addres;
	}
	/**
	 * 
	 * @return
	 */
	public int getLotNum() {
		return this.lotNum;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getLotCap() {
		return this.LOT_CAP;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getOccu() {
		return this.occu;
	}
	
	/**
	 * 
	 * @return
	 */
	public int [] getNearLots() {
		return this.nearLots;
	}
	
	/**
	 * Checks to see if lot is filled or not
	 * @return
	 */
	public boolean filled() {
		// Incase a numbers occupancy is greater than capacity, just treat as full
		return LOT_CAP - occu <= 0;
	}
	
	/**
	 * Tries to occupy a lot it there is still space
	 * @return True if can occupy, false if lot is filled
	 */
	public boolean park() {
		if(filled()) {
			return false;
		}
		
		occu++;
		
		return true;
		
	}
	
	public String strNearLots() {
		return "[" + nearLots[0] + ", " + nearLots[1] + ", " + nearLots[2] + "]";
	}
	
	/**
	 * Returns all data pertaining to the lot
	 */
	@Override
	public String toString() {
		
		return "Address: " + addres  + "\n"
			 + "Lot Number: " + lotNum  + "\n"
			 + "Capacity: " + LOT_CAP  + "\n"
			 + "Occupancy: " + occu  + "\n"
			 + "Nearest Lots: " + this.strNearLots()  + "\n";
	}
}
