package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.GridLayout;
public class PlayerStatistics extends JPanel {
	
	private static final long serialVersionUID = 1L;
	 private boolean DEBUG = false;
/**
 * Create the frame.
 */
	public PlayerStatistics() {
		super(new GridLayout(1,0));

		JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);
}
    
/** Returns an ImageIcon, or null if the path was invalid. */
class MyTableModel extends AbstractTableModel {
    private String[] columnNames = {"Rank",
                                    "Username",
                                    "total game played",
                                    "# of win",
                                    "# of lose",
                                    "points"};
    //sample data 
    private Object[][] data = {
    		{new Integer(01), new String("one") , new Integer(10) , new Integer(5),  new Integer (5) ,calculatePoints(5,5) },
    		{new Integer(02), new String("two") , new Integer(10) , new Integer(4), new Integer (5) ,calculatePoints(4,5) },
    		{new Integer(03), new String("three") , new Integer(10) , new Integer(2), new Integer (1),calculatePoints(2,1) },
    		{new Integer(04), new String("four") , new Integer(10) , new Integer(5), new Integer (5) ,999 },
    		{new Integer(05), new String("five") , new Integer(10) , new Integer(5), new Integer (5),-1 },
    		{new Integer(06), new String("six") , new Integer(10) , new Integer(5), new Integer (5),-1 },
    		{new Integer(07), new String("seven") , new Integer(10) , new Integer(5), new Integer (5) ,-1 },
    		{new Integer(8), new String("eight") , new Integer(10) , new Integer(5), new Integer (5) ,-1 },
    		{new Integer(9), new String("nine") , new Integer(10) , new Integer(5), new Integer (5) ,-1 },
    		{new Integer(10), new String("ten") , new Integer(10) , new Integer(5), new Integer (5) ,-1 }
    };
    	

        public int getColumnCount() {
            return columnNames.length;
        }
 
        public int getRowCount() {
            return data.length;
        }
 
        public String getColumnName(int col) {
            return columnNames[col];
        }
 
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
 
        /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
 
    public boolean isCellEditable(int row, int col) {
    	//all cell non-editable
    	return false;
        }
 
    
    //calculate points
    // points =  5 * win + lose
    /**
     * set and returns the points of user.
     * @param uname
     * @return
     */
    public Integer calculatePoints(String uname){ 
    	int pts = -1;
    	try {
			Object [] stat = getStatisticsOfUname(uname);
			stat[5] = pts = ( 5 * (Integer) stat[3] + (Integer)stat[4]);
			setStatisticsOfUser(uname,stat);
			
    	} catch (StatisticsNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pts;
    	
    }
    public Integer calculatePoints(int win , int lost){
    	return 5 * win + lost;
    }
    //recalculate the entire list and sort according to rank
    // this is used to update the data object 
    Object [] [] resetList(){return null; }
    
    /**
     * set the statistics of the user
     * @param uname user name 
     * @param o user statics array
     * @throws StatisticsNotFoundException
     */
    void setStatisticsOfUser(String uname,Object [] o) throws StatisticsNotFoundException{
    	data[getRowIdxOfUname(uname)] = o ;
    }
    
    /**
     * returns the objects sets of this username 
     * @param uname 
     * @return
     * @throws StatisticsNotFoundException 
     */
    Object [] getStatisticsOfUname(String uname ) throws StatisticsNotFoundException{
    	int row = getRowIdxOfUname(uname) ;
    	return data[row];
    }
    /**
     * returns the idx of the row of which the user name is located 
     **/
    int getRowIdxOfUname(String uname) throws StatisticsNotFoundException{
    	int row = 0 ;
    	boolean found = false ;
    	for (Object dRow[] : data ) {		//access a row of data
    		if ( ((String) dRow[1]).equals(uname) ) { //if this row contains this specific user's info
    			found = true;
    			break ;
    		}
    		row ++ ;
    	}
    	if ( !found ) throw new StatisticsNotFoundException("cannot find username.");
    	else return row;
    }
    
    /*
	/	should not be called directly use the methods above to alter each element of the table
	 * instead
	/
     */
    public void setValueAt(Object value, int row, int col) {    	
        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col
                               + " to " + value
                               + " (an instance of "
                               + value.getClass() + ")");
            }
 
            data[row][col] = value;
 
            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }
 
        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();
 
            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
        }
    }
 
    /**
 * Create the GUI and show it.  For thread safety,
 * this method should be invoked from the
 * event-dispatching thread.
 */
private static void createAndShowGUI() {
    //Create and set up the window.
    JFrame frame = new JFrame("TableSortDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
    PlayerStatistics newContentPane = new PlayerStatistics();
    newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}