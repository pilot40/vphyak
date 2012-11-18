import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;


public class Okno {

	private JFrame frame;
	private JTextField txG_2;
	private JTextField txtVzlVes;
	//private Options options = null;
	//public int Gpust;

	/**
	 * <b>Запуск приложения<b>.
	 */
	public static void main(String[] args) {
		/**
		 * Установка Java Look and Feel (по умолчанию).
		 */		  
		   try {
		      //UIManager.setLookAndFeel(
		    		 // UIManager.getSystemLookAndFeelClassName());
			   //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		   }
		   catch (Exception e) { }
			/**
			 * остальная часть программы.
			 */	
			Options options = new Options();
			options.setVisible(true);
		// получаем домашний каталог пользователя
	        String homeDir = System.getProperty("user.home");

	        // они объявлены как final, так что к ним можно получить доступ 

	        // во внутреннем анонимном классе ниже
	        final String settingsFilename =
	        homeDir + File.separator + "mySettings.properties";
	        final Properties props = new Properties();

	        // Загрузка сохраненных настроек
	        try {
	            FileInputStream input = new FileInputStream(settingsFilename);
	            props.load(input);
	            input.close();
	        } catch(Exception ignore) {
	            // исключение игнорируется, поскольку ожидалось, что
	       // файл установочных параметров иногда может не существовать
	            // при первом запуске приложения он точно не будет существовать
	        }
		   
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Okno window = new Okno();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Создание приложения.
	 */
	public Okno() {
		initialize();
	}

	/**
	 * frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Okno.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		frame.setResizable(false);
		frame.setTitle("ВПХ Як-40");
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.setBounds(100, 100, 724, 501);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 25, 720, 448);
		frame.getContentPane().add(tabbedPane);
		
		JPanel pFuel = new JPanel();
		tabbedPane.addTab("Топливо и время", null, pFuel, null);
		tabbedPane.setBackgroundAt(0, Color.ORANGE);
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
				/**
				 * Расчет потребного топлива
				 */
				int S = (int) spinS.getValue();
				/**
				*Расстояние до аэропорта назначения
				*/
				int Salt = (int) spinSalt.getValue();
				/**
				*Расстояние до запасного аэропорта
				**/
				float Tpsum = ((float)S + (float)Salt)/420;
				/**
				* Время полета для общего топлива
				* */
				float Tp = (float)S/420; 
				/**
				*Время полета до аэропорта назначения
				**/
				int Hhh = (int)Tp;/**
				*Часы
				**/
				int Mmm = (int)((Tp- Hhh)*60);/**
				* Минуты
				* */
				int Gf = (int) (((Tpsum*1200)*1.03)+600);/**@see Топливо на полёт*/
				txT.setText("0"+(String.valueOf(Hhh))+":"+(String.valueOf(Mmm)));/**@see Вывод времени полёта*/
				txG.setText(String.valueOf(Gf));/**@see Вывод общей заправки*/
				txG_2.setText(String.valueOf(Gf));/**@see Вывод общей заправки*/
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
		tabbedPane.setBackgroundAt(1, Color.ORANGE);
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
		lbGvzl.setBounds(10, 258, 103, 14);
		pVes.add(lbGvzl);
		
		txtVzlVes = new JTextField();
		txtVzlVes.setFont(new Font("Arial", Font.BOLD, 12));
		txtVzlVes.setBounds(159, 256, 86, 20);
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
		spinZagr.setModel(new SpinnerNumberModel(0, 0, 32, 1));
		spinZagr.setBounds(159, 169, 86, 20);
		pVes.add(spinZagr);
		
		JLabel lbBag = new JLabel("Багаж=");
		lbBag.setFont(new Font("Arial", Font.BOLD, 12));
		lbBag.setBounds(10, 217, 120, 14);
		pVes.add(lbBag);
		
		final JSpinner spinBag = new JSpinner();
		spinBag.setModel(new SpinnerNumberModel(0, 0, 3200, 10));
		spinBag.setFont(new Font("Arial", Font.BOLD, 12));
		spinBag.setBounds(159, 215, 86, 20);
		pVes.add(spinBag);
		
		JButton btnRasVes = new JButton("Расчитать вес");
		btnRasVes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int Gpust = Options.getVesPust();				
			    int Gsl = 80;/**Служебный груз*/
			    int Gfull = Integer.parseInt(txG_2.getText());/**Заправка топливом*/
			    int vEk = 80;/**Вес члена экипажа*/
			    int vPr = 75;/**Вес бортпроводника*/
			    int vTeh = 75;/**Вес техника*/
			    int vPass = 80;/**Вес пассажира*/
			    int Ek = (int) spinEk.getValue();/**Число членов экипажа*/
			    int Pr = (int) spinBpr.getValue();/**Число бортпроводников*/
			    int Teh = (int) spinTeh.getValue();/**Число техников*/
			    int Pass = (int) spinZagr.getValue();/**Число пассажиров*/
			    int vBag = (int) spinBag.getValue();/**Вес багажа*/
			    int Gvzl =Gpust+Gsl+Gfull+vBag+(Ek*vEk)+(Pr*vPr)+(Teh*vTeh)+(Pass*vPass)-65;
			    txtVzlVes.setText(String.valueOf(Gvzl));/**Вычисление взлётного веса*/	   
			}
		});
		btnRasVes.setFont(new Font("Arial", Font.BOLD, 12));
		btnRasVes.setBounds(10, 300, 120, 23);
		pVes.add(btnRasVes);
		

		
		JPanel pSpeed = new JPanel();/**Вкладка графика скоростей*/
		pSpeed.setBackground(Color.WHITE);
		tabbedPane.addTab("Скорости", null, pSpeed, null);
		pSpeed.setLayout(null);
		JLabel lblSpeed = new JLabel("");
		lblSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeed.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSpeed.setBackground(Color.WHITE);
		lblSpeed.setIcon(new ImageIcon("image/1.png"));/**Размещение картинки на метке*/
		lblSpeed.setBounds(0, 0, 715, 421);
		pSpeed.add(lblSpeed);
		tabbedPane.setBackgroundAt(2, Color.ORANGE);
		
		JPanel pStab = new JPanel();
		pStab.setBackground(Color.WHITE);
		tabbedPane.addTab("Стабилизатор", null, pStab, null);
		tabbedPane.setBackgroundAt(3, Color.ORANGE);
		pStab.setLayout(null);
		
		JLabel lblStab = new JLabel("");
		lblStab.setHorizontalAlignment(SwingConstants.CENTER);
		lblStab.setHorizontalTextPosition(SwingConstants.CENTER);
		lblStab.setBackground(Color.WHITE);
		lblStab.setIcon(new ImageIcon("image/3.png"));/**Размещение картинки на метке*/
		lblStab.setBounds(0, 0, 715, 421);
		pStab.add(lblStab);
		
		
	    JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 194, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu menuFile = new JMenu("Файл");
		menuBar.add(menuFile);
		
		JMenuItem menuItemOpen = new JMenuItem("Открыть");
		menuFile.add(menuItemOpen);
		
		JMenuItem menuItemExit = new JMenuItem("Выход");
		/**выход из программы с запросом*/
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
		
		JMenu menu = new JMenu("Настройки");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("Выбрать");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Options options = new Options();
				options.setVisible(true);
			}
			
		});
		menu.add(menuItem);
		
		JMenu menuHelp = new JMenu("Помощь");
		menuBar.add(menuHelp);
		/**О программе*/
		JMenuItem menuItemAbout = new JMenuItem("О программе");
		menuItemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame, "Расчёт ВПХ Як-40", "О программе", JOptionPane.PLAIN_MESSAGE);
			}
		});
		menuHelp.add(menuItemAbout);
	}
}

