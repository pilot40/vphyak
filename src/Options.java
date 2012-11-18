//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;



public class Options extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField txtVesPust;
	//private int VesPust;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Options kkkk = new Options();
					kkkk.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Options() {
		setLocation(new Point(500, 500));
		setResizable(false);
		setEnabled(true);
		setAlwaysOnTop(true);
		setTitle("Настройки");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVesPust = new JLabel("Вес пустого самолёта:");
		lblVesPust.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVesPust.setBounds(10, 11, 89, 14);
		contentPane.add(lblVesPust);
		
		txtVesPust = new JTextField();
		txtVesPust.setHorizontalAlignment(SwingConstants.CENTER);
		txtVesPust.setText("9940");
		txtVesPust.setBounds(109, 9, 86, 20);
		contentPane.add(txtVesPust);
		txtVesPust.setColumns(10);
		
		JLabel lblKg = new JLabel("кг");
		lblKg.setFont(new Font("Arial", Font.BOLD, 12));
		lblKg.setBounds(205, 12, 17, 14);
		contentPane.add(lblKg);
		
		JButton btnNewButton = new JButton("Внести");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getVesPust();
				hide();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton.setBounds(10, 63, 89, 23);
		contentPane.add(btnNewButton);
	}
	/*
	public void setVub(int vub)
    {
      this.VesPust = vub;
    }
	*/
	public static int getVesPust()
    {
		int VesPust = Integer.parseInt(txtVesPust.getText());
        return VesPust;
    }
	
	
}
