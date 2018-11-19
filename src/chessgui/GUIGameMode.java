package chessgui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GUIGameMode extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIGameMode frame = new GUIGameMode();
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
	public GUIGameMode() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 851, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooseWhicGame = new JLabel("Choose Which Game Mode To Play");
		lblChooseWhicGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseWhicGame.setFont(new Font("Papyrus", Font.BOLD, 50));
		lblChooseWhicGame.setBounds(6, 0, 835, 92);
		contentPane.add(lblChooseWhicGame);
		
		JButton btnClasic = new JButton("Clasic");
		btnClasic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Table table = new Table();
			}
		});
		btnClasic.setFont(new Font("Papyrus", Font.BOLD, 16));
		btnClasic.setBounds(43, 104, 117, 41);
		contentPane.add(btnClasic);
		
		JButton btnDinasty = new JButton("Dynasty");
		btnDinasty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				board.setBoardImhotep();
			}
		});
		btnDinasty.setFont(new Font("Papyrus", Font.BOLD, 16));
		btnDinasty.setBounds(43, 157, 117, 41);
		contentPane.add(btnDinasty);
		
		JButton btnImhotep = new JButton("Imhotep");
		btnImhotep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				board.setBoardDynasty();
			}
		});
		btnImhotep.setFont(new Font("Papyrus", Font.BOLD, 16));
		btnImhotep.setBounds(43, 210, 117, 41);
		contentPane.add(btnImhotep);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("/Users/dannyacosta/eclipse-workspace/DataComunications/ChessGUI/images/SelectAGameScreen.png"));
		label.setBounds(0, 0, 850, 500);
		contentPane.add(label);
	}
}
