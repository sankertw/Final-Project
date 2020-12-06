import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;

public class Game implements ActionListener {
  JTextField uName;
  JButton butA1, butA2, butA3, butA4, butNext;
  JLabel jlabWelcome, jlabQuestion, jlabWorth, jlabScore, jlabCorrect;
  static ArrayList<Question> questions;
  static String filename = "trivia.txt";
  static FileReader myFile;
  int score = 0;
  int curQuestion = 0;
  JButton[] buttons = new JButton[4];
  
  public void getQ(int question){
    jlabQuestion.setText(questions.get(question).getQText());
    jlabWorth.setText("Value: " + Integer.toString(questions.get(question).getValue()));
    butA1.setText(questions.get(question).getA1Text());
    butA2.setText(questions.get(question).getA2Text());
    butA3.setText(questions.get(question).getA3Text());
    butA4.setText(questions.get(question).getA4Text());
  }
  Game(){ 
    
    JFrame frame = new JFrame("Group ?s Super Ultra Mega Wicked Trivia Game");
    frame.setLayout(new FlowLayout());
    frame.setSize(375, 300);

    uName = new JTextField(10);
    uName.setActionCommand("myTF");
    
    butA1 = new JButton("A");
    butA2 = new JButton("B");
    butA3 = new JButton("C");
    butA4 = new JButton("D");
    butNext = new JButton("Next Question");
    
    buttons[0] = butA1;
    buttons[1] = butA2;
    buttons[2] = butA3;
    buttons[3] = butA4;

    uName.addActionListener(this);
    butA1.addActionListener(this);
    butA2.addActionListener(this);
    butA3.addActionListener(this);
    butA4.addActionListener(this);
    butNext.addActionListener(this);


    jlabWelcome = new JLabel("Welcome, Please enter your name and press Enter");
    jlabQuestion = new JLabel("");
    jlabWorth = new JLabel("");
    jlabScore = new JLabel("Score: " + score);
    jlabCorrect = new JLabel("");

    frame.add(jlabWelcome);
    frame.add(jlabScore);
    frame.add(uName);
    frame.add(jlabQuestion);
    frame.add(butA1);
    frame.add(butA2);
    frame.add(butA3);
    frame.add(butA4);
    frame.add(butNext);
    frame.add(jlabWorth);
    frame.add(jlabCorrect);
    
    frame.setVisible(true);
    jlabQuestion.setVisible(false);
    jlabWorth.setVisible(false);
    jlabScore.setVisible(false);
    butA1.setVisible(false);
    butA2.setVisible(false);
    butA3.setVisible(false);
    butA4.setVisible(false);
    butNext.setVisible(false);
    jlabCorrect.setVisible(false);
    
    questions = new ArrayList<Question>();
    filename = "trivia.txt";
    String qText = "",  a1Text = "", a2Text = "", a3Text = "", a4Text = "", correct = "", value = "";
    
    try{
      myFile  = new FileReader(filename);
      BufferedReader reader = new BufferedReader(myFile);
      
      while(reader.ready()){
        qText = reader.readLine();
        a1Text = reader.readLine();
        a2Text = reader.readLine();
        a3Text = reader.readLine();
        a4Text = reader.readLine();
        correct = reader.readLine();
        value = reader.readLine();

        Question aQuestion = new Question(qText, a1Text, a2Text, a3Text, a4Text, Integer.parseInt(correct), Integer.parseInt(value));
        questions.add(aQuestion);
      } 
      reader.close(); 
    } catch (IOException exception){
      System.out.println("An error occured: " + exception);
    }

  }
  public void actionPerformed(ActionEvent ae) {
    if(ae.getActionCommand().equals("myTF")){
      String user = uName.getText();
      jlabWelcome.setText("Welcome, " + user);

      getQ(curQuestion);

      jlabScore.setVisible(true);
      uName.setVisible(false);
      jlabQuestion.setVisible(true);
      jlabWorth.setVisible(true);
      butA1.setVisible(true);
      butA2.setVisible(true);
      butA3.setVisible(true);
      butA4.setVisible(true);
      butNext.setVisible(true);
      jlabCorrect.setVisible(false);
    }

    String correctAns = buttons[questions.get(curQuestion).getCorrect()-1].getText();
    if(ae.getActionCommand().equals("Next Question")){
      curQuestion++;
      getQ(curQuestion);
      jlabCorrect.setText("");
    }
    if(ae.getActionCommand().equals(correctAns)){
      jlabCorrect.setText("You Got it!, please Click Next Question!");
      score = score + questions.get(curQuestion).getValue();
      jlabScore.setText("Score: "+ score);
      jlabCorrect.setVisible(true);
    }
    else if(ae.getActionCommand().equals("myTF")){
      jlabCorrect.setText(""); 
    } 
    else {
      jlabCorrect.setText("That's not Correct, please click Next Question.");
      jlabCorrect.setVisible(true);
    }    
}
}
