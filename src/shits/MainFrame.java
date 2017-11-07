package shits;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.TransferHandler;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelOutput = new JPanel();
		panelOutput.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelOutput.setBounds(169, 0, 1005, 662);
		contentPane.add(panelOutput);
		PanelDropTarget panelDropTgt = new PanelDropTarget(panelOutput);
		panelOutput.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		panelOutput.add(scrollPane);

		
		JPanel panelComponents = new JPanel();
		panelComponents.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelComponents.setBounds(0, 0, 159, 662);
		contentPane.add(panelComponents);
		panelComponents.setLayout(null);
		
		
		DnDLabel lblComponents = new DnDLabel("New label");
		lblComponents.setBounds(56, 60, 46, 14);
		panelComponents.add(lblComponents);
		
		DnDButton btnComponents = new DnDButton("New button");
		btnComponents.setBounds(35, 104, 89, 23);
		panelComponents.add(btnComponents);
		
		DnDTextField txtComponents = new DnDTextField();
		txtComponents.setBounds(35, 170, 89, 52);
		panelComponents.add(txtComponents);
		
	}
}
