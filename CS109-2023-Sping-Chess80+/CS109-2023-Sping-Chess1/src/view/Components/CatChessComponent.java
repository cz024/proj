package view.Components;


import model.PlayerColor;
import view.ChessComponent;

import javax.swing.*;
import java.awt.*;

/**
 * This is the equivalent of the ChessPiece class,
 * but this class only cares how to draw Chess on ChessboardComponent
 */
public class CatChessComponent extends ChessComponent {
    //private PlayerColor owner;

    private boolean selected;
//    private ImageIcon gifIcon;
//    private boolean showGif;

    public CatChessComponent(PlayerColor owner, int size) {
        super(owner,size);
        this.owner=owner;
        setSize(size/2, size/2);
        setLocation(0,0);
        setVisible(true);

//        showGif=false;//初态是看不到的
//        this.gifIcon = new ImageIcon("path_to_your_gif_file.gif");
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
//    public void setShowGif(boolean flag)
//    {
//        this.showGif=flag;
//    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("楷体", Font.PLAIN, getWidth() / 2);
        g2.setFont(font);
        g2.setColor(owner.getColor());
        g2.drawString("猫", getWidth() / 4, getHeight() * 5 / 8); // FIXME: Use library to find the correct offset.
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
//        if (showGif) {
//            g.drawImage(gifIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
//        }
    }
}