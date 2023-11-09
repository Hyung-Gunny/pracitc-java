package chap09_Homework2_22112204_박형건;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

class JPanel_BouncingBall extends JPanel implements ActionListener {
    Timer timer = new Timer(5, this);
    int pos_x = 0, pos_y = 0;
    int dx = 1, dy = 1;
    int ball_diameter;
    int fr_width, fr_height;
    int fr_x_margin, fr_y_margin;
    int border_thickness;
    double ballSpeed = 1.0;
    public Color ball_color = Color.RED;

    JPanel_BouncingBall(int ball_diameter, int fr_width, int fr_height, int x_margin, int y_margin, int border_thickness) {
        this.ball_diameter = ball_diameter;
        this.fr_width = fr_width;
        this.fr_height = fr_height;
        this.fr_x_margin = x_margin;
        this.fr_y_margin = y_margin;
        this.border_thickness = border_thickness;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(ball_color);
        g.fillOval(pos_x, pos_y, ball_diameter, ball_diameter);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (pos_x < 0 || pos_x > (fr_width - ball_diameter - fr_x_margin)) {
            dx = -dx;
        }
        if (pos_y < 0 || pos_y > (fr_height - ball_diameter - fr_y_margin)) {
            dy = -dy;
        }
        pos_x += (int) (dx * ballSpeed);
        pos_y += (int) (dy * ballSpeed);
        System.out.println("Ball speed(" + ballSpeed + "), position(" + pos_x + ", " + pos_y + ")");
        repaint();
    }
}
