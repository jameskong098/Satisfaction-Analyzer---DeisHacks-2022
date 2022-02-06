/**
 * James Kong
 * jameskong098@gmail.com
 * February 6, 2022
 * SatisFaction Analyzer: DeisHacks 2022
 * Implements a GUI with all necessary frames, panels, buttons, appropriate output, and etc.
 * Known Bugs: FileNotFoundException
 */

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;

public class GUI implements ActionListener {
	
	private JFrame frame;
	private JButton uploadButton;
	private JLabel fileInfo;
	private JLabel mag;
	private JLabel posCount;
	private JLabel posToNeg;
	private JLabel posPerc;
	private JLabel negCount;
	private JLabel negToPos;
	private JLabel negPerc;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel neutral;
	private JLabel verdict;
	private JLabel picture;
	private ImageIcon image;
	private JButton clearButton;
	private boolean checkClear;
	private JLabel totalWords;
	
	public GUI() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("DeisHacks - Satisfaction Analyzer");
		frame.getContentPane().setLayout(null);
		frame.setBounds(0,0,585,617);
		
		uploadButton = new JButton("Upload");
		uploadButton.setBounds(197, 513, 76, 25);
		uploadButton.setBackground(SystemColor.controlShadow);
		uploadButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		uploadButton.addActionListener(this);
		
		clearButton = new JButton("Clear");
		clearButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		clearButton.setBackground(SystemColor.controlShadow);
		clearButton.setBounds(302, 513, 76, 25);
		clearButton.addActionListener(this);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		uploadButton.setVerticalAlignment(SwingConstants.TOP);
		
		panel = new JPanel();
		panel.setBounds(105, 208, 375, 283);
		panel.setBackground(SystemColor.activeCaption);
		panel.setLayout(null);
		
		image = new ImageIcon(getClass().getClassLoader().getResource("clippership.png"));
		picture = new JLabel(image);
		mag = new JLabel("Magnitude Number:  (no results yet, upload a file!)");
		posCount = new JLabel("Positive Words/Phrases Count:  (no results yet, upload a file!)");
		posToNeg = new JLabel("Positive to Negative Ratio:  (no results yet, upload a file!)");
		posPerc = new JLabel("Positive % of Total Words:  (no results yet, upload a file)");
		negCount = new JLabel("Negative Words/Phrases Count:  (no results yet, upload a file!)");
		negToPos = new JLabel("Negative to Positive Ratio:  (no results yet, upload a file!)");
		negPerc = new JLabel("Negative % of Total Words:  (no results yet, upload a file!)");
		neutral = new JLabel("Neutral words/phrases:  (no results yet, upload a file!)");
		verdict = new JLabel("Verdict:  (no results yet, upload a file!)");
		verdict.setVerticalAlignment(SwingConstants.TOP);
		
		fileInfo = new JLabel("(Only .txt files are accepted at the moment)");
		
		panel_1.setBounds(0, 0, 576, 606);
		frame.getContentPane().add(panel_1);
		panel_1.add(uploadButton);
		panel_1.add(panel);
		panel.setLayout(null);
		panel_1.add(panel);
		mag.setBounds(10, 11, 326, 14);
		panel.add(mag);
		posCount.setBounds(10, 42, 370, 14);
		panel.add(posCount);
		posToNeg.setBounds(10, 68, 370, 14);
		panel.add(posToNeg);
		posPerc.setBounds(10, 94, 354, 14);
		panel.add(posPerc);
		negToPos.setBounds(10, 156, 370, 14);
		panel.add(negToPos);
		negPerc.setBounds(10, 182, 370, 14);
		panel.add(negPerc);
		neutral.setBounds(10, 219, 370, 14);
		panel.add(neutral);
		verdict.setBounds(10, 261, 353, 22);
		panel.add(verdict);
		negCount.setBounds(10, 130, 370, 14);
		panel.add(negCount);
		
		totalWords = new JLabel("Total Number of Words/Phrases:  (no results yet, upload a file!)");
		totalWords.setBounds(10, 245, 370, 14);
		panel.add(totalWords);
		fileInfo.setBounds(164, 550, 253, 14);
		panel_1.add(fileInfo);
		
		picture.setBounds(78, 12, 414, 184);
		panel_1.add(picture);
		
		panel_1.add(clearButton);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		checkClear = false;
	}
	
	public void startFrame() {
		frame.setVisible(true);
	}
	
	public String getFileType(File file) {
		String temp = file.getName();
		String type = "";
		int dotExt = temp.lastIndexOf('.');
		for (int i=dotExt+1; i < temp.length(); i++) {
			type += temp.charAt(i);
		}
		return type;
	}
	
	public static String takeFile(File text_txt) {
		  //this method should take the text file that contains the interview text
		String parseFile = "";
		try {
			Scanner input = new Scanner(text_txt);
			while (input.hasNextLine()) {
				parseFile += input.nextLine().trim().toLowerCase() + "\n";
			}
			input.close();
			int length = parseFile.length();
			parseFile = parseFile.substring(0, length - 1);
			return parseFile;
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return parseFile;
		}
	}
		  
	public static String[] parseWordsList(File file) {
		String temp = takeFile(file);
		String[] tempList = temp.split("\n"); 
		return tempList;
	}	
	
	public void checkWords(File file, String[] posWords, String[] negWords, CalculateMagnitude calc, String[] negations) {
		  String temp = takeFile(file);
		  String[] interList = temp.split("\\s");
		  ArrayList<String> noSpace = new ArrayList<String>();
		  for (int i=0; i < interList.length; i++) {
			  if (interList[i].equals("") == false && interList[i].equals(" ") == false) {
				  noSpace.add(interList[i]);
			  }
		  }
		  int positiveCount = 0;
		  int negativeCount = 0;
		  boolean foundWord = false;
		  for (int i=0; i < noSpace.size(); i++) {
			  String currentWord = noSpace.get(i);
			  for (int j=0; j < posWords.length; j++) {
				  if (currentWord.contains(posWords[j])) {
					  if (i - 1 >= 0) {
						  String prevWord = noSpace.get(i - 1);
						  for (int l=0; l < negations.length; l++) {
							  if (prevWord.contains(negations[l])) {
								  negativeCount += 1;
								  foundWord = true;
								  break;
							  }
						  }
					  }
					  if (foundWord == false) {
						  positiveCount += 1;
						  break;
					  }
				  }
			  }
			  if (foundWord != true) {
				  for (int k=0; k < negWords.length; k++) {
					  if (currentWord.contains(negWords[k])) {
						  if (i - 1 >= 0) {
							  String prevWord = noSpace.get(i - 1);
							  for (int l=0; l < negations.length; l++) {
								  if (prevWord.contains(negations[l])) {
									  positiveCount += 1;
									  foundWord = true;
									  break;
								  }
							  }
						  }
						  if (foundWord == false) {
							  negativeCount += 1;
							  break;
						  }
					  }
				  }
			  }
			  foundWord = false;
		  }
		  
		  int totalPosNeg = positiveCount + negativeCount;
		  int neutralWords = noSpace.size() - totalPosNeg;
		  int posRatio = (int) Math.round(((double) positiveCount) / totalPosNeg * 100);
		  int negRatio = (int) Math.round(((double) negativeCount) / totalPosNeg * 100);
		  int posTotalPerc = (int) Math.round(((double) positiveCount) / noSpace.size() * 100);
		  int negTotalPerc = (int) Math.round(((double) negativeCount) / noSpace.size() * 100);
		  
		  mag.setText("Magnitude Number: " + calc.getImpact());
		  posCount.setText("Positive Words/Phrases Count: " + positiveCount);
		  posToNeg.setText("Positive to Negative Ratio: " + posRatio + "%");
		  posPerc.setText("Positive % of Total Words: " + posTotalPerc + "%");
		  negCount.setText("Negative Words/Phrases Count: " + negativeCount);
		  negToPos.setText("Negative to Positive Ratio: " + negRatio + "%");
		  negPerc.setText("Negative % of Total Words: " + negTotalPerc + "%");
		  neutral.setText("Neutral words/phrases: " + neutralWords);
		  totalWords.setText("Total words/phrases: " + noSpace.size());
		  
		  if (posRatio > 60) {
			  verdict.setText("Verdict: Satisfied, positive word/phrase ratio past 60%");
		  }
		  else {
			  verdict.setText("Verdict: Not satisfied, positive word/phrase ratio less than 60%");
		  }
	}
	  
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == uploadButton) {
			JFileChooser fileChooser = new JFileChooser();
			int response = fileChooser.showOpenDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				File textFile = new File(fileChooser.getSelectedFile().getAbsolutePath()); 
				if (getFileType(textFile).equals("txt") == false) {
					JOptionPane.showMessageDialog(null, "Unsupported file type: Please upload a text file (.txt)!");
				}
				else {
					//try {
						//URL url = new URL(getClass().getClassLoader().getResource("positiveWords.txt"), null);
						//File wordFile = new File(url.getFile());
						File wordFile = new File("positiveWords.txt");
						String[] posWords = parseWordsList(wordFile);
						//url = new URL(getClass().getClassLoader().getResource("negativeWords.txt"), null);
						//wordFile = new File(url.getFile());
						wordFile = new File("negativeWords.txt");
						String[] negWords = parseWordsList(wordFile);
						CalculateMagnitude calc = new CalculateMagnitude(textFile);
						String[] negations = calc.getNegations();
						checkWords(textFile, posWords, negWords, calc, negations);
						checkClear = true;
					//} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
					//	e1.printStackTrace();
					//}
				}
			}
			
		}
		else if (e.getSource() == clearButton) {
			if (checkClear == true) {
				mag.setText("Magnitude Number:  (no results yet, upload a file!)");
				posCount.setText("Positive Words/Phrases Count:  (no results yet, upload a file!)");
				posToNeg.setText("Positive to Negative Ratio:  (no results yet, upload a file!)");
				posPerc.setText("Positive % of Total Words:  (no results yet, upload a file)");
				negCount.setText("Negative Words/Phrases Count:  (no results yet, upload a file!)");
				negToPos.setText("Negative to Positive Ratio:  (no results yet, upload a file!)");
				negPerc.setText("Negative % of Total Words:  (no results yet, upload a file!)");
				neutral.setText("Neutral words/phrases:  (no results yet, upload a file!)");
				totalWords.setText("Total words/phrases:  (no results yet, upload a file");
				verdict.setText("Verdict:  (no results yet, upload a file!)");
				checkClear = false;
			}
			else {
				JOptionPane.showMessageDialog(null, "No data to clear, upload a file!");
			}
		}
	}
}