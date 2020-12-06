import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;

class Main {
public static void main(String args[]) {
  SwingUtilities.invokeLater(new Runnable() {
    public void run() {
       new Game();
    }
  });
 }
}