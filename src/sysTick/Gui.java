package sysTick;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.Box;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.Choice;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextPane;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class Gui extends JFrame {

	private JPanel contentPane;
	private CortexM0_SysTick CortexSysTick = new CortexM0_SysTick();
	private Galka galka = new Galka();
	private JTable table;
	private Generator generator = new Generator();
	private int currentImpulse,impulses;
	JCheckBox chckbxTickint = new JCheckBox("Tickint");
	JCheckBox chckbxEnabled = new JCheckBox("Enabled");
	JComboBox comboBoxSource = new JComboBox();
	JCheckBox chckbxBurst = new JCheckBox("Burst");
	JSlider slider = new JSlider();
	JSpinner spinnerBurst = new JSpinner();
	JButton btnStart = new JButton("Start");
	JButton btnNewButton_2 = new JButton("Tick External");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
					frame.setTitle("Cortex M0 SysTick");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void updateTable() {
		table.setValueAt(CortexSysTick.getRVR(), 0, 1);
		table.setValueAt(CortexSysTick.getCVR(), 1, 1);
		
		table.setValueAt(slider.getValue(), 3, 1);
		if ((String)comboBoxSource.getSelectedItem()=="Internal") 
			table.setValueAt("Internal", 5, 1);
		else
			table.setValueAt("External", 5, 1);
		table.setValueAt(chckbxEnabled.isSelected(), 6, 1);
		table.setValueAt(chckbxTickint.isSelected(), 7, 1);
		table.setValueAt(CortexSysTick.isCountFlag(), 8, 1);
		table.setValueAt(CortexSysTick.isInterrupt(), 9, 1);
		table.setValueAt(chckbxBurst.isSelected(), 10, 1);
		table.setValueAt(spinnerBurst.getValue(), 11, 1);

	}
	/**
	 * Create the frame.
	 */

	public Gui() {
        

		generator.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		table = new JTable();
		JSpinner spinnerRVR = new JSpinner();
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton setRVR = new JButton("Ustaw RVR");
		panel_4.add(setRVR);
		setRVR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int val=(int) spinnerRVR.getValue();
				CortexSysTick.setRVR(val);
				updateTable();
			}
		});

		panel_4.add(spinnerRVR);
		
		JButton btnNewButton_1 = new JButton("Ustaw CVR");
		panel_4.add(btnNewButton_1);
		JSpinner spinnerCVR = new JSpinner();
		panel_4.add(spinnerCVR);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int val=(int) spinnerCVR.getValue();
				CortexSysTick.setCVR(val);
				table.setValueAt("false", 8, 1);
				updateTable();
			}
		});
		
		JPanel panel_11 = new JPanel();
		panel.add(panel_11);
		
		JPanel panel_12 = new JPanel();
		panel.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton = new JButton("Aktualizuj CSR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setValueAt(CortexSysTick.getCSR(), 2, 1);
				
			}
		});
		panel_12.add(btnNewButton);
		
		JButton btnNewButton_3_1_2 = new JButton("Wyjœcie");
		panel.add(btnNewButton_3_1_2);
		btnNewButton_3_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_5.add(panel_9);
		
		
		panel_9.add(chckbxEnabled);
		chckbxEnabled.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chckbxEnabled.isSelected())
					CortexSysTick.setEnable();
				else
					CortexSysTick.setDisable();
				updateTable();
			}
		});
		
		
		panel_9.add(chckbxTickint);
		chckbxTickint.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chckbxTickint.isSelected())
					CortexSysTick.setInterruptEnable();
				else
					CortexSysTick.setInterruptDisable();
				updateTable();
			}
		});
		
		
		JButton btnNewButton_4 = new JButton("Reset");
		panel_5.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CortexSysTick.reset();
				chckbxEnabled.setSelected(false);
				chckbxTickint.setSelected(false);
				updateTable();
			}
		});
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		
		JLabel lblNewLabel = new JLabel("Source");
		panel_6.add(lblNewLabel);
		
		
		comboBoxSource.setModel(new DefaultComboBoxModel(new String[] {"External", "Internal"}));
		comboBoxSource.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if ((String)comboBoxSource.getSelectedItem()=="Internal") {
					CortexSysTick.setSource(true);
					btnStart.setEnabled(true);
					btnNewButton_2.setEnabled(false);
					updateTable();
				}
				else {
					generator.halt();
					btnStart.setEnabled(false);
					btnNewButton_2.setEnabled(true);
					CortexSysTick.setSource(false);
					updateTable();
				}	
			}
		});
		panel_6.add(comboBoxSource);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CortexSysTick.tickExternal();
				updateTable();
			}
		});
		
		
		panel_6.add(btnNewButton_2);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 3, 0, 0));
		
		JLabel delayLabel = new JLabel("Delay: 0",JLabel.CENTER);
		panel_7.add(delayLabel);
		
		
		slider.setValue(0);
		slider.setMaximum(1000);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
		       generator.setPulseDelay(slider.getValue());
		       delayLabel.setText("Delay[ms]: " + slider.getValue());
		       updateTable();
		       
			}
		});
		panel_7.add(slider);
		
		
		chckbxBurst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxBurst.isEnabled())
					generator.setMode((byte) 1);
				else
					generator.setMode((byte) 0);
				updateTable();				
			}
		});
		chckbxBurst.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(chckbxBurst);
		
		
		spinnerBurst.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerBurst.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				updateTable();
			}
		});
		panel_7.add(spinnerBurst);
		
		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8);
		panel_8.setLayout(new GridLayout(2, 0, 0, 0));
		btnStart.setEnabled(false);
		
		
		panel_8.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generator.trigger();
				currentImpulse=0;
			}
		});
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generator.halt();
			}
		});
		panel_8.add(btnStop);
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"RVR", new Integer(0)},
				{"CVR", new Integer(0)},
				{"CSR", new Integer(0)},
				{"Delay[ms]: ", new Integer(0)},
				{"Impulses", new Integer(0)},
				{"Source", "External"},
				{"Enable", "false"},
				{"Tickint", "false"},
				{"Countflag", "false"},
				{"Interrupt", "false"},
				{"Burst", "false"},
				{"BurstValue", new Integer(0)},
			},
			new String[] {
				"New column", "New column"
			}
		));
		panel_2.add(table, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_3.add(panel_10, BorderLayout.NORTH);
		panel_10.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Ilosc impulsow: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(lblNewLabel_1);
		
		JSpinner spinnerImpulse = new JSpinner();
		spinnerImpulse.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				table.setValueAt(spinnerImpulse.getValue(), 4, 1);
			}
		});
		spinnerImpulse.setModel(new SpinnerNumberModel(0, 0, 359, 1));
		panel_10.add(spinnerImpulse);
		galka.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				spinnerImpulse.setValue(galka.res);
				impulses=galka.res;
				updateTable();
			}
		});

		panel_3.add(galka, BorderLayout.CENTER);
		
        generator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int mode = generator.getMode();
                switch (mode) {
                    case 0:
                        if (currentImpulse >= impulses) 
                        	generator.halt();
                        CortexSysTick.tick();
                        if(CortexSysTick.isInterrupt())
                        	{
                        	updateTable();
                        	generator.halt();
                        	JOptionPane.showConfirmDialog(contentPane, "There was an interrupt","Interrupt detection",JOptionPane.DEFAULT_OPTION);
                        	CortexSysTick.setInterruptDisable();
                        	chckbxTickint.setSelected(false);
                        	generator.trigger();
                        	}
                        updateTable();
                        //table.setValueAt(CortexSysTick.getCVR(), 1, 1);
                        currentImpulse++;
                        break;
                    case 1:
                        if (currentImpulse >= (int) spinnerBurst.getValue() - 1) generator.halt();
                        if(CortexSysTick.isInterrupt())
                    	{
                    	updateTable();
                    	generator.halt();
                    	JOptionPane.showConfirmDialog(contentPane, "There was an interrupt","Interrupt detection",JOptionPane.DEFAULT_OPTION);
                    	CortexSysTick.setInterruptDisable();
                    	chckbxTickint.setSelected(false);
                    	generator.trigger();
                    	}
                        CortexSysTick.tick();
                        updateTable();
                        currentImpulse++;
                        break;
                    default:
                        break;
                }
            }
        });
		
}}