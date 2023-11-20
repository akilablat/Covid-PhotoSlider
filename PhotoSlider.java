
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.Timer;

public class PhotoSlider extends JFrame implements ActionListener {
    JFrame frame;
    JPanel panel, p2, p3;
    CardLayout card, card2;
    JButton home, next, pre, last, play, list, info;
    JLabel title, t2, np, nv;
    JTextArea area;
    int g, id, id2, id3, z, i2;
    ArrayList<String> idx, infox, index, num;
    JScrollPane scr;

    PhotoSlider() {
        g = id = id2 = id3 = z = i2 = 0;
        idx = new ArrayList<String>();
        infox = new ArrayList<String>();
        index = new ArrayList<String>();
        num = new ArrayList<String>();

        for (int fx = 0; fx < 100; fx++) {
            num.add(Integer.toString(fx));
        }

        File dir = new File("images");
        File[] picture = dir.listFiles();

        try {
            Scanner scanner = new Scanner(new File("metadata.txt"));
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("\t");
                for (File child : picture) {
                    for (String data : line) {
                        if (data.equals(child.getName())) {
                            idx.add(line[1]);
                            index.add(line[0]);
                            infox.add(line[1]);
                            infox.add(line[2]);
                            infox.add(line[3]);
                            infox.add(line[4]);
                            infox.add(line[5]);
                            infox.add(line[6]);
                            infox.add(line[7]);
                            infox.add(line[8]);
                            infox.add(line[9]);
                            infox.add(line[10]);
                            infox.add(line[11]);
                            infox.add(line[12]);
                            infox.add(line[13]);
                            infox.add(line[14]);
                        }
                    }
                }
            }
            scanner.close();
        } catch (IOException x) {
            System.err.println(x);
        }

        home = new JButton(
                "<HTML><BODY> <P> <IMG SRC='https://cdn1.iconfinder.com/data/icons/pixel-perfect-at-16px-volume-1/16/5019-512.png' width='22' height='32' Home >  </P> </BODY>  </HTML>");
        home.setBounds(320, 650, 100, 30);
        home.setContentAreaFilled(false);
        home.setFocusable(false);
        home.addActionListener(this);

        next = new JButton(
                "<HTML><BODY> <P> <IMG SRC='https://image.flaticon.com/icons/png/512/130/130884.png' width='30' height='20' Next >  </P> </BODY>  </HTML>");
        next.setBounds(710, 650, 100, 30);
        next.setContentAreaFilled(false);
        next.setFocusable(false);
        next.addActionListener(this);

        pre = new JButton(
                "<HTML><BODY> <P> <IMG SRC='https://cdn0.iconfinder.com/data/icons/arrows-android-l-lollipop-icon-pack/24/previous-512.png' width='30' height='25' Previous >  </P> </BODY>  </HTML>");
        pre.setBounds(450, 650, 100, 30);
        pre.setContentAreaFilled(false);
        pre.setFocusable(false);
        pre.addActionListener(this);

        last = new JButton(
                "<HTML><BODY> <P> <IMG SRC='https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_skip_next_48px-512.png' width='30' height='40' Last >  </P> </BODY>  </HTML>");
        last.setBounds(840, 650, 100, 30);
        last.setContentAreaFilled(false);
        last.setFocusable(false);
        last.addActionListener(this);

        play = new JButton(
                "<HTML><BODY> <P> <IMG SRC='https://www.pngkey.com/png/full/503-5038265_png-file-svg-replay-icon.png' width='30' height='25' play > </P> </BODY> </HTML>");
        play.setBounds(580, 650, 100, 30);
        play.setContentAreaFilled(false);
        play.setFocusable(false);
        play.addActionListener(this);

        list = new JButton(
                "<HTML><BODY> <P> <IMG SRC='https://img.icons8.com/metro/452/list.png' width='25' height='25' list >  </P> </BODY>  </HTML>");
        list.setBounds(1150, 10, 100, 30);
        list.setContentAreaFilled(false);
        list.setFocusable(false);
        list.addActionListener(this);

        info = new JButton(
                "<HTML><BODY> <P> <IMG SRC='https://img.icons8.com/pastel-glyph/2x/info.png' width='28' height='28' info >  </P> </BODY>  </HTML>");
        info.setBounds(1150, 650, 100, 30);
        info.setContentAreaFilled(false);
        info.setFocusable(false);
        info.addActionListener(this);

        title = new JLabel("Image Gallery");
        title.setBounds(530, 30, 280, 50);
        title.setFont(new Font("Arial", Font.PLAIN, 25));
        title.setVisible(true);

        nv = new JLabel("Image Information");
        nv.setBounds(530, 30, 280, 50);
        nv.setFont(new Font("Arial", Font.PLAIN, 25));
        nv.setVisible(false);

        np = new JLabel("Playing");
        np.setBounds(10, 50, 100, 50);
        np.setForeground(Color.red);
        np.setFont(new Font("Arial", Font.PLAIN, 20));
        np.setVisible(false);

        t2 = new JLabel();
        t2.setBounds(530, 30, 280, 50);
        t2.setFont(new Font("Arial", Font.PLAIN, 25));
        t2.setText("Patient ID: " + idx.get(g));
        t2.setVisible(false);

        card = new CardLayout(220, 100);
        card2 = new CardLayout(250, 100);

        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(card);
        panel.setBounds(0, 0, 1280, 720);
        panel.setVisible(false);

        p2 = new JPanel();
        p2.setBackground(Color.white);
        p2.setLayout(card2);
        p2.setBounds(0, 0, 1280, 720);
        p2.setVisible(false);

        for (z = 0; z < infox.size(); z += 14) {
            area = new JTextArea();
            area.setEditable(false);
            area.setFont(new Font("Arial", Font.PLAIN, 20));
            String aa = (" Categories:             " + "Results:\n\n");
            String bb = (" Patient ID:             " + infox.get(z) + "\n\n");
            String cc = (" Offset:                 " + infox.get(z + 1) + "\n\n");
            String dd = (" Sex:                    " + infox.get(z + 2) + "\n\n");
            String ee = (" Age:                    " + infox.get(z + 3) + "\n\n");
            String ff = (" Finding:                " + infox.get(z + 4) + "\n\n");
            String gg = (" RT_PCR_positive:        " + infox.get(z + 5) + "\n\n");
            String hh = (" Survival:               " + infox.get(z + 6) + "\n\n");
            String ii = (" Intubated:              " + infox.get(z + 7) + "\n\n");
            String jj = (" Intubation:             " + infox.get(z + 8) + "\n\n");
            String oo = (" Went_ICU:               " + infox.get(z + 9) + "\n\n");
            String kk = (" In_ICU:                 " + infox.get(z + 10) + "\n\n");
            String ll = (" Needed_Supplemental_O2: " + infox.get(z + 11) + "\n\n");
            String mm = (" Date:                   " + infox.get(z + 12) + "\n\n");
            String nn = (" Location:               " + infox.get(z + 13) + "\n\n");
            area.setText(aa + bb + cc + dd + ee + ff + gg + hh + ii + jj + oo + kk + ll + mm + nn);
            JScrollPane scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            p2.add(index.get(i2), scroll);
            i2 = i2 + 1;
        }

        p3 = new JPanel();
        p3.setLayout(new GridLayout(0, 4, 10, 10));

        for (File child : picture) {
            JButton ao = new JButton();
            JLabel ai = new JLabel();
            try {
                Image image = ImageIO.read(child);
                ImageIcon imageIcon = new ImageIcon(image);
                Image ii = imageIcon.getImage();
                Image newimg = ii.getScaledInstance(200, 130, java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                ao.setPreferredSize(new Dimension(200, 160));
                ao.setVerticalAlignment(SwingConstants.TOP);
                ao.setIcon(new ImageIcon(newimg));
                ao.setContentAreaFilled(false);
                ao.setFocusable(false);
                ao.setMinimumSize(new Dimension(200, 160));
                ao.setVerticalTextPosition(SwingConstants.BOTTOM);
                ao.setHorizontalTextPosition(SwingConstants.CENTER);
                ao.setFont(new Font("Arial", Font.PLAIN, 15));
                ao.setText("Patient ID: " + idx.get(id));
                ao.setName(index.get(id));
                ai.setText(num.get(id));
                ao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent c) {
                        g = Integer.parseInt(ai.getText());
                        card.show(panel, ao.getName());
                        card2.show(p2, ao.getName());
                        title.setVisible(false);
                        t2.setText(ao.getText());
                        t2.setVisible(true);
                        scr.setVisible(false);
                        panel.setVisible(true);
                        home.setEnabled(true);
                        next.setEnabled(true);
                        pre.setEnabled(true);
                        last.setEnabled(true);
                        play.setEnabled(true);
                        list.setEnabled(true);
                        info.setEnabled(true);
                    }
                });
                p3.add(ao);
                id = id + 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (File child : picture) {
            JLabel ab = new JLabel();
            try {
                Image image = ImageIO.read(child);
                ImageIcon imageIcon = new ImageIcon(image);
                Image ii = imageIcon.getImage();
                Image newimg = ii.getScaledInstance(800, 565, java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                ab.setIcon(new ImageIcon(newimg));
                panel.add(index.get(id2), ab);
                id2 = id2 + 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        scr = new JScrollPane(p3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scr.setBounds(230, 100, 840, 500);
        scr.setVisible(true);

        frame = new JFrame("Covid Slider");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(title);
        frame.add(t2);
        frame.add(home);
        frame.add(next);
        frame.add(pre);
        frame.add(last);
        frame.add(play);
        frame.add(list);
        frame.add(nv);
        frame.add(info);
        frame.add(np);
        frame.add(panel);
        frame.add(p2);
        frame.add(scr);

        home.setEnabled(false);
        next.setEnabled(false);
        pre.setEnabled(false);
        last.setEnabled(false);
        play.setEnabled(false);
        list.setEnabled(false);
        info.setEnabled(false);

    }

    public static void main(String[] args) {
        PhotoSlider slider = new PhotoSlider();
    }

    Timer timer = new Timer(5000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent c) {
            card.next(panel);
            card2.next(p2);
            if (g <= idx.size() - 2) {
                g = g + 1;
                t2.setText("Patient ID: " + idx.get(0 + g));
            } else if (g > idx.size() - 2) {
                g = 0;
                t2.setText("Patient ID: " + idx.get(0 + g));
            }
        }
    });

    @Override
    public void actionPerformed(ActionEvent c) {

        if (c.getSource() == next) {
            card.next(panel);
            card2.next(p2);
            if (g <= idx.size() - 2) {
                g = g + 1;
                t2.setText("Patient ID: " + idx.get(0 + g));
            } else if (g > idx.size() - 2) {
                g = 0;
                t2.setText("Patient ID: " + idx.get(0 + g));
            }
        } else if (c.getSource() == home) {
            t2.setVisible(true);
            card.first(panel);
            card2.first(p2);
            t2.setText("Patient ID: " + idx.get(0));
            g = 0;
        } else if (c.getSource() == pre) {
            card.previous(panel);
            card2.previous(p2);
            if (g == 0) {
                g = idx.size() - 1;
                t2.setText("Patient ID: " + idx.get(0 + g));
            } else if (g <= idx.size() - 1) {
                g = g - 1;
                t2.setText("Patient ID: " + idx.get(0 + g));
            }
        } else if (c.getSource() == last) {
            card.last(panel);
            card2.last(p2);
            t2.setText("Patient ID: " + idx.get(idx.size() - 1));
            g = idx.size() - 1;
        } else if (c.getSource() == play) {
            if (timer.isRunning() == true) {
                timer.stop();
                np.setVisible(false);
                home.setEnabled(true);
                next.setEnabled(true);
                pre.setEnabled(true);
                last.setEnabled(true);
                play.setEnabled(true);
                list.setEnabled(true);
                info.setEnabled(true);
            } else {
                timer.start();
                np.setVisible(true);
                home.setEnabled(false);
                next.setEnabled(false);
                pre.setEnabled(false);
                last.setEnabled(false);
                list.setEnabled(false);
                info.setEnabled(false);
            }

        } else if (c.getSource() == list) {
            timer.stop();
            p2.setVisible(false);
            panel.setVisible(false);
            scr.setVisible(true);
            title.setVisible(true);
            t2.setVisible(false);
            np.setVisible(false);
            home.setEnabled(false);
            next.setEnabled(false);
            pre.setEnabled(false);
            last.setEnabled(false);
            play.setEnabled(false);
            list.setEnabled(false);
            info.setEnabled(false);
        } else if (c.getSource() == info) {
            if (p2.isVisible() == true) {
                t2.setVisible(true);
                nv.setVisible(false);
                panel.setVisible(true);
                p2.setVisible(false);
                home.setEnabled(true);
                next.setEnabled(true);
                pre.setEnabled(true);
                last.setEnabled(true);
                play.setEnabled(true);
                list.setEnabled(true);
            } else {
                t2.setVisible(false);
                nv.setVisible(true);
                panel.setVisible(false);
                p2.setVisible(true);
                home.setEnabled(false);
                next.setEnabled(false);
                pre.setEnabled(false);
                last.setEnabled(false);
                play.setEnabled(false);
                list.setEnabled(false);
            }
        }
    }
}
