/**
@author Molly Campbell, Dom Dalessandro, Andrew Kaiser, Tom Sanker
@version 12-6-2020
*/
class Question {
  private String qText;
  private String a1Text;
  private String a2Text;
  private String a3Text;
  private String a4Text;
  private int correct;
  private int value;
// Question constructor w/ params
  public Question(String aQText, String anA1Text, String anA2Text,  String anA3Text,  String anA4Text, int aCorrect, int aValue){
    qText = aQText;
    a1Text = anA1Text;
    a2Text = anA2Text;
    a3Text = anA3Text;
    a4Text = anA4Text;
    correct = aCorrect;
    value = aValue;
  } //question getter
  public String getQText(){
    return qText;
  } //option1 getter
  public String getA1Text(){
    return a1Text;
  }//option2 getter
  public String getA2Text(){
    return a2Text;
  }//option3 getter
  public String getA3Text(){
    return a3Text;
  }//option4 getter
  public String getA4Text(){
    return a4Text;
  }// correct answer getter
  public int getCorrect(){
    return correct;
  } //point value getter
  public int getValue(){
    return value;
  }
  }
