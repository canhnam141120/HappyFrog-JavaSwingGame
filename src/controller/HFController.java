package controller;

import keyboard.KeyController;
import gui.HFGui;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class HFController {

    private HFGui g;
    private KeyController key = new KeyController();
    private JButton btn1 = new JButton();
    private JButton btn2 = new JButton();
    private JButton btn3 = new JButton();
    private JButton btn4 = new JButton();
    private JButton btnF = new JButton();
    private Timer timer;
    private int point = 0;

    public HFController(HFGui g) {
        this.g = g;
        g.getBtnPause().addKeyListener(key);
        g.getBtnSave().addKeyListener(key);
        g.getBtnExit().addKeyListener(key);
//        g.addKeyListener(key);
        run();
    }

    private int x1 = 280;
    private int h1 = 120;
    private int x2 = 600;
    private int h2 = 120;
    private int h3 = 160;
    private int h4 = 160;
    private int yF = 200;

    public void addBtn() {
        ImageIcon icon = new ImageIcon("C:\\Users\\DELL\\Downloads\\NewCanvas1.png");
        btn1.setBounds(x1, 0, 40, h1); //Set kích thước cho Button 1
        btn2.setBounds(x2, 0, 40, h2);
        btn3.setBounds(x1, 400 - h3, 40, h3);
        btn4.setBounds(x2, 400 - h4, 40, h4);
        btnF.setBounds(100, yF, 40, 40);
        btnF.setIcon(icon);
        g.getjPanel1().add(btn1); //Đẩy button lên Panel
        g.getjPanel1().add(btn2);
        g.getjPanel1().add(btn3);
        g.getjPanel1().add(btn4);
        g.getjPanel1().add(btnF);

    }

    public void checkPoint() {  //Hàm tính điểm
        if (btnF.getX() == (btn1.getX() + 41) || btnF.getX() == (btn2.getX() + 41)) {
            point++;
            g.getLbPoint().setText(point + "");
        }
    }
    
    //CHECK CLICK VÀO BUTTON PAUSE
    //NEU STOP RỒI THÌ LẦN CLICK SAU PHẢI RESTART
    private boolean checkPause = false;
    public void pressPause() { //Ham pause game
        if (checkPause == false) { //TH nếu chưa nhấn nút thì sẽ có thể nhấn được để tạm dừng game
            timer.stop();
            checkPause = true;
        } else { //TH nút đang nhấn thì nhấn lần nữa để tiếp tục game
            timer.restart();
            checkPause = false;
        }
    }
    
    private boolean checkSave = false;
    private int x11 = 280;
    private int h11 = 120;
    private int x22 = 600;
    private int h22 = 120;
    private int h33 = 160;
    private int h44 = 160;
    private int yFF = 200;
    private int point1 = 0;
    
    public void pressSave(JButton save){ //Lưu thông tin khi ấn Button Save
        checkSave = true;
        x11 = x1;
        h11 = h1;
        x22 = x2;
        h22 = h2;
        h33 = h3;
        h44 = h4;
        yFF = yF;
        point1 = point;
    }

    public void showMes() { //Hàm hiện thị thông báo tương ứng
        Object mes[] = {"New game", "Exit"};
        int option = JOptionPane.showOptionDialog(null, "Oh", "No", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, mes, mes[0]);
        if (option == 0) {
            x1 = 280;
            h1 = 120;
            x2 = 600;
            h2 = 120;
            h3 = 160;
            h4 = 160;
            yF = 200;
            point = 0;
            g.getLbPoint().setText("0");
            timer.restart();
        } else {
            System.exit(0);
        }

    }
    
    public void showMesSaving(){ //Ham hien thi thông báo tương ứng
        Object mes[] = {"New game","Replay", "Exit"};
        int option = JOptionPane.showOptionDialog(null, "Oh", "No", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, mes, mes[0]);
        switch (option) {
            case 0: //TH New Game -> Rest lại cấc thông số như ban đầu
                x1 = 280;
                h1 = 120;
                x2 = 600;
                h2 = 120;
                h3 = 160;
                h4 = 160;
                yF = 200;
                point = 0;
                checkSave = false;
                g.getLbPoint().setText("0");
                timer.start();
                break;
            case 1: //TH nếu quay lại nơi Save
                x1 = x11;
                h1 = h11;
                x2 = x22;
                h2 = h22;
                h3 = h33;
                h4 = h44;
                yF = yFF;
                point = point1;
                checkSave = false;
                g.getLbPoint().setText(point1+"");
                timer.start();
                break;
            default:
                System.exit(0);
        }
    }

    public boolean checkLose() { //Hàm check chạm button để xét thua game 
        if (btnF.getY() <= 0 || (btnF.getY() + 40) >= 400) {
            return true;
        }
        //Chuyển tất cả button thành hình chữ nhật để dùng hàm check chạm intersects
        Rectangle btF = new Rectangle(btnF.getX(), btnF.getY(), btnF.getWidth(), btnF.getHeight());
        Rectangle bt1 = new Rectangle(btn1.getX(), btn1.getY(), btn1.getWidth(), btn1.getHeight());
        Rectangle bt2 = new Rectangle(btn2.getX(), btn2.getY(), btn2.getWidth(), btn2.getHeight());
        Rectangle bt3 = new Rectangle(btn3.getX(), btn3.getY(), btn3.getWidth(), btn3.getHeight());
        Rectangle bt4 = new Rectangle(btn4.getX(), btn4.getY(), btn4.getWidth(), btn4.getHeight());
        return btF.intersects(bt1) || btF.intersects(bt2) || btF.intersects(bt3) || btF.intersects(bt4);
    }
    
    public void pressExit(){ //Hàm chức năng tương ứng với nút Exit
        timer.stop();
        int option = JOptionPane.showConfirmDialog(null, "Do you want to exit  ?", "hi", JOptionPane.YES_NO_OPTION);;
        if(option == JOptionPane.NO_OPTION) {
            timer.restart();  
        }else System.exit(0);
    }

    public void run() {
        timer = new Timer(15, (ActionEvent e) -> {
            if (key.isPress()) { //THmỗi lần nhấn phím thì Button F sẽ dịch chuyển lên phía trên
                yF -= 20;
                key.setPress(false);
            }
            addBtn();
            x1--; //Các cột button dịch chuyển sang trái so vs vị trí ban đầu
            x2--;
            yF++; //Button Frog dịch chuyển xuống dưới theo thời gian so với vị trí ban đầu
            if (x1 == -40) {  //Sau khi cột button chạy đến hết Panel thì một cột khác xuất hiện random thông số
                x1 = 650;
                h1 = new Random().nextInt(200) + 40;
                h3 = 400 - 120 - h1;
            }
            if (x2 == -40) {
                x2 = 650;
                h2 = new Random().nextInt(200) + 40;
                h4 = 400 - 120 - h2;
            }
            
            checkPoint();
            
            if (checkLose()) { //Nếu chạm thì stop lại thread
                if(checkSave == false){
                    timer.stop();
                    showMes();
                }
                else{
                    timer.stop();
                    showMesSaving();
                }
            }
        });
        timer.restart();
    }

}
