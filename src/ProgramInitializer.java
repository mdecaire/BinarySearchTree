
/**
 * Gui with main
 *  this class is the front end of the program with a driver it
 * builds a gui, checks the format of the values, and tokenizes the string. Creates new fractions
 * and builds both types of tree
 * 
 * @author Michelle Decaire cmsc 350 project three 12/3/2017
 *
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ProgramInitializer extends JFrame {

	// all variable for the gui panel
	private static final long serialVersionUID = 1L;
	private final static int WID = 450;
	private final static int HEI = 350;
	private JButton performSort = new JButton("Perform Sort");
	private JTextField userList = new JTextField("", 30);
	private JLabel userListLabel = new JLabel("Original List");
	private JTextField sortedList = new JTextField("", 30);
	private JLabel sortedListLab = new JLabel("Sorted List");

	private ButtonGroup sortOrder = new ButtonGroup();
	private JRadioButton ascending = new JRadioButton("Ascending", true);
	private JRadioButton descending = new JRadioButton("Descending");

	private ButtonGroup numericType = new ButtonGroup();
	private JRadioButton integerBtn = new JRadioButton("Integer", true);
	private JRadioButton fractionBtn = new JRadioButton("Fraction");

	/**
	 * initializes the GUI
	 */
	public ProgramInitializer() {
		super("Binary Search Tree Sort");
		setSize(WID, HEI);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * builds the GUI and listens for action events
	 */
	public void display() {
		sortedList.setEditable(false);
		sortOrder.add(ascending);
		sortOrder.add(descending);
		numericType.add(integerBtn);
		numericType.add(fractionBtn);

		// event handlers for the radio buttons
		ascending.addActionListener((new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ascendingActionPerformed(evt);
			}

			private void ascendingActionPerformed(ActionEvent evt) {
				ascending.setSelected(true);
			}
		}));

		descending.addActionListener((new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				descendingActionPerformed(evt);
			}

			private void descendingActionPerformed(ActionEvent evt) {
				descending.setSelected(true);
			}
		}));

		integerBtn.addActionListener((new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				integerActionPerformed(evt);
			}

			private void integerActionPerformed(ActionEvent evt) {
				integerBtn.setSelected(true);
			}
		}));

		fractionBtn.addActionListener((new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fractionActionPerformed(evt);
			}

			private void fractionActionPerformed(ActionEvent evt) {
				fractionBtn.setSelected(true);
			}

		}));

		// layout information for panels and frame
		JPanel rPanel1 = new JPanel();
		rPanel1.setLayout(new GridLayout(2, 1));
		rPanel1.add(ascending);
		rPanel1.add(descending);
		rPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Sort Order"));

		JPanel rPanel2 = new JPanel();
		rPanel2.setLayout(new GridLayout(2, 1));
		rPanel2.add(integerBtn);
		rPanel2.add(fractionBtn);
		rPanel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Numeric Type"));

		JPanel orList = new JPanel();
		orList.add(userListLabel, BorderLayout.WEST);
		orList.add(userList, BorderLayout.EAST);
		orList.setBorder(BorderFactory.createEmptyBorder(25, 15, 20, 5));

		JPanel sorList = new JPanel();
		sorList.add(sortedListLab, BorderLayout.WEST);
		sorList.add(sortedList, BorderLayout.EAST);
		sorList.setBorder(BorderFactory.createEmptyBorder(25, 15, 20, 0));

		JPanel actBtn = new JPanel();
		actBtn.add(performSort, BorderLayout.CENTER);

		JPanel rPanel = new JPanel(new GridLayout(1, 3));
		rPanel.add(rPanel1, BorderLayout.WEST);
		rPanel.add(rPanel2, BorderLayout.EAST);

		setLayout(new GridLayout(4, 1));
		add(orList);
		add(sorList);
		add(actBtn);
		add(rPanel);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * main driver method of the gui...event handler for sort button
		 */
		performSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sortOrder = "";
				String sortedOrder = "";
				String numType = "";
				BinarySearchTree<?> bst = null;
				try {
					if (userList.getText().isEmpty()) {
						throw new NumFormatException("Please enter a list of numbers!");
					}

					// checks the format of the list
					String userInput = userList.getText();
					String[] list = null;
					if (!checkFormat(userInput)) {
						throw new NumFormatException("Invalid character found in list");
					} else {
						list = tokenize(userInput);
					}

					if (ascending.isSelected()) {
						sortOrder = "ascending";
					} else if (descending.isSelected()) {
						sortOrder = "descending";
					}
					if (integerBtn.isSelected()) {
						numType = "integer";

					} else if (fractionBtn.isSelected()) {
						numType = "fraction";
					}
					sortedOrder = buildSearchTree(numType, sortOrder, bst, list);
					sortedList.setText(sortedOrder);
				} catch (NumFormatException numExc) {
					showMessage(numExc.mess);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			// tokenizes the original input
			private String[] tokenize(String list) {

				String[] tokens;
				list = list.trim();
				tokens = list.split("\\s+");
				return tokens;

			}

			/**
			 * checks the formatting
			 * 
			 * @param list
			 * @return
			 */
			public boolean checkFormat(String list) {
				char[] c = list.toCharArray();
				boolean correctFormat = true;
				try {
					for (char item : c) {

						if (!((Character.isDigit(item)) || (item == ' ') || (item == '/') || (item == '-'))) {
							correctFormat = false;
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return correctFormat;
			}

			/**
			 * This method determines what type of tree should be build and builds it
			 * returns a string value of the ordered list
			 * @throws NumberFormatException
			 */
			@SuppressWarnings("unchecked")
			public String buildSearchTree(String type, String order, BinarySearchTree bst, String[] tokens)
					throws NumberFormatException {
				String numType = type;
				String sortedList = null;
				if (numType.equalsIgnoreCase("integer")) {
					bst = new BinarySearchTree<Integer>();
				} else {
					bst = new BinarySearchTree<Fraction>();
				}

				try {

					for (String s : tokens) {
						if (numType.equalsIgnoreCase("integer")) {
							if (s.contains("/")) {
								throw new NumFormatException("Invalid integer input");
							}
							Integer i = Integer.parseInt(s);
							bst.insert(i);

						} else {
							Fraction numNode = new Fraction(s);
							bst.insert(numNode);
						}

					}
					if (order.equalsIgnoreCase("ascending")) {
						sortedList = bst.inOrdertreetraversal();
					} else if (order.equalsIgnoreCase("descending")) {
						sortedList = bst.descendingOrder();
					}

				} catch (IndexOutOfBoundsException ex) {
					ex.printStackTrace();

				}
				return sortedList;
			}

		});

	}

	/**
	 * error message for invalid input
	 * JOptionPane
	 * @param mess
	 */
	protected void showMessage(String mess) {
		JFrame frame = new JFrame("Syntax Error");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JOptionPane.showMessageDialog(frame, mess, "Syntax Error", JOptionPane.INFORMATION_MESSAGE);

	}

	public static void main(String[] args) {
		ProgramInitializer bSt = new ProgramInitializer();
		bSt.display();

	}

}
