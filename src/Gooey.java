import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class Gooey {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextArea size;
	private JPasswordField seedField;
	private JTextField log;
	private String entry;
	private JFileChooser fc;
	private String targetdata;
	private BufferedImage one;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gooey window = new Gooey();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Gooey() {
		initialize();
	}
	public static String ByteCount(long bytes) {
	    int unit = 1000;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = String.valueOf(("kMGTPE").charAt(exp-1));
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
	
	public void extractCat(String pw, String sd){
		if(one!=null){
			int[] pixels = new int[one.getWidth()*one.getHeight()];
			int[] dat = new int[(Integer.parseInt(sd)+1)*3];
			one.getRGB(0, 0,one.getWidth(),one.getHeight(), pixels, 0, one.getWidth());
			for(int i=0; i<(Integer.parseInt(sd)+1)*3; i+=3){
				String b = "0000000" + Integer.toBinaryString(pixels[i] & 0xff);
				String g = "0000000" + Integer.toBinaryString((pixels[i] & 0xff00) >> 8);
				String r = "0000000" + Integer.toBinaryString((pixels[i] & 0xff0000) >> 16);
				
			}
		}
	}
	private void initialize() {
		entry = "";
		frame = new JFrame();
		frame.setBounds(100, 100, 475, 210);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel encryptionOptions = new JPanel();
		encryptionOptions.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "Decryption Options", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		JPanel fileOptions = new JPanel();
		fileOptions.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "File Options", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		JPanel runOptions = new JPanel();
		runOptions.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "Run Options", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		this.log = new JTextField();
		fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
	
		seedField = new JPasswordField();
		JButton save = new JButton("Save!");		
		passwordField = new JPasswordField();		
		JButton extract = new JButton("Extract!");		
		extract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) { 
            	extractCat(passwordField.getText(),seedField.getText());
            }});	
		
		JButton open = new JButton("Open Host Image ");
		open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) { 
            	if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            	    File selectedFile = fc.getSelectedFile();
            	    try {
            	        one = ImageIO.read(selectedFile);
            	    } catch (IOException e) {
            	    }
            	    targetdata = selectedFile.getAbsolutePath();
            	    long bytes = selectedFile.length();
            	    size.setText(ByteCount(bytes));
            }}});
		
	
		this.log.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(encryptionOptions, 0, 0, Short.MAX_VALUE)
						.addComponent(fileOptions, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(log)
						.addComponent(runOptions, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
					.addGap(111))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(fileOptions, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(runOptions, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(encryptionOptions, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(log, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		runOptions.setLayout(null);		
		JLabel password = new JLabel("Password:");		
		save.setBounds(105, 21, 85, 25);
		runOptions.add(save);
		extract.setBounds(10, 22, 85, 23);
		runOptions.add(extract);
		password.setHorizontalAlignment(SwingConstants.LEFT);		
		passwordField.setColumns(10);		
		JLabel lblConfirm = new JLabel("Seed:");		
		GroupLayout gl_encryptionOptions = new GroupLayout(encryptionOptions);
		gl_encryptionOptions.setHorizontalGroup(
			gl_encryptionOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_encryptionOptions.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_encryptionOptions.createParallelGroup(Alignment.LEADING)
						.addComponent(lblConfirm)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_encryptionOptions.createParallelGroup(Alignment.LEADING, false)
						.addComponent(seedField)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_encryptionOptions.setVerticalGroup(
			gl_encryptionOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_encryptionOptions.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_encryptionOptions.createParallelGroup(Alignment.BASELINE)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_encryptionOptions.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirm, GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
						.addComponent(seedField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53))
		);
		encryptionOptions.setLayout(gl_encryptionOptions);				
		size = new JTextArea();
		GroupLayout gl_fileOptions = new GroupLayout(fileOptions);
		gl_fileOptions.setHorizontalGroup(
			gl_fileOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fileOptions.createSequentialGroup()
					.addGap(2)
					.addComponent(open, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(size, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_fileOptions.setVerticalGroup(
			gl_fileOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fileOptions.createSequentialGroup()
					.addGroup(gl_fileOptions.createParallelGroup(Alignment.BASELINE)
						.addComponent(size, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(open))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		fileOptions.setLayout(gl_fileOptions);
		frame.getContentPane().setLayout(groupLayout);
	}
}
