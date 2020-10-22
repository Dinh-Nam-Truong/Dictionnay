import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Scrollbar;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URLEncoder;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionListener;

import com.darkprograms.speech.recognizer.Languages;
import com.darkprograms.speech.translator.GoogleTranslate;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.darkprograms.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.AbstractListModel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JTextPane;


public class DictionaryApplication extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nhapTu;
	
	DefaultListModel listWord;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DictionaryApplication frame = new DictionaryApplication();
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
	
	DictionaryCommandline DC = new DictionaryCommandline();
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	public DictionaryApplication() throws IOException {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 585);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(127, 255, 212));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setViewportView(list);
		
		
		DC.insertFromFile();
		listWord = new DefaultListModel<String>();
		for(Word i: DC.getArr_Word()) {
			listWord.addElement(i.getWord_target());
		}
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(492, 301, 2, 2);
		contentPane.add(scrollPane);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(45, 251, 226, 268);
		contentPane.add(scrollPane_1);
		
		JTextPane txt_nghiaTu = new JTextPane();
		txt_nghiaTu.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		JList list = new JList();
		list.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//System.out.println(list.getSelectedValue().toString());
				String searchWord = (String) list.getSelectedValue();
				String content = DC.dictionaryLookup(searchWord);
				txt_nghiaTu.setText(content);
			}
		});
		scrollPane_1.setViewportView(list);
		list.setModel(listWord);
		
		txt_nhapTu = new JTextField();
		txt_nhapTu.setBackground(Color.WHITE);
		txt_nhapTu.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt_nhapTu.addKeyListener(new KeyAdapter() {
			@Override
			
			public void keyReleased(KeyEvent e) {
			
				//DC.dictionaryExportToFile();
				String search = txt_nhapTu.getText();
				var filterList = new DefaultListModel<String>();
				filterList.removeAllElements();
				if(search != "")
				{
					
					for(Word i:DC.dictionarySearcher(search).arr_Word)
					{
							
						filterList.addElement(i.getWord_target());
							
					}
					if(filterList.size() != 0)
					{
						list.setModel(filterList);
						list.setSelectedIndex(0);
					}
					else 
					{
						list.setSelectedIndex(-1);
						txt_nghiaTu.setText("Not Found");
					};
				}
				else
				{
					list.setModel(listWord);
				}
				
				
			}
		});
		txt_nhapTu.setBounds(168, 160, 389, 31);
		contentPane.add(txt_nhapTu);
		txt_nhapTu.setColumns(10);
		
		JButton btnNewButton = new JButton("T\u00ECm Ki\u1EBFm");
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String apinhap = txt_nhapTu.getText();
					//System.out.print(apinhap);
					String text = GoogleTranslate.translate("en", "vi", apinhap);
					txt_nghiaTu.setText(text);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(568, 157, 123, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Danh s\u00E1ch t\u1EEB");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(45, 219, 123, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u00DD ngh\u0129a");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(333, 220, 123, 19);
		contentPane.add(lblNewLabel_1);
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(333, 251, 414, 268);
		contentPane.add(scrollPane_2);
		
		scrollPane_2.setViewportView(txt_nghiaTu);
		JButton btnNewButton_1 = new JButton("Menu");
		btnNewButton_1.setBackground(new Color(224, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu dialog = new Menu(listWord, DC);
				dialog.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(45, 160, 85, 31);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Speak");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBackground(new Color(224, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
		        if(voice!=null){
		            voice.allocate();
		            voice.speak(txt_nhapTu.getText());
		        }
			}
		});
		btnNewButton_2.setBounds(718, 160, 85, 31);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("T\u1EEA \u0110I\u1EC2N ANH-VI\u1EC6T");
		lblNewLabel_2.setBackground(Color.RED);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2.setBounds(118, 40, 548, 86);
		contentPane.add(lblNewLabel_2);
		
	}
}
