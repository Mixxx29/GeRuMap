package dsw.gerumap.app.gui.swing.dialogs;

import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorChooserDialog extends AbstractDialog {

    private JPanel colorPanel;
    private JPanel selectedColorPanel;
    private JPanel hueBar;

    private int hue;
    private float saturation;
    private float value;

    public ColorChooserDialog(String title) {
        super();
        setSize(300, 420);
        setLocationRelativeTo(null);
        setIconImage(ResourceLoader.load("color_chooser.png", ResourceType.IMAGE));
        setTitle(title);

        setFocusable(true);
        requestFocus();

        hue = 0;
        saturation = 1.0f;
        value = 1.0f;

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        colorPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                for (int x = 0; x < getWidth(); x++) {
                    for (int y = 0; y < getWidth(); y++) {
                        g.setColor(
                                HSVtoRGB(
                                        hue,
                                        (float)x / (float)getWidth(),
                                        1.0f - (float)y / (float)getHeight()
                                )
                        );
                        g.fillRect(x, y, 1, 1);
                    }
                }
                g.setColor(new Color(200, 200, 200));
                g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                selectedColorPanel.setBackground(HSVtoRGB(hue, saturation, value));
            }
        };
        colorPanel.setPreferredSize(new Dimension(230, 230));

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                saturation = (float)e.getX() / (float)colorPanel.getWidth();
                saturation = Math.min(saturation, 1.0f);
                saturation = Math.max(saturation, 0.0f);

                value = 1.0f - (float)e.getY() / (float)colorPanel.getHeight();
                value = Math.min(value, 1.0f);
                value = Math.max(value, 0.0f);

                colorPanel.getParent().repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                saturation = (float)e.getX() / (float)colorPanel.getWidth();
                saturation = Math.min(saturation, 1.0f);
                saturation = Math.max(saturation, 0.0f);

                value = 1.0f - (float)e.getY() / (float)colorPanel.getHeight();
                value = Math.min(value, 1.0f);
                value = Math.max(value, 0.0f);

                colorPanel.getParent().repaint();
            }
        };
        colorPanel.addMouseListener(mouseAdapter);
        colorPanel.addMouseMotionListener(mouseAdapter);

        JPanel colorPanelWrapper = new JPanel(new GridBagLayout()) {
            @Override
            public void paint(Graphics g) {
                super.paint(g);

                int x = (getWidth() - colorPanel.getWidth()) / 2;
                x += (int)(saturation * colorPanel.getWidth());
                int y = (getHeight() - colorPanel.getHeight()) / 2;
                y += (int)((1.0f - value) * colorPanel.getHeight());

                Graphics2D g2 = (Graphics2D) g;

                g2.setStroke(new BasicStroke(3.0f));
                g2.setColor(Color.black);
                g2.drawOval(x - 5, y - 5, 10, 10);

                g2.setStroke(new BasicStroke(2.0f));
                g2.setColor(Color.white);
                g2.drawOval(x - 5, y - 5, 10, 10);
            }
        };
        colorPanelWrapper.setPreferredSize(new Dimension(250, 250));
        colorPanelWrapper.setBackground(new Color(100, 100, 100));
        colorPanelWrapper.add(colorPanel);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridwidth = 6;
        add(colorPanelWrapper, constraints);

        hueBar = new JPanel() {
            @Override
            public void paint(Graphics g) {
                for (int x = 0; x < getWidth(); x++) {
                    g.setColor(HSVtoRGB((int)((float)x / (float)getWidth() * 360), 1.0f, 1.0f));
                    g.fillRect(x, 0, 1, getHeight());
                }

                g.setColor(new Color(200, 200, 200));
                g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            }
        };
        hueBar.setPreferredSize(new Dimension(230, 20));

        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                hue = (int)((float)e.getX() / (float)hueBar.getWidth() * 360.0f);
                hue = Math.min(hue, 360);
                hue = Math.max(hue, 0);

                hueBar.getParent().repaint();
                colorPanel.getParent().repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                hue = (int)((float)e.getX() / (float)hueBar.getWidth() * 360.0f);
                hue = Math.min(hue, 360);
                hue = Math.max(hue, 0);

                hueBar.getParent().repaint();
                colorPanel.getParent().repaint();
            }
        };
        hueBar.addMouseListener(mouseAdapter);
        hueBar.addMouseMotionListener(mouseAdapter);

        JPanel hueBarWrapper = new JPanel(new GridBagLayout()) {
            @Override
            public void paint(Graphics g) {
                super.paint(g);

                int x = (getWidth() - hueBar.getWidth()) / 2;
                x += (float)hue / 360.0f * hueBar.getWidth();


                Graphics2D g2 = (Graphics2D) g;

                g2.setStroke(new BasicStroke(2.0f));
                g2.setColor(Color.white);
                g2.drawLine(x, 0, x, getWidth());
            }
        };
        hueBarWrapper.setPreferredSize(new Dimension(250, 30));
        hueBarWrapper.setOpaque(false);
        hueBarWrapper.add(hueBar);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        add(hueBarWrapper, constraints);

        selectedColorPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(new Color(200, 200, 200));
                g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            }
        };
        selectedColorPanel.setPreferredSize(new Dimension(80, 25));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, -20, 10, 0);
        add(selectedColorPanel, constraints);

        JLabel hexLabel = new JLabel("Hex:", SwingConstants.RIGHT);
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        add(hexLabel, constraints);

        JTextField hexValue = new JTextField();
        hexValue.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (getText(0, 1).equals("0")) return;
                if (!str.equals("#") && !verify(getText(0, offs) + str)) return;
                super.insertString(offs, str, a);
            }

            private boolean verify(String t) {
                int n;
                try {
                    n = Integer.parseInt(t.replace("#", ""));
                } catch (NumberFormatException e) {
                    return false;
                }
                return n >= 0 && n <= 255;
            }
        });
        hexValue.setPreferredSize(new Dimension(80, 25));
        hexValue.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 4;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0, -15, 0, 0);
        add(hexValue, constraints);

        JLabel redChanelLabel = new JLabel("R:", SwingConstants.RIGHT);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 20, 0, 5);
        add(redChanelLabel, constraints);

        JTextField redChanelField = new JTextField();
        redChanelField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (getText(0, 1).equals("0")) return;
                if (!verify(getText(0, offs) + str)) return;
                super.insertString(offs, str, a);
            }

            private boolean verify(String t) {
                int n;
                try {
                    n = Integer.parseInt(t);
                } catch (NumberFormatException e) {
                    return false;
                }
                return n >= 0 && n <= 255;
            }
        });
        redChanelField.setPreferredSize(new Dimension(40, 25));
        redChanelField.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 0, 25);
        add(redChanelField, constraints);

        JLabel greenChanelLabel = new JLabel("G:", SwingConstants.CENTER);
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 0, 5);
        add(greenChanelLabel, constraints);

        JTextField greenChanelField = new JTextField();
        greenChanelField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (getText(0, 1).equals("0")) return;
                if (!verify(getText(0, offs) + str)) return;
                super.insertString(offs, str, a);
            }

            private boolean verify(String t) {
                int n;
                try {
                    n = Integer.parseInt(t);
                } catch (NumberFormatException e) {
                    return false;
                }
                return n >= 0 && n <= 255;
            }
        });
        greenChanelField.setPreferredSize(new Dimension(40, 25));
        greenChanelField.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 0, 25);
        add(greenChanelField, constraints);

        JLabel blueChanelLabel = new JLabel("B:", SwingConstants.CENTER);
        constraints.gridx = 4;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        add(blueChanelLabel, constraints);

        JTextField blueChanelField = new JTextField();
        blueChanelField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (getText(0, 1).equals("0")) return;
                if (!verify(getText(0, offs) + str)) return;
                super.insertString(offs, str, a);
            }

            private boolean verify(String t) {
                int n;
                try {
                    n = Integer.parseInt(t);
                } catch (NumberFormatException e) {
                    return false;
                }
                return n >= 0 && n <= 255;
            }
        });
        blueChanelField.setPreferredSize(new Dimension(40, 25));
        blueChanelField.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 5;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, -15, 0, 0);
        add(blueChanelField, constraints);
    }

    private Color HSVtoRGB(int hue, float saturation, float value) {
        int M = (int)(255 * value);
        int m = (int)(M * (1.0f - saturation));
        int z = (int)((M - m) * (1.0f - Math.abs((hue / 60.0f) % 2 - 1.0f)));

        int r, g, b;
        if (0 <= hue && hue < 60) {
            r = M;
            g = z + m;
            b = m;
        } else if (60 <= hue && hue < 120) {
            r = z + m;
            g = M;
            b = m;
        } else if (120 <= hue && hue < 180) {
            r = m;
            g = M;
            b = z + m;
        } else if (180 <= hue && hue < 240) {
            r = m;
            g = z + m;
            b = M;
        } else if (240 <= hue && hue < 300) {
            r = z + m;
            g = m;
            b = M;
        } else {
            r = M;
            g = m;
            b = z + m;
        }

        return new Color(r, g, b);
    }

    @Override
    public Object start(Object object) {
        if (object instanceof Color color) {
            float[] array = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
            hue = (int)(array[0] * 360.0f);
            saturation = array[1];
            value = array[2];
        }
        setVisible(true);
        return HSVtoRGB(hue, saturation, value);
    }
}
