/**
@author Molly Campbell, Dom Dalessandro, Andrew Kaiser, Tom Sanker
@version 12-6-2020
*/
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;

public class Game implements ActionListener { //add action listener
  JTextField uName;     //declare jtextfield
  JButton butA1, butA2, butA3, butA4, butNext, butQuit; // declare those buttons
  JLabel jlabWelcome, jlabQuestion, jlabWorth, jlabScore, jlabCorrect; //jlabels = declared
  static ArrayList<Question> questions; //declare arraylist for questions
  static String filename = "trivia.txt"; 
  static FileReader myFile;
  int score = 0;
  int curQuestion = 0;
  String user = "";
  JButton[] buttons = new JButton[4]; //made a jbutton array to determine correct answer based on index
  
  public void getQ(int question){   //method to swap buttons' text for next question
    jlabQuestion.setText(questions.get(question).getQText());
    jlabWorth.setText("Value: " + Integer.toString(questions.get(question).getValue()));
    butA1.setText(questions.get(question).getA1Text());
    butA2.setText(questions.get(question).getA2Text());
    butA3.setText(questions.get(question).getA3Text());
    butA4.setText(questions.get(question).getA4Text());
  }
  //made methods to start and end game (ie. hide components and show)
  public void endGame(){
      jlabScore.setVisible(true);
      uName.setVisible(false);
      jlabQuestion.setVisible(false);
      jlabWorth.setVisible(false);
      butA1.setVisible(false);
      butA2.setVisible(false);
      butA3.setVisible(false);
      butA4.setVisible(false);
      butNext.setVisible(false);
      jlabCorrect.setVisible(false); 
      butQuit.setVisible(false);
  }
  public void startGame(){
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
      butQuit.setVisible(true);
  }
  Game(){ 
    
    JFrame frame = new JFrame("Group 1's Super Ultra Mega Wicked Trivia Game"); //new frame object
    frame.setLayout(new FlowLayout());
    frame.setSize(750, 500);

    uName = new JTextField(10); //new textField to get user name
    uName.setActionCommand("myTF");
    
    //Creates new buttons and formats the style
    butA1 = new JButton("A"); 
     butA1.setFont(new Font("Serif",Font.BOLD,15));
    butA2 = new JButton("B");
     butA2.setFont(new Font("Serif",Font.BOLD,15));
    butA3 = new JButton("C");
     butA3.setFont(new Font("Serif",Font.BOLD,15));
    butA4 = new JButton("D");
     butA4.setFont(new Font("Serif",Font.BOLD,15));
    butNext = new JButton("Next Question");
    butQuit = new JButton("Quit");

    JPanel buttonsPanel = new JPanel(new GridLayout(2,2));
    buttonsPanel.add(butA1);
    buttonsPanel.add(butA2);
    buttonsPanel.add(butA3);
    buttonsPanel.add(butA4);


    //assigning buttons to array indeces
    
    buttons[0] = butA1;
    buttons[1] = butA2;
    buttons[2] = butA3;
    buttons[3] = butA4;

    uName.addActionListener(this); //ACTION LISTENERS!
    butA1.addActionListener(this);
    butA2.addActionListener(this);
    butA3.addActionListener(this);
    butA4.addActionListener(this);
    butNext.addActionListener(this);
    butQuit.addActionListener(this);

    //Adding in all labels and formatting the necessary ones
    jlabWelcome = new JLabel("Welcome, Please enter your name and press Enter");
    jlabWelcome.setFont(new Font("SanSerif", Font.ITALIC, 15));
    jlabQuestion = new JLabel("");
    jlabWorth = new JLabel("");
    jlabScore = new JLabel("Score: " + score);
    jlabCorrect = new JLabel("");
    //add components to frame
    frame.add(jlabWelcome);
    frame.add(jlabScore);
    frame.add(uName);
    frame.add(jlabQuestion);
    frame.add(jlabWorth);
    frame.add(buttonsPanel);
    /*frame.add(butA1);
    frame.add(butA2);
    frame.add(butA3);
    frame.add(butA4); */
    frame.add(butNext);
    frame.add(jlabCorrect);
    frame.add(butQuit);
    
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
    butQuit.setVisible(false);
    buttonsPanel.setVisible(true);

    
    questions = new ArrayList<Question>(); //initialize arraylist object questions
    filename = "trivia.txt";
    String qText = "",  a1Text = "", a2Text = "", a3Text = "", a4Text = "", correct = "", value  = "";
    //try block for file reader
    try{
      myFile  = new FileReader(filename);
      BufferedReader reader = new BufferedReader(myFile); //new filereader and bufferedreader
      
      while(reader.ready()){  //buffered reader reading lines
        qText = reader.readLine();
        a1Text = reader.readLine();
        a2Text = reader.readLine();
        a3Text = reader.readLine();
        a4Text = reader.readLine();
        correct = reader.readLine();        
        value = reader.readLine();
        //new question object
      
        Question aQuestion = new Question(qText, a1Text, a2Text, a3Text, a4Text, Integer.parseInt(correct), Integer.parseInt(value));
        questions.add(aQuestion);
      } 
      reader.close(); 
    } catch (IOException exception){
      System.out.println("An error occured: " + exception);
    } catch (NumberFormatException numException){
      System.out.println("An error occured: "+ numException);
    }
  }
  public void actionPerformed(ActionEvent ae) {
    if(ae.getActionCommand().equals("myTF")){
      //welcome user with name from textfield
      user = uName.getText();
      jlabWelcome.setText("Welcome, " + user);
      //call method to get question
      getQ(curQuestion);
      //hide textbox and bring up questions/ answer choices
      startGame();
    }
    //get value of correct answer and store in a variable for comparison
    String correctAns = buttons[questions.get(curQuestion).getCorrect()-1].getText();

    //if else to check selected answer against correct answer
    if(!ae.getActionCommand().equals(correctAns)) {
      jlabCorrect.setText("That's not Correct, please click Next Question.");
      jlabCorrect.setVisible(true);
    }
    if(ae.getActionCommand().equals("Next Question")){
      curQuestion++;
      if(curQuestion >= questions.size()){
      jlabWelcome.setText("Thats it! Thanks for playing");
      jlabScore.setText("You Scored: " + score  + " points! Nice!");
      endGame();
      //try loop to append user's name and score to text file
      try {
        File scores = new File("scores.txt");
        if(!scores.exists()){
          scores.createNewFile();
        } 
        FileWriter toScores = new FileWriter(scores.getName(),true);
        BufferedWriter output = new BufferedWriter(toScores);
        output.write(user);
        output.newLine();
        output.write(String.valueOf(score));
        output.newLine();
        output.close();
      }
  catch(IOException exception){
    System.out.println("An error occured: " + exception);
  }     
  } else {
      getQ(curQuestion);
      jlabCorrect.setVisible(false);
      }
    }
    if(ae.getActionCommand().equals("myTF")){
    jlabCorrect.setText(""); 
    } 
    else if(ae.getActionCommand().equals(correctAns)){
      jlabCorrect.setText("You Got it!, please Click Next Question!");
      score = score + questions.get(curQuestion).getValue();
      jlabScore.setText("Score: "+ score);
      jlabCorrect.setVisible(true);
    } //quit functionality
    if(ae.getActionCommand().equals("Quit")){
    jlabWelcome.setText("You Quit! Thanks for playing, I guess.");
    jlabScore.setText("You Scored: " + score  + " points! Too bad it doesn't matter because you're a quitter...");
   //call end game method
    endGame();
    }

  }



}

