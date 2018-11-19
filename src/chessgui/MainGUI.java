package chessgui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setBounds(100, 100, 849, 521);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnPlayGame = new JButton("Play Game");
		btnPlayGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				GUIGameMode gameMode = new GUIGameMode();
				gameMode.setVisible(true);
			}
		});
		btnPlayGame.setFont(new Font("Papyrus", Font.BOLD, 16));
		btnPlayGame.setBounds(11, 174, 117, 41);
		frame.getContentPane().add(btnPlayGame);
		
		JButton btnRules = new JButton("Rules");
		btnRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				frame.dispose();
//				GUIRules rules1 = new GUIRules();
//				rules1.setVisible(true);
			}
		});
		btnRules.setFont(new Font("Papyrus", Font.BOLD, 16));
		btnRules.setBounds(11, 227, 117, 41);
		frame.getContentPane().add(btnRules);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setFont(new Font("Papyrus", Font.BOLD, 16));
		btnQuit.setBounds(11, 280, 117, 41);
		frame.getContentPane().add(btnQuit);
		
		JLabel lblNewLabel = new JLabel("Choose Play Game To Start");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Papyrus", Font.BOLD, 50));
		lblNewLabel.setBounds(11, 0, 835, 92);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("/Users/dannyacosta/eclipse-workspace/DataComunications/ChessGUI/images/SelectAGameScreen.png"));
		lblNewLabel_1.setBounds(0, 0, 850, 500);
		frame.getContentPane().add(lblNewLabel_1);
	}

}
