class Question {
  private String qText;
  private String a1Text;
  private String a2Text;
  private String a3Text;
  private String a4Text;
  private int correct;
  private int value;

  public Question(String aQText, String anA1Text, String anA2Text,  String anA3Text,  String anA4Text, int aCorrect, int aValue){
    qText = aQText;
    a1Text = anA1Text;
    a2Text = anA2Text;
    a3Text = anA3Text;
    a4Text = anA4Text;
    correct = aCorrect;
    value = aValue;
  }
  public String getQText(){
    return qText;
  }
  public String getA1Text(){
    return a1Text;
  }
  public String getA2Text(){
    return a2Text;
  }
  public String getA3Text(){
    return a3Text;
  }
  public String getA4Text(){
    return a4Text;
  }
  public int getCorrect(){
    return correct;
  }
  public int getValue(){
    return value;
  }
  }
