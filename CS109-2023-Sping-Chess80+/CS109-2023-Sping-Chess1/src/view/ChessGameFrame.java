package view;

import controller.GameController;
import controller.User;
import model.Chessboard;

import model.PlayerColor;
import model.Step;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.prefs.Preferences;


/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;

    private final int ONE_CHESS_SIZE;

    private ChessboardComponent chessboardComponent;

    public ChessGameFrame(int width, int height) {
        setTitle("2023 CS109 Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;

//        ImageIcon bg=new ImageIcon("resources/IMG_2043.JPG");
//        setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
//        JLabel jLabel=new JLabel(bg);
//        jLabel.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
//        JPanel jPanel=(JPanel) getContentPane();
//        jPanel.setOpaque(false);
//        getLayeredPane().add(jLabel,Integer.MIN_VALUE);
////        setVisible(true);

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);


        addChessboard();//在这
        //addLabel();
        addRemakeButton();
        addUndoButton();
        addSaveButton();
        addLoadButton();
        addMuteButton();
//        JButton red=new JButton("RED");
//        JButton blue=new JButton("BLUE");
//        JFrame frame=new JFrame();
//        red.setLocation(150,50);
//        red.setFont(new Font("Rockwell", Font.BOLD, 20));
//        red.setSize(200,60);
//        blue.setLocation(150,120);
//        blue.setFont(new Font("Rockwell", Font.BOLD, 20));
//        blue.setSize(200,60);
//        frame.add(red);
//        frame.add(blue);
//        frame.setSize(500,500);
//        frame.setLocationRelativeTo(null);
//        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setLayout(null);
//        frame.setVisible(true);
//        JLabel label=new JLabel("Who will go first?");
//        label.setSize(200,60);
//        label.setFont(new Font("Rockwell", Font.BOLD, 20));
//        label.setLocation(150,80);
//        frame.add(label);
//        add(frame);

    }

    public ChessboardComponent getChessboardComponent() {
        return chessboardComponent;
    }

    public void setChessboardComponent(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() //该操作初始化了chessboardcomponent
    {
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE);//构造chessboardcomponent并设置了size
        chessboardComponent.setLocation(HEIGTH / 5, HEIGTH / 10);//没有找到这个函数？
        add(chessboardComponent);
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {
        JLabel statusLabel = new JLabel("");
        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addRemakeButton() {
        JButton button = new JButton("RE!");
        button.setLocation(HEIGTH, HEIGTH / 10 + 50);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.addActionListener((e) -> {
            chessboardComponent.getGameController().getTimer().setVisible(false);
            //把timer设置成
            chessboardComponent.clear(chessboardComponent.getGameController().getModel());
            GameController g1=new GameController(chessboardComponent,new Chessboard(),chessboardComponent.getGameController().getUserRed(),chessboardComponent.getGameController().getUserBlue());
            JOptionPane.showMessageDialog(this, "棋盘已重置！", "提示", JOptionPane.INFORMATION_MESSAGE);
        });


        add(button);
    }

    private void addUndoButton() {
        JButton button = new JButton("UNDO");
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.addActionListener((e) -> {
            chessboardComponent.getGameController().undo();
//            JOptionPane.showMessageDialog(this, "已撤销", "提示", JOptionPane.INFORMATION_MESSAGE);
        });
//        StringBuilder red;
//        StringBuilder blue;
//        red=new StringBuilder("RED:");
//        blue=new StringBuilder("BLUE:");
//        red.append(String.format("%s Score:%d\n",chessboardComponent.getGameController().getUserRed().getName(),chessboardComponent.getGameController().getUserRed().getScore()));
//        blue.append(String.format("%s Score:%d\n",chessboardComponent.getGameController().getUserBlue().getName(),chessboardComponent.getGameController().getUserBlue().getScore()));
//        String redOutput=red.toString();
//        String blueOutput=blue.toString();
//        JLabel redLabel=new JLabel(redOutput);
//        JLabel blueLabel=new JLabel(blueOutput);
//        add(redLabel);
//        add(blueLabel);
        add(button);
    }
    private void addSaveButton() {
        JButton button = new JButton("Save");
        button.setLocation(HEIGTH, HEIGTH / 10 + 190);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.addActionListener((e) -> {
            chessboardComponent.getGameController().save();
            JOptionPane.showMessageDialog(this, "保存", "提示", JOptionPane.INFORMATION_MESSAGE);
        });

        add(button);
    }


    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 260);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            List<Step> loadsteps=chessboardComponent.getGameController().loadGameFromFile(path);

            if(!Objects.equals(path.substring(path.length() - 4), ".txt")){
                JOptionPane.showMessageDialog(this,"文件类型错误！");
            }

            if(loadsteps.size()>0) {
                chessboardComponent.getGameController().getTimer().setVisible(false);
                chessboardComponent.clear(chessboardComponent.getGameController().getModel());
                chessboardComponent.setGameController(new GameController(chessboardComponent, loadsteps.get(loadsteps.size() - 1).getModel(),loadsteps.get(loadsteps.size() - 1).getUserRed(),loadsteps.get(loadsteps.size() - 1).getUserBlue()));

                //GameController g1 = new GameController(chessboardComponent, loadsteps.get(loadsteps.size() - 1).getModel(),loadsteps.get(loadsteps.size() - 1).getUserRed(),loadsteps.get(loadsteps.size() - 1).getUserBlue());

                chessboardComponent.getGameController().setTurns(loadsteps.size());
                chessboardComponent.getGameController().setSteps(loadsteps);

                chessboardComponent.getGameController().timer.setFont(new Font("Rockwell", Font.BOLD, 20));
                chessboardComponent.getGameController().timer.setSize(200,60);
                chessboardComponent.getGameController().timer.setLocation(1000,81+420);
                chessboardComponent.getGameController().CountDown();
                this.add(chessboardComponent.getGameController().timer);
                this.repaint();
            }
        });
    }
    private void addMuteButton()
    {
        JButton button = new JButton("Mute");
        button.setLocation(HEIGTH, HEIGTH / 10 + 330);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.addActionListener((e) -> {
            chessboardComponent.getGameController().getMusic().stop();
            JOptionPane.showMessageDialog(this, "已静音", "提示", JOptionPane.INFORMATION_MESSAGE);
        });

        add(button);

    }



}
