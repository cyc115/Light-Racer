package UI;

//TODO What is the difference between this and MapSelect.java. Please explain. 
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GameCore.Map;

public class MapFileChooser extends JFrame
{
   JFileChooser fc = new JFileChooser();

   public MapFileChooser(String title)
   {
      super(title);
      setDefaultCloseOperation(EXIT_ON_CLOSE);

      JPanel pnl = new JPanel();
      pnl.setLayout(new GridLayout(2, 1));

      JButton btn = new JButton("JFileChooser.showOpenDialog() Demo");
      ActionListener al;
      al = new ActionListener()
           {
              @Override
              public void actionPerformed(ActionEvent ae)
              {
                 switch (fc.showOpenDialog(MapFileChooser.this))
                 {
                    case JFileChooser.APPROVE_OPTION:
                       JOptionPane.showMessageDialog(MapFileChooser.this, "Selected: "+
                                                     fc.getSelectedFile(),
                                                     "MapFileChooser",
                                                     JOptionPane.OK_OPTION);
                       break;

                    case JFileChooser.CANCEL_OPTION:
                       JOptionPane.showMessageDialog(MapFileChooser.this, "Cancelled",
                                                     "MapFileChooser",
                                                     JOptionPane.OK_OPTION);
                       break;
                 
                    case JFileChooser.ERROR_OPTION:
                       JOptionPane.showMessageDialog(MapFileChooser.this, "Error",
                                                     "MapFileChooser",
                                                     JOptionPane.OK_OPTION);
                 }
              }
           };
      btn.addActionListener(al);
      pnl.add(btn);

      btn = new JButton("JFileChooser.showSaveDialog() Demo");
      al = new ActionListener()
           {
              @Override
              public void actionPerformed(ActionEvent ae)
              {
                 switch (fc.showSaveDialog(MapFileChooser.this))
                 {
                    case JFileChooser.APPROVE_OPTION:
                       JOptionPane.showMessageDialog(MapFileChooser.this, "Selected: "+
                                                     fc.getSelectedFile(),
                                                     "MapFileChooser",
                                                     JOptionPane.OK_OPTION);
                       break;

                    case JFileChooser.CANCEL_OPTION:
                       JOptionPane.showMessageDialog(MapFileChooser.this, "Cancelled",
                                                     "MapFileChooser",
                                                     JOptionPane.OK_OPTION);
                       break;
                 
                    case JFileChooser.ERROR_OPTION:
                       JOptionPane.showMessageDialog(MapFileChooser.this, "Error",
                                                     "MapFileChooser",
                                                     JOptionPane.OK_OPTION);
                 }
              }
           };
      btn.addActionListener(al);
      pnl.add(btn);

      setContentPane(pnl);

      pack();
      setVisible(true);
   }

   public static void main(String[] args)
   {
      Runnable r = new Runnable()
                   {
                      @Override
                      public void run()
                      {
                         new MapFileChooser("FileChooser Demo");
                      }
                   };
      EventQueue.invokeLater(r);
   }
}