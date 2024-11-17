package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class AboutUsWindow extends JFrame {

    public AboutUsWindow(){
        Toolkit kit =Toolkit.getDefaultToolkit();
        Dimension screenSize =kit.getScreenSize();
        setSize(650,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("AboutUs");


        JPanel jPanel =new JPanel(new GridLayout(2,2,10,10));


        Color clr=new Color(100,100,150);  //boja za pozadinu
        getContentPane().setBackground(clr);


        ImageIcon imageIcon1=new ImageIcon(loadImage("/images/user.png"));
        Image ivaImage = imageIcon1.getImage();
        Image modIvaImage = ivaImage.getScaledInstance(270,270,Image.SCALE_SMOOTH);
        imageIcon1= new ImageIcon(modIvaImage);

        JLabel jlImage1=new JLabel(imageIcon1);

        ImageIcon imageIcon2=new ImageIcon(loadImage("/images/user.png"));
        Image nikolaImage2 = imageIcon2.getImage();
        Image modNikolaImage = nikolaImage2.getScaledInstance(270,270,Image.SCALE_SMOOTH);
        imageIcon2= new ImageIcon(modNikolaImage);

        JLabel jlImage2=new JLabel(imageIcon2);


        JLabel jLabelIva = new JLabel("Ime Prezime");
        JLabel jLabelNikola = new JLabel("Ime Prezime");
        //jLabelIva.set;
        jPanel.add(jlImage1);
        jPanel.add(jLabelIva);
        jPanel.add(jlImage2);
        jPanel.add(jLabelNikola);


        jPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(jPanel);
    }

    public Image loadImage(String fileName) {
        URL imageURL = getClass().getResource(fileName);
        Image image = null;

        if (imageURL != null) {
            image = new ImageIcon(imageURL).getImage();
        } else {
            System.out.println("Image not found at the path: " + fileName);
        }

        return image;
    }
}
