import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
//import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class okno {

	private JFrame frame;
	private JTextField txG_2;
	private JTextField txtVzlVes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		   //Установка Java Look and Feel (по умолчанию)
		   /*try {
		      UIManager.setLookAndFeel(
		    		  UIManager.getSystemLookAndFeelClassName());
		   }
		   catch (Exception e) { }*/
		   //остальная часть программы
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					okno window = new okno();
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
	public okno() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(okno.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		frame.setResizable(false);
		frame.setTitle("ВПХ Як-40");
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.setBounds(100, 100, 604, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 25, 590, 380);
		frame.getContentPane().add(tabbedPane);
		
		JPanel pFuel = new JPanel();
		tabbedPane.addTab("Топливо и время", null, pFuel, null);
		pFuel.setLayout(null);
		
		JLabel lbS = new JLabel("S=");
		lbS.setFont(new Font("Arial", Font.BOLD, 12));
		lbS.setBounds(10, 11, 46, 14);
		pFuel.add(lbS);
		
		JLabel lbSalt = new JLabel("Salt=");
		lbSalt.setFont(new Font("Arial", Font.BOLD, 12));
		lbSalt.setBounds(10, 46, 46, 14);
		pFuel.add(lbSalt);
		
		final JSpinner spinS = new JSpinner();
		spinS.setFont(new Font("Tahoma", Font.BOLD, 11));
		spinS.setModel(new SpinnerNumberModel(50, 50, 1500, 50));
		spinS.setBounds(77, 8, 62, 23);
		pFuel.add(spinS);
		
		final JSpinner spinSalt = new JSpinner();
		spinSalt.setFont(new Font("Tahoma", Font.BOLD, 11));
		spinSalt.setModel(new SpinnerNumberModel(50, 50, 1000, 10));
		spinSalt.setBounds(77, 43, 62, 23);
		pFuel.add(spinSalt);
		
		JLabel lblG = new JLabel("Gтоп=");
		lblG.setFont(new Font("Arial", Font.BOLD, 12));
		lblG.setBounds(168, 11, 46, 14);
		pFuel.add(lblG);
		
		JLabel lblT = new JLabel("Время=");
		lblT.setFont(new Font("Arial", Font.BOLD, 12));
		lblT.setBounds(168, 46, 46, 14);
		pFuel.add(lblT);
		
		final JTextPane txG = new JTextPane();
		txG.setFont(new Font("Arial", Font.BOLD, 12));
		txG.setBounds(224, 11, 56, 20);
		pFuel.add(txG);
		
		final JTextPane txT = new JTextPane();
		txT.setFont(new Font("Arial", Font.BOLD, 12));
		txT.setBounds(224, 46, 56, 20);
		pFuel.add(txT);
		
		JButton btR = new JButton("Расчитать");
		btR.setIcon(null);
		btR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int S = (int) spinS.getValue();
				int Salt = (int) spinSalt.getValue();
				float Tpsum = ((float)S + (float)Salt)/420; //Время полета для общего топлива
				float Tp = (float)S/420; //Время полета до ап
				int Hhh = (int)Tp;
				int Mmm = (int)((Tp- Hhh)*60);
				int Gf = (int) (((Tpsum*1200)*1.03)+600);
				txT.setText("0"+(String.valueOf(Hhh))+":"+(String.valueOf(Mmm)));
				txG.setText(String.valueOf(Gf));
				txG_2.setText(String.valueOf(Gf));
			}
		});
		btR.setBounds(10, 87, 97, 23);
		pFuel.add(btR);
		
		JLabel lblKg = new JLabel("кг");
		lblKg.setFont(new Font("Arial", Font.BOLD, 11));
		lblKg.setBounds(286, 12, 19, 14);
		pFuel.add(lblKg);
		
		JPanel pVes = new JPanel();
		tabbedPane.addTab("Вес", null, pVes, null);
		pVes.setLayout(null);
		
		JLabel lblG_2 = new JLabel("Топливо на полет=");
		lblG_2.setFont(new Font("Arial", Font.BOLD, 12));
		lblG_2.setBounds(10, 11, 120, 14);
		pVes.add(lblG_2);
		
		txG_2 = new JTextField();
		txG_2.setFont(new Font("Arial", Font.BOLD, 12));
		txG_2.setBounds(159, 9, 86, 20);
		pVes.add(txG_2);
		txG_2.setColumns(10);
		
		JLabel lbEk = new JLabel("Экипаж=");
		lbEk.setFont(new Font("Arial", Font.BOLD, 12));
		lbEk.setBounds(10, 48, 120, 14);
		pVes.add(lbEk);
		
		JLabel lbPr = new JLabel("Б/пр=");
		lbPr.setFont(new Font("Arial", Font.BOLD, 12));
		lbPr.setBounds(10, 85, 120, 14);
		pVes.add(lbPr);
		
		JLabel lbTeh = new JLabel("Техники=");
		lbTeh.setFont(new Font("Arial", Font.BOLD, 12));
		lbTeh.setBounds(10, 129, 120, 14);
		pVes.add(lbTeh);
		
		JLabel lbPass = new JLabel("Загрузка=");
		lbPass.setFont(new Font("Arial", Font.BOLD, 12));
		lbPass.setBounds(10, 171, 120, 14);
		pVes.add(lbPass);
		
		JLabel lbGvzl = new JLabel("Взлётный вес=");
		lbGvzl.setFont(new Font("Arial", Font.BOLD, 12));
		lbGvzl.setBounds(10, 216, 103, 14);
		pVes.add(lbGvzl);
		
		txtVzlVes = new JTextField();
		txtVzlVes.setFont(new Font("Arial", Font.BOLD, 12));
		txtVzlVes.setBounds(159, 214, 86, 20);
		pVes.add(txtVzlVes);
		txtVzlVes.setColumns(10);
				
		final JSpinner spinEk = new JSpinner();
		spinEk.setFont(new Font("Arial", Font.BOLD, 12));
		spinEk.setModel(new SpinnerNumberModel(3, 3, 5, 1));
		spinEk.setBounds(159, 46, 86, 20);
		pVes.add(spinEk);
		
		final JSpinner spinBpr = new JSpinner();
		spinBpr.setFont(new Font("Arial", Font.BOLD, 12));
		spinBpr.setModel(new SpinnerNumberModel(1, 0, 2, 1));
		spinBpr.setBounds(159, 83, 86, 20);
		pVes.add(spinBpr);
		
		final JSpinner spinTeh = new JSpinner();
		spinTeh.setFont(new Font("Arial", Font.BOLD, 12));
		spinTeh.setModel(new SpinnerNumberModel(0, 0, 2, 1));
		spinTeh.setBounds(159, 127, 86, 20);
		pVes.add(spinTeh);
		
		final JSpinner spinZagr = new JSpinner();
		spinZagr.setFont(new Font("Arial", Font.BOLD, 12));
		spinZagr.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinZagr.setBounds(159, 169, 86, 20);
		pVes.add(spinZagr);
		
		JButton btnRasVes = new JButton("Расчитать вес");
		btnRasVes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    int Gpust = 11245;//Масса пустого
			    int Gsl = 80;//Служебный груз
			    int Gfull = Integer.parseInt(txG_2.getText());
			    int vEk = 80;
			    int vPr = 75;
			    int vTeh = 75;
			    int vPass = 80;
			    int Ek = (int) spinEk.getValue();//Экипаж
			    int Pr = (int) spinBpr.getValue();//Проводники
			    int Teh = (int) spinTeh.getValue();//Техники
			    int Pass = (int) spinZagr.getValue();
			    int Gvzl =Gpust+Gsl+Gfull+(Ek*vEk)+(Pr*vPr)+(Teh*vTeh)+(Pass*vPass)-65;
			    txtVzlVes.setText(String.valueOf(Gvzl));	   
			}
		});
		btnRasVes.setFont(new Font("Arial", Font.BOLD, 12));
		btnRasVes.setBounds(10, 258, 120, 23);
		pVes.add(btnRasVes);
		
		JPanel pSpeed = new JPanel();
		tabbedPane.addTab("Скорости", null, pSpeed, null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 108, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu menuFile = new JMenu("Файл");
		menuBar.add(menuFile);
		
		JMenuItem menuItemOpen = new JMenuItem("Открыть");
		menuFile.add(menuItemOpen);
		
		JMenuItem menuItemExit = new JMenuItem("Выход");
		//выход
		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Да", "Нет!" };
				int n = JOptionPane
						.showOptionDialog(frame, "Закрыть окно?",
								"Подтверждение", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (n == 0) {
					frame.setVisible(false);
					System.exit(0);
			}
		
			}
			
		});
		menuFile.add(menuItemExit);
		
		JMenu menuHelp = new JMenu("Помощь");
		menuBar.add(menuHelp);
		//О программе
		JMenuItem menuItemAbout = new JMenuItem("О программе");
		menuItemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame, "Расчёт ВПХ Як-40 RA-88306\n ООО АК СКОЛ", "О программе", JOptionPane.PLAIN_MESSAGE);
			}
		});
		menuHelp.add(menuItemAbout);
	}
}
