import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import java.awt.Font;
import java.awt.Color;

public class Menu extends JDialog {

	protected static Object dialog;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_themTu;
	private JTextField txt_nghiaTumoi;
	private JTextField txt_xoaTu;
	private JTextField txt_suaTu;
	private JTextField txt_Tuthaythe;
	private JTextField txt_nghiaThaythe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Menu dialog = new Menu(new DefaultListModel<String>(), new DictionaryCommandline());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	DictionaryCommandline DC = new DictionaryCommandline();
	DefaultListModel listWord;
	/**
	 * Create the dialog.
	 */
	public Menu(DefaultListModel listWord, DictionaryCommandline DC) {
		this.listWord = listWord;
		this.DC = DC;
		getContentPane().setBackground(new Color(240, 248, 255));
		setTitle("menu");
		setModal(true);
		setAutoRequestFocus(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 455, 587);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 441, 20);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel txt_chuthich = new JLabel("");
		txt_chuthich.setBounds(56, 468, 326, 31);
		getContentPane().add(txt_chuthich);
		getContentPane().add(contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 519, 441, 31);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						txt_nghiaThaythe.setText("");
						txt_nghiaTumoi.setText("");
						txt_suaTu.setText("");
						txt_xoaTu.setText("");
						txt_Tuthaythe.setText("");
						txt_themTu.setText("");
						txt_chuthich.setText("");
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
//		try {
//			//DC.insertFromFile();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		JLabel lblNewLabel = new JLabel("Th\u00EAm t\u1EEB");
		lblNewLabel.setBounds(33, 22, 108, 20);
		getContentPane().add(lblNewLabel);
		
		
		txt_themTu = new JTextField();
		txt_themTu.setBounds(221, 53, 161, 19);
		getContentPane().add(txt_themTu);
		txt_themTu.setColumns(10);
		
		txt_nghiaTumoi = new JTextField();
		txt_nghiaTumoi.setBounds(221, 87, 161, 19);
		getContentPane().add(txt_nghiaTumoi);
		txt_nghiaTumoi.setColumns(10);
		
		JButton btnNewButton = new JButton("Th\u00EAm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String themtu = txt_themTu.getText();
				String nghiatumoi = txt_nghiaTumoi.getText();
				Word nw = new Word(themtu,nghiatumoi);
				
				DC.them_word(nw);
				DC.dictionaryExportToFile();	
				listWord.addElement(themtu);
				txt_chuthich.setText(" THÀNH CÔNG . Nhấn OK để tiếp tục ");
				
			}
		});
		
		btnNewButton.setBounds(158, 121, 85, 21);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("X\u00F3a t\u1EEB");
		lblNewLabel_1.setBounds(33, 173, 55, 20);
		getContentPane().add(lblNewLabel_1);
		
		txt_xoaTu = new JTextField();
		txt_xoaTu.setBounds(221, 228, 161, 19);
		getContentPane().add(txt_xoaTu);
		txt_xoaTu.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("S\u1EEDa t\u1EEB");
		lblNewLabel_2.setBounds(33, 291, 45, 13);
		getContentPane().add(lblNewLabel_2);
		
		txt_suaTu = new JTextField();
		txt_suaTu.setBounds(221, 316, 161, 19);
		getContentPane().add(txt_suaTu);
		txt_suaTu.setColumns(10);
		
		txt_Tuthaythe = new JTextField();
		txt_Tuthaythe.setBounds(221, 345, 161, 19);
		getContentPane().add(txt_Tuthaythe);
		txt_Tuthaythe.setColumns(10);
		
		txt_nghiaThaythe = new JTextField();
		txt_nghiaThaythe.setBounds(221, 374, 161, 19);
		getContentPane().add(txt_nghiaThaythe);
		txt_nghiaThaythe.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("X\u00F3a");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String xoatu = txt_xoaTu.getText();
				DC.xoa_word(xoatu);
				DC.dictionaryExportToFile();
				listWord.removeElement(xoatu);
				txt_chuthich.setText(" THÀNH CÔNG . Nhấn OK để tiếp tục ");
			}
		});
		btnNewButton_1.setBounds(158, 262, 85, 21);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("S\u1EEDa");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tusua = txt_suaTu.getText();
				int index = listWord.indexOf(tusua);
				String tuthaythe = txt_Tuthaythe.getText();
				String nghiathaythe = txt_nghiaThaythe.getText();
				Word thaytu = new Word(tuthaythe,nghiathaythe);
				DC.sua_word(tusua, thaytu);
				DC.dictionaryExportToFile();
				listWord.setElementAt(tuthaythe, index);
				txt_chuthich.setText(" THÀNH CÔNG . Nhấn OK để tiếp tục ");
				
			}
		});
		btnNewButton_2.setBounds(158, 403, 85, 21);
		getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("T\u1EEB th\u00EAm m\u1EDBi");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(56, 52, 108, 20);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Ngh\u0129a th\u00EAm m\u1EDBi");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(56, 83, 108, 20);
		getContentPane().add(lblNewLabel_4);
		
		
		JLabel lblNewLabel_6 = new JLabel("X\u00F3a t\u1EEB");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(56, 229, 108, 16);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_5 = new JLabel("Từ được sửa");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(56, 318, 98, 13);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_7 = new JLabel("Từ thay thế");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(56, 348, 85, 13);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Nghĩa mới");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_8.setBounds(56, 377, 85, 13);
		getContentPane().add(lblNewLabel_8);
	}
	
}
