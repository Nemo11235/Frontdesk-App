import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.nio.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.nio.file.*;
public class FrontDeskApp {
 // frames
 private static GridBagLayout gbl = new GridBagLayout();
 private static JFrame frame = new JFrame("UBC");
 private static JFrame frame2 = new JFrame("Item Selection");
 private static JFrame frame3 = new JFrame("UBC");
 private static JFrame frame4 = new JFrame("UBC");
 private static JFrame framePrivate = new JFrame("Private Lesson");
 private static JFrame framePass = new JFrame("Pass Purchase");
 private static JFrame frameString = new JFrame("Stringing Service");
 // panels
 private static JPanel bpanel = new JPanel(gbl);
 private static JPanel panel = new JPanel(gbl);
 private static JPanel panel4 = new JPanel(gbl);
 // labels
 private static GridBagConstraints c = new GridBagConstraints();
 private JLabel labelName = new JLabel("Cashier Name: ");
 private JLabel labelDate = new JLabel("Date (mm-dd-yy): ");
 private JLabel labelShift = new JLabel("Shift(from-to): ");
 private JLabel labelNum = new JLabel("物品编号：");
 private JLabel labelQuan = new JLabel();
 private JLabel labelQuanQ = new JLabel("数量: ");
 private JLabel labelCash = new JLabel("Cash");
 private JLabel labelCard = new JLabel("Card");
 private JLabel labelCheck = new JLabel("Check");
 private JLabel labelCoach = new JLabel("教练: ");
 private JLabel labelStd = new JLabel("学生: ");
 private JLabel labelFrom = new JLabel("开始时间: ");
 private JLabel labelTo = new JLabel("结束时间: ");
 private JLabel labelPrice = new JLabel("价格: ");
 private JLabel lbType = new JLabel("Pass类型：");
 private JLabel lbNum = new JLabel("卡号：");
 private JLabel lbName = new JLabel("顾客姓名：");
 private JLabel lbPhone = new JLabel("电话号码：");
 private JLabel labelType = new JLabel("单练课类型：");
 // textfields
 private static JTextField textNum = new JTextField();
 private static JTextField textQuan = new JTextField();
 private static JTextField textStudent = new JTextField();
 private static JTextField textHour = new JTextField();
 private static JTextField textMin = new JTextField();
 private static JTextField textToMin = new JTextField();
 private static JTextField textToHour = new JTextField();
 private static JTextField textPrice = new JTextField();
 private static JTextField txtNum = new JTextField();
 private static JTextField txtName = new JTextField();
 private static JTextField txtPhone = new JTextField();
 // textarea
 private static JTextArea textBox = new JTextArea();
 // variables
 private String cashier = "";
 private String date = "";
 private String shift = "";
 private String fileName = "";
 private String itemList = "";
 private int num = 0;
 private int quantity = 0;
 private String fromHour, hour, toHour, coach, student, plType;
 private double price;
 private double cashTotal = 0, cardTotal = 0, checkTotal = 0;
 private String passType, passNumber, passName, passPhone;
 private String day, month, year;
 private String dir;
 private int customerType;
 private String sType, cType, memberID;
 private static int itemPrivate = 16;
 private static int itemString = 17;
 private static int itemPass = 15;
 private static String item, unit, pieces, total;
 // combobox
 private String[] coachList = { "请选择教练", "Liu", "Hua", "Allen", "Chris", "Johnson", "Cindy", "Collin" };
 private String[] cashierList = { "值班前台", "Allen", "Lisa", "Sherry", "Qianqian", "刘教练", "Jojo", "Levana" };
 private String[] ampmList = { "a.m.", "p.m." };
 private String[] typeList = { "Pass", "Peak Time", "Lunch Time" };
 private String[] stringList = { "球线", "BG 65", "BG 65Ti", "BG 66", "BG 66UL", "BG 68Ti", "BG 70Pro", "BG 80",
   "BG 85", "NBG 95", "NBG 98", "NBG 99", "Aerobite", "Labor Only" };
 private String[] customerList = { "顾客类型", "Non-Member", "Member", "Elite Team" };
 private String[] plTypeList = { "课程类型", "1v1", "1v2" };
 private String[] monthList = new String[13];
 private String[] hourList = new String[25];
 private String[] hour2List = new String[25];
 private String[] yearList = new String[20];
 private String[] dayList = new String[32];
 private JComboBox plTypeBox = new JComboBox(plTypeList);
 private JComboBox CoachBox = new JComboBox(coachList);
 private JComboBox cashierBox = new JComboBox(cashierList);
 private JComboBox ampmBox = new JComboBox(ampmList);
 private JComboBox typeBox = new JComboBox(typeList);
 private JComboBox stringBox = new JComboBox(stringList);
 private JComboBox customerBox = new JComboBox(customerList);
 private JComboBox hourBox;
 private JComboBox hourBox2;
 private JComboBox yearBox;
 private JComboBox monthBox;
 private JComboBox dayBox;
 // buttons
 private static JButton enter = new JButton("确认");
 private static JButton enter2 = new JButton("确认");
 private static JButton enter3 = new JButton("确认");
 private static JButton bttnCash = new JButton();
 private static JButton bttnCard = new JButton();
 private static JButton bttnCheck = new JButton();
 private static JButton bttnBack = new JButton("返回");
 private static JButton bttnBack2 = new JButton("返回");
 private static JButton bttnEnter2 = new JButton("确认");
 private static JButton exit = new JButton("退出程序（结账）");
 private static JButton bttnList = new JButton("显示账目");
 private static JButton bttnBackPass = new JButton("返回");
 private static JButton bttnEnterPass = new JButton("确认");
 private static JButton bttnEnterString = new JButton("确认");
 private static JButton bttnBackString = new JButton("返回");
 private static JButton bttnFindPass = new JButton("查找Pass");
 Font def = new Font("Ariel", Font.PLAIN, 15);
 Dimension defD = new Dimension(160, 28);
 ArrayList<item> items = new ArrayList<item>();
 HashMap<Integer, String> passList = new HashMap<>();
 Integer firstPassNum;
 File passFile = new File(dir + "/pass");
 double p;
 int amount;
 public FrontDeskApp() {
  dir = System.getProperty("user.dir");
  hourList[0] = "开始时间";
  monthList[0] = "月";
  yearList[0] = "年";
  dayList[0] = "日";
  hour2List[0] = "结束时间";
  for (int i = 1; i < (hourList.length - 1); i++) {
   hourList[i] = String.valueOf(i);
  }
  for (int i = 1; i < monthList.length; i++) {
   monthList[i] = String.valueOf(i);
  }
  for (int i = 19; i < (19 + yearList.length - 1); i++) {
   yearList[(i - 18)] = String.valueOf(i);
  }
  for (int i = 1; i < dayList.length; i++) {
   dayList[i] = String.valueOf(i);
  }
  for (int i = 1; i < hourList.length; i++) {
   hourList[i] = String.valueOf(i);
   hour2List[i] = String.valueOf(i);
  }
  for (int i = 1; i < dayList.length; i++) {
   dayList[i] = String.valueOf(i);
  }
  hourBox = new JComboBox(hourList);
  monthBox = new JComboBox(monthList);
  yearBox = new JComboBox(yearList);
  hourBox = new JComboBox(hourList);
  hourBox2 = new JComboBox(hour2List);
  dayBox = new JComboBox(dayList);
  // Drinks
  items.add(new item("pl", 0));
  items.add(new item("汽水", 1));
  items.add(new item("水", 1));
  items.add(new item("Gatorade", 2.5));
  items.add(new item("维他命水", 2.5));
  items.add(new item("Monster", 2.5));
  items.add(new item("Snapple", 2.5));
  // Birdies
  items.add(new item("优选", 21));
  items.add(new item("精选", 20));
  items.add(new item("李宁", 27));
  items.add(new item("yy30", 35));
  items.add(new item("yy50", 36));
  // Grip and Strings
  items.add(new item("手胶", 8));
  items.add(new item("毛巾胶", 7));
  items.add(new item("袜子", 9));
  /* log in */
  // panel for the frame which asks for cashier's info
  InputStream ubcInput = getClass().getResourceAsStream("ubc.png");
  JLabel image = null;
  try {
   image = new JLabel(new ImageIcon(ImageIO.read(ubcInput)));
  } catch (IOException e) {
   e.printStackTrace();
  }
  // set up the fonts
  labelName.setFont(def);
  labelDate.setFont(def);
  labelShift.setFont(def);
  enter.addActionListener(new ClickedEnter());
  cashierBox.addActionListener(new CashierBoxAction());
  dayBox.addActionListener(new DayBoxAction());
  monthBox.addActionListener(new MonthBoxAction());
  yearBox.addActionListener(new YearBoxAction());
  hourBox.addActionListener(new HourBoxAction());
  hourBox2.addActionListener(new HourBox2Action());
  // add labels and textFields to the panel
  c = setupConstraints(0, 0, 1, 1, GridBagConstraints.HORIZONTAL);
  panel.add(labelName, c);
  c = setupConstraints(0, 1, 1, 1, GridBagConstraints.HORIZONTAL);
  panel.add(labelDate, c);
  c = setupConstraints(0, 2, 1, 1, GridBagConstraints.HORIZONTAL);
  panel.add(labelShift, c);
  c = setupConstraints(1, 0, 6, 1, GridBagConstraints.HORIZONTAL);
  panel.add(cashierBox, c);
  c = setupConstraints(1, 1, 2, 1, GridBagConstraints.HORIZONTAL);
  panel.add(monthBox, c);
  c = setupConstraints(3, 1, 2, 1, GridBagConstraints.HORIZONTAL);
  panel.add(dayBox, c);
  c = setupConstraints(5, 1, 2, 1, GridBagConstraints.HORIZONTAL);
  panel.add(yearBox, c);
  c = setupConstraints(1, 2, 3, 1, GridBagConstraints.HORIZONTAL);
  panel.add(hourBox, c);
  c = setupConstraints(4, 2, 2, 1, GridBagConstraints.HORIZONTAL);
  panel.add(hourBox2, c);
  c = setupConstraints(3, 3, 2, 1, GridBagConstraints.HORIZONTAL);
  panel.add(enter, c);
  // add the panel to the frame
  if (image != null) {
   frame.add(image);
  }
  frame.add(panel);
  frame.setSize(800, 400);
  frame.setLayout(gbl);
  centerWindow(frame);
  centerWindow(frame2);
  centerWindow(frame3);
  centerWindow(frame4);
  centerWindow(framePrivate);
  centerWindow(framePass);
  frame.setVisible(true);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  framePrivate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frameString.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  /* end of log in */
  InputStream is = this.getClass().getResourceAsStream("ItemList.txt");
  Scanner infile = new Scanner(is);
  while (infile.hasNextLine()) {
   itemList += infile.nextLine() + "\n";
  }
  infile.close();
  textNum.setPreferredSize(defD);
  textNum.setFont(def);
  textBox.setText(itemList);
  textBox.setEditable(false);
  textBox.setOpaque(false);
  textBox.setFont(new Font("Ariel", Font.PLAIN, 20));
  exit.addActionListener(new ExitAction());
  bttnFindPass.addActionListener(new FindPassAction());
  bttnList.addActionListener(new openListAction());
  frame2.setLayout(gbl);
  frame2.setSize(700, 600);
  frame2.setLocationRelativeTo(null);
  textNum.addKeyListener(new enterKey2());
  enter2.addActionListener(new ClickedEnter2());
  labelNum.setFont(def);
  c = setupConstraints(0, 0, 4, 1, GridBagConstraints.HORIZONTAL);
  frame2.add(textBox, c);
  c = setupConstraints(0, 1, 1, 1, GridBagConstraints.HORIZONTAL);
  frame2.add(labelNum, c);
  c = setupConstraints(1, 1, 2, 1, GridBagConstraints.HORIZONTAL);
  frame2.add(textNum, c);
  c = setupConstraints(3, 1, 1, 1, GridBagConstraints.HORIZONTAL);
  frame2.add(enter2, c);
  c = setupConstraints(0, 2, 1, 1, GridBagConstraints.HORIZONTAL);
  frame2.add(bttnList, c);
  c = setupConstraints(3, 2, 1, 1, GridBagConstraints.HORIZONTAL);
  frame2.add(exit, c);
  c = setupConstraints(1, 2, 2, 1, GridBagConstraints.HORIZONTAL);
  frame2.add(bttnFindPass, c);
  /* end of item selection */
  /* quantity */
  frame3.setLayout(gbl);
  frame3.setSize(600, 300);
  frame3.setLocationRelativeTo(null);
  textQuan.setPreferredSize(defD);
  textQuan.addKeyListener(new enterKey3());
  textQuan.setFont(def);
  labelQuanQ.setFont(def);
  labelQuan.setFont(new Font("Ariel", Font.PLAIN, 20));
  enter3.addActionListener(new ClickedEnter3());
  bttnBack.addActionListener(new ClickBack());
  c = setupConstraints(0, 0, 2, 1, GridBagConstraints.HORIZONTAL);
  frame3.add(labelQuan, c);
  c = setupConstraints(0, 1, 1, 1, GridBagConstraints.HORIZONTAL);
  frame3.add(labelQuanQ, c);
  c = setupConstraints(1, 1, 1, 1, GridBagConstraints.HORIZONTAL);
  frame3.add(textQuan, c);
  c = setupConstraints(0, 2, 1, 1, GridBagConstraints.HORIZONTAL);
  frame3.add(bttnBack, c);
  c = setupConstraints(1, 2, 1, 1, GridBagConstraints.HORIZONTAL);
  frame3.add(enter3, c);
  /* end of quantity */
  /* payment */
  // image size should be 160*90
  InputStream cashInput = getClass().getResourceAsStream("cash.png");
  InputStream cardInput = getClass().getResourceAsStream("card.png");
  InputStream checkInput = getClass().getResourceAsStream("check.png");
  try {
   bttnCash.setIcon(new ImageIcon(ImageIO.read(cashInput)));
   bttnCard.setIcon(new ImageIcon(ImageIO.read(cardInput)));
   bttnCheck.setIcon(new ImageIcon(ImageIO.read(checkInput)));
  } catch (IOException e) {
   e.printStackTrace();
  }
  bttnCash.addActionListener(new ClickedCash());
  bttnCard.addActionListener(new ClickedCard());
  bttnCheck.addActionListener(new ClickedCheck());
  labelCash.setFont(def);
  labelCard.setFont(def);
  labelCheck.setFont(def);
  c.gridx = 0;
  c.gridy = 0;
  c.insets = new Insets(0, 0, 0, 0);
  bpanel.add(bttnCash, c);
  c.gridx = 1;
  bpanel.add(bttnCard, c);
  c.gridx = 2;
  bpanel.add(bttnCheck, c);
  c.gridx = 0;
  c.gridy = 1;
  bpanel.add(labelCash, c);
  c.gridx = 1;
  bpanel.add(labelCard, c);
  c.gridx = 2;
  bpanel.add(labelCheck, c);
  c.gridx = 0;
  c.gridy = 0;
  c.fill = GridBagConstraints.HORIZONTAL;
  c.insets = new Insets(20, 0, 0, 0);
  frame4.setLayout(gbl);
  frame4.add(bpanel, c);
  c.gridx = 0;
  c.gridy = 1;
  frame4.add(panel4, c);
  frame4.setSize(600, 300);
  frame4.setLocationRelativeTo(null);
  /* payment */
  /* private lesson */
  CoachBox.setPreferredSize(defD);
  textStudent.setPreferredSize(defD);
  textHour.setPreferredSize(defD);
  textToHour.setPreferredSize(defD);
  plTypeBox.setPreferredSize(defD);
  framePrivate.setSize(600, 300);
  framePrivate.setLayout(gbl);
  bttnEnter2.addActionListener(new ClickedEnter4());
  bttnBack2.addActionListener(new ClickedBackP());
  CoachBox.addActionListener(new CoachBoxAction());
  plTypeBox.addActionListener(new plTypeBoxAction());
  c = setupConstraints(0, 0, 1, 1, GridBagConstraints.HORIZONTAL);
  framePrivate.add(labelCoach, c);
  c = setupConstraints(1, 0, 1, 1, GridBagConstraints.HORIZONTAL);
  framePrivate.add(CoachBox, c);
  c = setupConstraints(2, 0, 1, 1, GridBagConstraints.HORIZONTAL);
  framePrivate.add(labelStd, c);
  c = setupConstraints(3, 0, 1, 1, GridBagConstraints.HORIZONTAL);
  framePrivate.add(textStudent, c);
  c = setupConstraints(0, 1, 1, 1, GridBagConstraints.HORIZONTAL);
  framePrivate.add(labelFrom, c);
  c = setupConstraints(1, 1, 1, 1, GridBagConstraints.HORIZONTAL);
  framePrivate.add(textHour, c);
  c = setupConstraints(2, 1, 1, 1, GridBagConstraints.HORIZONTAL);
  framePrivate.add(labelTo, c);
  c = setupConstraints(3, 1, 1, 1, GridBagConstraints.HORIZONTAL);
  framePrivate.add(textToHour, c);
  c = setupConstraints(0, 2, 1, 1, GridBagConstraints.HORIZONTAL);
  framePrivate.add(labelPrice, c);
  c = setupConstraints(1, 2, 1, 1, GridBagConstraints.HORIZONTAL);
  framePrivate.add(textPrice, c);
  c = setupConstraints(2, 2, 1, 1, GridBagConstraints.HORIZONTAL);
  framePrivate.add(labelType, c);
  c = setupConstraints(3, 2, 1, 1, GridBagConstraints.HORIZONTAL);
  framePrivate.add(plTypeBox, c);
  c = setupConstraints(0, 3, 2, 1, GridBagConstraints.HORIZONTAL);
  framePrivate.add(bttnEnter2, c);
  c = setupConstraints(2, 3, 2, 1, GridBagConstraints.HORIZONTAL);
  framePrivate.add(bttnBack2, c);
  /* end of private lesson */
  /* Pass */
  framePass.setLayout(gbl);
  framePass.setSize(600, 400);
  txtNum.setPreferredSize(defD);
  txtName.setPreferredSize(defD);
  txtPhone.setPreferredSize(defD);
  typeBox.addActionListener(new TypeBoxAction());
  bttnEnterPass.addActionListener(new ClickedEnterPass());
  bttnBackPass.addActionListener(new ClickedBackPass());
  c = setupConstraints(0, 0, 1, 1, GridBagConstraints.HORIZONTAL);
  framePass.add(lbType, c);
  c = setupConstraints(1, 0, 2, 1, GridBagConstraints.HORIZONTAL);
  framePass.add(typeBox, c);
  c = setupConstraints(3, 0, 1, 1, GridBagConstraints.HORIZONTAL);
  framePass.add(lbNum, c);
  c = setupConstraints(4, 0, 2, 1, GridBagConstraints.HORIZONTAL);
  framePass.add(txtNum, c);
  c = setupConstraints(0, 1, 1, 1, GridBagConstraints.HORIZONTAL);
  framePass.add(lbName, c);
  c = setupConstraints(1, 1, 2, 1, GridBagConstraints.HORIZONTAL);
  framePass.add(txtName, c);
  c = setupConstraints(3, 1, 1, 1, GridBagConstraints.HORIZONTAL);
  framePass.add(lbPhone, c);
  c = setupConstraints(4, 1, 2, 1, GridBagConstraints.HORIZONTAL);
  framePass.add(txtPhone, c);
  c = setupConstraints(1, 2, 1, 1, GridBagConstraints.HORIZONTAL);
  framePass.add(bttnEnterPass, c);
  c = setupConstraints(4, 2, 1, 1, GridBagConstraints.HORIZONTAL);
  framePass.add(bttnBackPass, c);
  /* end of pass */
  /* string */
  frameString.setLayout(gbl);
  frameString.setSize(600, 300);
  customerBox.setPreferredSize(defD);
  stringBox.setPreferredSize(defD);
  stringBox.addActionListener(new stringBoxAction());
  customerBox.addActionListener(new customerBoxAction());
  bttnEnterString.addActionListener(new stringEnterAction());
  bttnBackString.addActionListener(new stringBackAction());
  c = setupConstraints(0, 0, 1, 1, GridBagConstraints.HORIZONTAL);
  frameString.add(customerBox, c);
  c = setupConstraints(1, 0, 1, 1, GridBagConstraints.HORIZONTAL);
  frameString.add(stringBox, c);
  c = setupConstraints(0, 1, 1, 1, GridBagConstraints.HORIZONTAL);
  frameString.add(bttnEnterString, c);
  c = setupConstraints(1, 1, 1, 1, GridBagConstraints.HORIZONTAL);
  frameString.add(bttnBackString, c);
  /* end of string */
 }
 /* log in */
 // enter button for the first frame which ask user for name, date and shift
 class CashierBoxAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == cashierBox) {
    JComboBox cb = (JComboBox) e.getSource();
    cashier = (String) cb.getSelectedItem();
   }
  }
 }
 class MonthBoxAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == monthBox) {
    JComboBox cb = (JComboBox) e.getSource();
    month = (String) cb.getSelectedItem();
   }
  }
 }
 class DayBoxAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == dayBox) {
    JComboBox cb = (JComboBox) e.getSource();
    day = (String) cb.getSelectedItem();
   }
  }
 }
 class HourBoxAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == hourBox) {
    JComboBox cb = (JComboBox) e.getSource();
    hour = (String) cb.getSelectedItem();
   }
  }
 }
 class HourBox2Action implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == hourBox2) {
    JComboBox cb = (JComboBox) e.getSource();
    toHour = (String) cb.getSelectedItem();
   }
  }
 }
 class YearBoxAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == yearBox) {
    JComboBox cb = (JComboBox) e.getSource();
    year = (String) cb.getSelectedItem();
   }
  }
 }
 class ClickedEnter implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   date = "(" + month + "-" + day + "-" + year + ")";
   shift = "(" + hour + "-" + toHour + ")";
   try {
    File file = new File(dir + "/" + year + "年");
    boolean bool = file.mkdir();
    if (bool) {
     System.out.println("Directory created successfully");
    } else {
     System.out.println("Sorry couldn’t create specified directory");
    }
    file = new File(dir + "/" + year + "年/" + month + "月");
    bool = file.mkdir();
    if (bool) {
     System.out.println("Directory created successfully");
    } else {
     System.out.println("Sorry couldn’t create specified directory");
    }
    fileName = dir + "/" + year + "年/" + month + "月/" + day + cashier + ".txt";
    String toPrint = cashier + " " + date + " " + shift;
    printFile(toPrint, fileName);
    frame.dispose();
    frame2.setVisible(true);
   } catch (Exception se) {
    se.printStackTrace();
   }
   deleteFolder();
   readPassFile();
   deletePass();
  }
 }
 /* item selection */
 // enter key for frame2 (item selection)
 class enterKey2 implements KeyListener {
  public void keyTyped(KeyEvent e) {
  }
  public void keyPressed(KeyEvent e) {
   if (e.getKeyCode() == KeyEvent.VK_ENTER) {
    try {
     num = Integer.valueOf(textNum.getText());
     if (num < 0 || num > (items.size() + 3)) {
      throw new Exception();
     }
     if (num == 0){
    	item = extendString(JOptionPane.showInputDialog("请输入商品名称："), 35);
    	p = Double.parseDouble(JOptionPane.showInputDialog("请输入单价："));
    	unit = extendString("$" + price , 12);
    	amount = Integer.parseInt(JOptionPane.showInputDialog("请输入数量："));
    	pieces = extendString(Integer.toString(amount), 12);
    	total = extendString("$" + Double.toString(amount * p), 12);
    	frame4.setVisible(true);
     } else if (num == itemPrivate) {
      frame2.setVisible(false);
      textStudent.setText(null);
      textPrice.setText(null);
      textHour.setText(null);
      textToHour.setText(null);
      centerWindow(framePrivate);
      framePrivate.setVisible(true);
     } else if (num == itemPass) {
      txtNum.setText(null);
      txtName.setText(null);
      txtPhone.setText(null);
      frame2.setVisible(false);
      centerWindow(framePass);
      framePass.setVisible(true);
     } else if (num == itemString) {
      centerWindow(frameString);
      frame2.setVisible(false);
      frameString.setVisible(true);
     } else {
      labelQuan.setText("所选商品为: " + items.get(num).name + "\t单价: $" + items.get(num).price);
      frame2.setVisible(false);
      frame3.setVisible(true);
     }
     textNum.setText(null);
    } catch (Exception ne) {
     ne.printStackTrace();
     JOptionPane.showMessageDialog(null, "请输入0至 " + (items.size() - 1) + "之间的数字");
    }
   }
  }
  public void keyReleased(KeyEvent e) {
  }
 } // end of enterKey2
  // enter button for the second frame which asks user for the ID of item
 class ClickedEnter2 implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   try {
    num = Integer.valueOf(textNum.getText());
    if (num < 0 || num > (items.size() + 3)) {
     throw new Exception();
    }
    if (num == 0){
    	item = extendString(JOptionPane.showInputDialog("请输入商品名称："), 35);
    	p = Double.parseDouble(JOptionPane.showInputDialog("请输入单价："));
    	unit = extendString("$" + price , 12);
    	amount = Integer.parseInt(JOptionPane.showInputDialog("请输入数量："));
    	pieces = extendString(Integer.toString(amount), 12);
    	total = extendString("$" + Double.toString(amount * p), 12);
    	frame4.setVisible(true);
    } else if (num == itemPrivate) {
     frame2.setVisible(false);
     textStudent.setText(null);
     textPrice.setText(null);
     textHour.setText(null);
     textToHour.setText(null);
     textMin.setText(null);
     textToMin.setText(null);
     framePrivate.setVisible(true);
    } else if (num == itemPass) {
     txtNum.setText(null);
     txtName.setText(null);
     txtPhone.setText(null);
     frame2.setVisible(false);
     centerWindow(framePass);
     framePass.setVisible(true);
    } else if (num == itemString) {
     frame2.setVisible(false);
     centerWindow(frameString);
     frameString.setVisible(true);
    } else {
     labelQuan.setText("所选商品为: " + items.get(num).name + "\t单价: $" + items.get(num).price);
     frame2.setVisible(false);
     frame3.setVisible(true);
    }
    textNum.setText(null);
   } catch (Exception ne) {
    ne.printStackTrace();
    JOptionPane.showMessageDialog(null, "请输入0至 " + (items.size() - 1) + "之间的数字");
   }
  }
 }
 class ExitAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   int input = JOptionPane.showConfirmDialog(null, "您确定要退出程序吗？(此操作不可逆)");
   if (input == 0) {
    String oneLine = "\nCash:\t$" + cashTotal + "\nCard:\t$" + cardTotal + "\nCheck:\t$" + checkTotal
      + "\nTotal:\t$" + (double) (cashTotal + cardTotal + checkTotal);
    printFile(oneLine, fileName);
    System.exit(0);
   }
  }
 }
 class FindPassAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   findPass();
  }
 }
 class openListAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   try {
    File passFile = new File(dir + "/" + year + "年/" + month + "月/" + day + cashier + ".txt");
    Desktop desktop = Desktop.getDesktop();
    if (passFile.exists())
     desktop.open(passFile);
   } catch (Exception pe) {
    pe.printStackTrace();
   }
  }
 }
 /* quantity */
 // actionListener that goes from quantity frame back to item selection frame
 class ClickBack implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   textQuan.setText("");
   frame3.setVisible(false);
   frame2.setVisible(true);
  }
 }
 // enter button for the third frame which asks for the quantity
 class ClickedEnter3 implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   try {
    quantity = Integer.valueOf(textQuan.getText());
    frame3.setVisible(false);
    textQuan.setText(null);
    frame4.setVisible(true);
   } catch (NumberFormatException ne) {
    ne.printStackTrace();
    JOptionPane.showMessageDialog(null, "请输入一个正整数");
   }
  }
 }
 class enterKey3 implements KeyListener {
  public void keyTyped(KeyEvent e) {
  }
  public void keyReleased(KeyEvent e) {
  }
  public void keyPressed(KeyEvent e) {
   if (e.getKeyCode() == KeyEvent.VK_ENTER) {
    try {
     quantity = Integer.valueOf(textQuan.getText());
     frame3.setVisible(false);
     textQuan.setText(null);
     frame4.setVisible(true);
    } catch (NumberFormatException ne) {
     ne.printStackTrace();
     JOptionPane.showMessageDialog(null, "请输入一个正整数");
    }
   }
  }
 }
 /* private lesson */
 // actionlistener for the enter button which in framePrivate)
 class ClickedEnter4 implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   try {
    student = textStudent.getText();
    fromHour = textHour.getText();
    toHour = textToHour.getText();
    price = Integer.valueOf(textPrice.getText());
    framePrivate.setVisible(false);
    textNum.setText(null);
    frame4.setVisible(true);
   } catch (NumberFormatException ne) {
    ne.printStackTrace();
    JOptionPane.showMessageDialog(null, "请输入一个正整数");
   }
  }
 }
 class ClickedBackP implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   framePrivate.setVisible(false);
   textNum.setText(null);
   frame2.setVisible(true);
  }
 }
 class CoachBoxAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == CoachBox) {
    JComboBox cb = (JComboBox) e.getSource();
    coach = (String) cb.getSelectedItem();
   } else {
    JOptionPane.showMessageDialog(null, "Error!");
   }
  }
 }
 class plTypeBoxAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == plTypeBox) {
    JComboBox cb = (JComboBox) e.getSource();
    plType = (String) cb.getSelectedItem();
   } else {
    JOptionPane.showMessageDialog(null, "Error!");
   }
  }
 }
 /* pass */
 class TypeBoxAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == typeBox) {
    JComboBox cb = (JComboBox) e.getSource();
    passType = (String) cb.getSelectedItem();
    if (passType == "Peak Time") {
     price = 95;
    } else {
     price = 72;
    }
   } else {
    JOptionPane.showMessageDialog(null, "Error!");
   }
  }
 }
 class ClickedEnterPass implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   passNumber = txtNum.getText();
   passName = txtName.getText();
   passPhone = txtPhone.getText();
   framePass.setVisible(false);
   frame4.setVisible(true);
  }
 }
 class ClickedBackPass implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == bttnBackPass) {
    textQuan.setText("");
    framePass.setVisible(false);
    frame2.setVisible(true);
   }
  }
 }
 /* payment */
 // actionlisteners for the buttons in frame 4 (payment)
 class ClickedCash implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   String note = JOptionPane.showInputDialog("备注（选填）: ");
   if (num == 0) {
    String oneLine = item + unit + pieces + total + "\tcash\t" + note;
    printFile(oneLine, fileName);
    JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
    cashTotal += amount * p;
   }
   if (num == itemPrivate) {
    item = extendString("PL-" + coach + "(" + fromHour + "-" + toHour + ")" + "(" + student + ")", 35);
    unit = extendString("$" + String.valueOf(price), 12);
    pieces = extendString("1", 12);
    total = extendString("$" + String.valueOf(price), 12);
    String oneLine = item + unit + pieces + total + "\tcash\t" + note;
    printFile(oneLine, fileName);
    JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
    oneLine += "\t" + date + "\tby\t" + cashier;
    printFile(oneLine, dir + "/" + year + "年/" + month + "月/" + coach);
    cashTotal += price;
   } else if (num == itemPass) {
    item = extendString(passType + " Pass#" + passNumber, 35);
    unit = extendString("$" + String.valueOf(price), 12);
    pieces = extendString("1", 12);
    total = extendString("$" + String.valueOf(price), 12);
    String oneLine = item + unit + pieces + total + "\tcash\t" + note;
    printFile(oneLine, fileName);
    JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
    oneLine = "#" + passNumber + passType + "\t" + "\tto\t" + passName + "(" + passPhone + ")" + "\tcash\t"
      + "on\t" + date + "by " + cashier + "\t" + note;
    printFile(oneLine, passFile.getName());
    String passNum = oneLine.substring(1, 5);
    Integer passNumInt = Integer.parseInt(passNum);
    passList.put(passNumInt, oneLine);
    cashTotal += price;
   } else if (num == itemString) {
    item = extendString(sType + "(" + memberID + ")", 35);
    unit = extendString("$" + String.valueOf(price), 12);
    pieces = extendString("1", 12);
    total = extendString("$" + String.valueOf(price), 12);
    String oneLine = item + unit + pieces + total + "\tcash\t" + note;
    JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
    printFile(oneLine, fileName);
    cashTotal += price;
   } else {
    item = extendString(items.get(num).name, 35);
    unit = extendString("$" + String.valueOf(items.get(num).price), 12);
    pieces = extendString(String.valueOf(quantity), 12);
    total = extendString("$" + String.valueOf((quantity * items.get(num).price)), 12);
    String oneLine = item + unit + pieces + total + "\tcash\t" + note;
    printFile(oneLine, fileName);
    JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
    cashTotal += (double) (quantity * items.get(num).price);
   }
   frame4.setVisible(false);
   frame2.setVisible(true);
  }
 }
 class ClickedCard implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   String note = JOptionPane.showInputDialog("备注（选填:");
   if (price < 20) {
	     price += 0.5;
	  }
   if (num == 0) {
	   String oneLine = item + unit + pieces + total + "\tcard\t" + note;
	   printFile(oneLine, fileName);
	   JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
	   cardTotal += amount * p;
	   if (amount * p < 20)
		   cardTotal += 0.5;
   } else if (num == itemPrivate) {
    item = extendString("PL-" + coach + "(" + fromHour + "-" + toHour + ")" + "(" + student + ")", 35);
    unit = extendString("$" + String.valueOf(price), 12);
    pieces = extendString("1", 12);
    total = extendString("$" + String.valueOf(price), 12);
    String oneLine = item + unit + pieces + total + "\tcard\t" + note;
    printFile(oneLine, fileName);
    JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
    oneLine += "\t" + date + "\tby\t" + cashier;
    printFile(oneLine, dir + "/" + year + "年/" + month + "月/" + coach);
    cardTotal += price;
   } else if (num == itemPass) {
    item = extendString(passType + " Pass#" + passNumber, 35);
    unit = extendString("$" + String.valueOf(price), 12);
    pieces = extendString("1", 12);
    total = extendString("$" + String.valueOf(price), 12);
    String oneLine = item + unit + pieces + total + "\tcard\t" + note;
    printFile(oneLine, fileName);
    JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
    oneLine = "#" + passNumber + passType + "\t" + "\tto\t" + passName + "(" + passPhone + ")" + "\tcard\t"
      + "on\t" + date + "by " + cashier + "\t" + note;
    printFile(oneLine, passFile.getName());
    String passNum = oneLine.substring(1, 5);
    Integer passNumInt = Integer.parseInt(passNum);
    passList.put(passNumInt, oneLine);
    cardTotal += price;
   } else if (num == itemString) {
    item = extendString(sType + "(" + memberID + ")", 35);
    unit = extendString("$" + String.valueOf(price), 12);
    pieces = extendString("1", 12);
    total = extendString("$" + String.valueOf(price), 12);
    String oneLine = item + unit + pieces + total + "\tcard\t" + note;
    JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
    printFile(oneLine, fileName);
    cardTotal += price;
   } else {
    double totalPrice = (quantity * items.get(num).price);
    if (totalPrice < 20) {
     totalPrice += 0.5;
    }
    item = extendString(items.get(num).name, 35);
    unit = extendString("$" + String.valueOf(items.get(num).price), 12);
    pieces = extendString(String.valueOf(quantity), 12);
    total = extendString("$" + String.valueOf(totalPrice), 12);
    String oneLine = item + unit + pieces + total + "\tcard\t" + note;
    printFile(oneLine, fileName);
    JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
    cardTotal += (double) (totalPrice);
   }
   frame4.setVisible(false);
   frame2.setVisible(true);
  }
 }
 class ClickedCheck implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   int checkNum = Validator.validateInt("Check #:", 0, 9999);
   String note = JOptionPane.showInputDialog("备注（选填）: ");
   if (num == 0)
   {
	   String oneLine = item + unit + pieces + total + "\tcheck#" + checkNum + "\t" + note;
	   printFile(oneLine, fileName);
	   JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
	   checkTotal += amount * p;;
   }else if (num == itemPrivate) {
    item = extendString("PL-" + coach + "(" + fromHour + "-" + toHour + ")" + "(" + student + ")", 35);
    unit = extendString("$" + String.valueOf(price), 12);
    pieces = extendString("1", 12);
    total = extendString("$" + String.valueOf(price), 12);
    String oneLine = item + unit + pieces + total + "\tcheck#" + checkNum + "\t" + note;
    printFile(oneLine, fileName);
    JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
    oneLine += "\t" + date + "\tby\t" + cashier;
    printFile(oneLine, dir + "/" + year + "年/" + month + "月/" + coach);
    checkTotal += price;
   } else if (num == itemPass) {
    item = extendString(passType + " Pass#" + passNumber, 35);
    unit = extendString("$" + String.valueOf(price), 12);
    pieces = extendString("1", 12);
    total = extendString("$" + String.valueOf(price), 12);
    String oneLine = item + unit + pieces + total + "\tcheck#" + checkNum + "\t" + note;
    printFile(oneLine, fileName);
    JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
    oneLine = "#" + passNumber + passType + "\t" + "\tto\t" + passName + "(" + passPhone + ")" + "\tcheck#"
      + checkNum + "\ton\t" + date + "\tby " + cashier + "\t" + note;
    printFile(oneLine, passFile.getName());
    String passNum = oneLine.substring(1, 5);
    Integer passNumInt = Integer.parseInt(passNum);
    passList.put(passNumInt, oneLine);
    checkTotal += price;
   } else if (num == itemString) {
    item = extendString(sType + "(" + memberID + ")", 35);
    unit = extendString("$" + String.valueOf(price), 12);
    pieces = extendString("1", 12);
    total = extendString("$" + String.valueOf(price), 12);
    String oneLine = item + unit + pieces + total + "\tcheck#" + checkNum + "\t" + note;
    JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
    printFile(oneLine, fileName);
    checkTotal += price;
   } else {
    item = extendString(items.get(num).name, 35);
    unit = extendString("$" + String.valueOf(items.get(num).price), 12);
    pieces = extendString(String.valueOf(quantity), 12);
    total = extendString("$" + String.valueOf((quantity * items.get(num).price)), 12);
    String oneLine = item + unit + pieces + total + "\tcheck#" + checkNum + "\t" + note;
    printFile(oneLine, fileName);
    JOptionPane.showMessageDialog(null, "以下内容已保存:\n" + oneLine);
    checkTotal += (double) (quantity * items.get(num).price);
   }
   frame4.setVisible(false);
   frame2.setVisible(true);
  }
 }
 /* string */
 class customerBoxAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == customerBox) {
    JComboBox cb = (JComboBox) e.getSource();
    cType = (String) cb.getSelectedItem();
    if (cType == "Non-Member") {
     customerType = 0;
     memberID = "";
    } else if (cType == "Member") {
     customerType = 1;
     memberID = "#" + JOptionPane.showInputDialog("Member ID: ");
    } else {
     customerType = 2;
     memberID = JOptionPane.showInputDialog("Elite Team队员的名字: ");
    }
   }
  }
 }
 class stringBoxAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == stringBox) {
    JComboBox cb = (JComboBox) e.getSource();
    sType = (String) cb.getSelectedItem();
    if (sType == "BG 65") {
     if (customerType == 0) {
      price = 21;
     } else if (customerType == 1) {
      price = 19.5;
     } else {
      price = 18.5;
     }
    }
    if (sType == "BG 65Ti") {
     if (customerType == 0) {
      price = 22;
     } else if (customerType == 1) {
      price = 20.5;
     } else {
      price = 19.5;
     }
    }
    if (sType == "BG 66") {
     if (customerType == 0) {
      price = 22;
     } else if (customerType == 1) {
      price = 20.5;
     } else {
      price = 19.5;
     }
    }
    if (sType == "BG 66UL") {
     if (customerType == 0) {
      price = 26;
     } else if (customerType == 1) {
      price = 24;
     } else {
      price = 23;
     }
    }
    if (sType == "BG 68Ti") {
     if (customerType == 0) {
      price = 26;
     } else if (customerType == 1) {
      price = 24;
     } else {
      price = 23;
     }
    }
    if (sType == "BG 70Pro") {
     if (customerType == 0) {
      price = 25;
     } else if (customerType == 1) {
      price = 23;
     } else {
      price = 22;
     }
    }
    if (sType == "BG 80") {
     if (customerType == 0) {
      price = 27;
     } else if (customerType == 1) {
      price = 25;
     } else {
      price = 24;
     }
    }
    if (sType == "BG 85") {
     if (customerType == 0) {
      price = 28;
     } else if (customerType == 1) {
      price = 26;
     } else {
      price = 25;
     }
    }
    if (sType == "NBG 95") {
     if (customerType == 0) {
      price = 30;
     } else if (customerType == 1) {
      price = 27;
     } else {
      price = 26;
     }
    }
    if (sType == "NBG 98") {
     if (customerType == 0) {
      price = 31;
     } else if (customerType == 1) {
      price = 28;
     } else {
      price = 27;
     }
    }
    if (sType == "NBG 99") {
     if (customerType == 0) {
      price = 32;
     } else if (customerType == 1) {
      price = 29;
     } else {
      price = 28;
     }
    }
    if (sType == "BG 68Ti") {
     if (customerType == 0) {
      price = 26;
     } else if (customerType == 1) {
      price = 24;
     } else {
      price = 23;
     }
    }
    if (sType == "Aerobite") {
     if (customerType == 0) {
      price = 35;
     } else if (customerType == 1) {
      price = 31;
     } else {
      price = 30;
     }
    }
    if (sType == "Labor Only") {
     if (customerType == 0) {
      price = 15;
     } else if (customerType == 1) {
      price = 13.5;
     } else {
      price = 12;
     }
    }
    JOptionPane.showMessageDialog(null, "应收： $" + price + " (低于$20+$0.5)");
   }
  }
 }
 class stringEnterAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   frameString.setVisible(false);
   frame4.setVisible(true);
  }
 }
 class stringBackAction implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   frameString.setVisible(false);
   frame2.setVisible(true);
  }
 }
 /* functions */
 public void makeDir(String name) {
  String path = dir + "/" + name;
  // Creating a File object
  File file = new File(path);
  // Creating the directory
  boolean bool = file.mkdir();
  if (bool) {
   System.out.println("Directory created successfully");
  } else {
   System.out.println("Sorry couldn’t create specified directory");
  }
 }
 public String extendString(String str, int length) {
  for (int i = 0; i < (length - str.length()); i++) {
   str += " ";
  }
  str += "\t";
  return str;
 }
 public void printFile(String print, String file) {
  try (FileWriter write = new FileWriter(file, true);
    BufferedWriter buff = new BufferedWriter(write);
    PrintWriter printer = new PrintWriter(buff)) {
   printer.println(print);
  } catch (IOException ex) {
   ex.printStackTrace();
   JOptionPane.showMessageDialog(null, "Error!");
  }
 }
 public void centerWindow(Window w) {
  Toolkit tk = Toolkit.getDefaultToolkit();
  Dimension d = tk.getScreenSize();
  w.setLocation((d.width - w.getWidth()) / 2, (d.height - w.getHeight()) / 2);
 } // end of centerWindow
 private GridBagConstraints setupConstraints(int gridx, int gridy, int gridwidth, int gridheight, int fill) {
  GridBagConstraints c = new GridBagConstraints();
  c.gridx = gridx;
  c.gridy = gridy;
  c.gridwidth = gridwidth;
  c.gridheight = gridheight;
  c.fill = fill;
  c.insets = new Insets(10, 10, 0, 0);
  return c;
 } // end of setupConstraints
 public void deleteFolder() {
  boolean deleteYear = false, deletedAll = false;
  int currMonth = 1, currYear = 1;
  String toDelMonth = "", toDelYear = "";
  // find the number of the current month selected
  for (int i = 1; i < monthList.length; i++) {
   if (month == monthList[i])
    currMonth = i;
  }
  // find the number of the current year selected
  for (int i = 1; i < monthList.length; i++) {
   if (year == yearList[i])
    currYear = i;
  }
  // if the toDelMonth is within the same year
  if (currMonth - 4 > 0) {
   toDelYear = yearList[currYear];
   toDelMonth = monthList[currMonth - 4];
  }
  // if the toDelMonth is December of the last year
  else if (currMonth - 4 == 0) {
   deleteYear = true;
   toDelYear = yearList[currYear - 1];
   toDelMonth = monthList[12];
  }
  // if the toDelMonth is in the previous year
  else {
   toDelYear = yearList[currYear - 1];
   toDelMonth = monthList[12 + (currMonth - 4)];
  }
  File monthFile = new File(dir + "/" + toDelYear + "年/" + toDelMonth + "月");
  if (monthFile.isDirectory()) {
   File[] inFile = monthFile.listFiles();
   for (int i = 0; i < inFile.length; i++) {
    deletedAll = inFile[i].delete();
    if (!deletedAll)
     System.out.println("Failed to delete " + toDelMonth + "月");
   }
   if (deletedAll)
    if (monthFile.delete())
     System.out.println(toDelMonth + "月 deleted successfully");
  }
  // only able to delete if empty
  if (deleteYear) {
   File yearFile = new File(dir + "/" + toDelYear + "年");
   if (yearFile.delete()) {
    System.out.println(toDelYear + "年 deleted successfully");
   } else {
    System.out.println("Failed to delete " + toDelYear + "年");
   }
  }
 }
 public void readPassFile() {
  String passInfo = "";
  String passNumString = "";
  Integer passNumInt;
  boolean first = true;
  File filePass = new File(dir + "/pass");
  try {
   Scanner sc = new Scanner(filePass);
   while (sc.hasNextLine()) {
    passInfo = sc.nextLine();
    passNumString = passInfo.substring(1, 5);
    passNumInt = Integer.parseInt(passNumString);
    passList.put(passNumInt, passInfo);
    if (first) {
     firstPassNum = passNumInt;
     first = false;
    }
   }
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  }
 }
 public void deletePass() {
  File filePass = new File(dir + "/pass");
  Iterator iterator = passList.entrySet().iterator();
  Map.Entry passElement;
  if (passList.size() > 3) {
   try (FileWriter write = new FileWriter(filePass, false);
     BufferedWriter buff = new BufferedWriter(write);
     PrintWriter printer = new PrintWriter(buff)) {
    for (int i = 0 ; i < passList.size()-3; i++){
     passElement = (Map.Entry)iterator.next();
    }
    while (iterator.hasNext()) {
     passElement = (Map.Entry)iterator.next();
     printer.println(passElement.getValue());
    }
   } catch (IOException ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error!");
   }
  }
 }
 public void findPass() {
  Integer key = Integer.parseInt(JOptionPane.showInputDialog("请输入要查询的Pass号码： "));
  if (passList.containsKey(key)) {
   JOptionPane.showMessageDialog(null, passList.get(key));
  } else {
   JOptionPane.showMessageDialog(null, "该Pass不存在或已过期");
  }
 }
 public static void main(String[] args) {
  new FrontDeskApp();
 }
}


