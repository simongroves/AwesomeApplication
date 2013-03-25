/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.lwuit.*;
import com.sun.lwuit.animations.*;
import com.sun.lwuit.events.*;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.list.DefaultListModel;
import com.sun.lwuit.plaf.*;
import com.sun.lwuit.tree.Tree;
import com.sun.lwuit.tree.TreeModel;
import com.sun.lwuit.util.Resources;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.midlet.*;
import javax.microedition.pim.Contact;
import javax.microedition.pim.ContactList;
import javax.microedition.pim.PIM;
import javax.microedition.pim.PIMException;
//import org.netbeans.microedition.lcdui.pda.PIMBrowser;

public class SwooshMIDlet extends MIDlet implements ActionListener {
    Form mHomeForm;
    Form mAwayForm;
    Form frmSurveys;
    Form frmResources;
    Form frmMessages;
    Form frmSupportMe;
    Form frmAboutYou;
    Form frmAboutUs;
    Form frmChallenges;
    Form frmCalendar;
    Form frmContacts;
    Form frmToDoList;
    Form frmTorch;


    Button btnSurveysFrm;
    Button btnChallengesFrm;
    Button btnResourceFrm;
    Button btnSupportFrm;
    Button mCubeButton;
    Button mRotateButton;
    Button btnCalendarFrm;
    Button btnContactsFrm;
    Button btnToDoFrm;
    Button mFilesButton;
    Button btnAboutYou;
    Button btnAboutUs;
    Button btnTorch;
    

    Command mExitCommand;
    Command mBackCommand;
    
    private Alert debugAlert;
    private List contactList = null;
    private PIM pim = null;
    //private PIMBrowser pimToDo;
    
    private List toDoList = null;
    

    public void startApp() {
      Display.init(this);

      installTheme();

      createUI();
      

      mHomeForm.show();
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSurveysFrm ) {
            frmSurveys.setTransitionInAnimator(Transition3D.createCube(300, false));
            frmSurveys.setTransitionOutAnimator(Transition3D.createCube(300, true));            
            frmSurveys.show();
        }
        else if (ae.getSource() == btnChallengesFrm) {                      
            frmChallenges.show();
        }
        else if (ae.getSource() == btnCalendarFrm) {                      
            frmCalendar.show();
        }
        
        else if (ae.getSource() == btnContactsFrm) {                      
            frmContacts.show();
        }
        else if (ae.getSource() == btnToDoFrm) {                      
            frmToDoList.show();
        }
        else if (ae.getSource() == btnTorch) {                      
            frmTorch.show();
        }
        else if (ae.getSource() == btnResourceFrm) {
            frmResources.setTransitionInAnimator(Transition3D.createCube(300, false));
            frmResources.setTransitionOutAnimator(Transition3D.createCube(300, true));            
            frmResources.show();
        }
        else if (ae.getSource() == mCubeButton) {
            frmMessages.setTransitionInAnimator(Transition3D.createCube(300, false));
            frmMessages.setTransitionOutAnimator(Transition3D.createCube(300, true));
            frmMessages.show();
        }
        else if (ae.getSource() == btnSupportFrm) {
            frmSupportMe.setTransitionInAnimator(Transition3D.createCube(300, false));
            frmSupportMe.setTransitionOutAnimator(Transition3D.createCube(300, true));
            frmSupportMe.show();
        }
         else if (ae.getSource() == btnAboutUs) {
            frmAboutUs.setTransitionInAnimator(Transition3D.createCube(300, false));
            frmAboutUs.setTransitionOutAnimator(Transition3D.createCube(300, true));
            frmAboutUs.show();
        }
         else if (ae.getSource() == btnAboutYou) {
            frmAboutYou.setTransitionInAnimator(Transition3D.createCube(300, false));
            frmAboutYou.setTransitionOutAnimator(Transition3D.createCube(300, true));
            frmAboutYou.show();
        }
        else if ( ae.getSource()==mFilesButton) {
            mAwayForm.setTransitionInAnimator(Transition3D.createCube(300, false));
            mAwayForm.setTransitionOutAnimator(Transition3D.createCube(300, true));
            mAwayForm.show();
        }
        else if (ae.getSource() == mRotateButton) {
            mAwayForm.setTransitionInAnimator(Transition3D.createRotation(300, true));
            mAwayForm.setTransitionOutAnimator(Transition3D.createRotation(300, false));
            mAwayForm.show();
        }
        else if (ae.getSource() == mBackCommand) {
            mHomeForm.show();
        }
        else if (ae.getCommand() == mExitCommand) {
            notifyDestroyed();
        }
    }

    private void installTheme() {
        // This is not the normal way to do this.
        // Usually you load a theme from a file.
        /*
        */
        try {
            Resources r = Resources.open("/res/javaTheme.res");
            UIManager.getInstance().setThemeProps(r.getTheme("javaTheme"));
            //Resources r = Resources.open("/res/businessTheme.res");
            //UIManager.getInstance().setThemeProps(r.getTheme("businessTheme"));
          } catch (IOException ioe) {
            System.out.println("Couldn't load theme.");
         }
        /*UIManager uim = UIManager.getInstance();
        Hashtable ht = new Hashtable();
        ht.put("sel#" + Style.BG_COLOR, "d0d0ed");
        ht.put(Style.BG_COLOR, "ffffff");
        ht.put(Style.FG_COLOR, "000056");
        uim.setThemeProps(ht); */
        
        
    }

    
    private void createUI() {
      // Set up screen for transitions.
      mAwayForm = new Form("...placeholder ...");
      mAwayForm.addComponent(new Label("Choose Back to return to the home screen."));

      mBackCommand = new Command("Back");
      mAwayForm.addCommand(mBackCommand);
      mAwayForm.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
      
      
      frmSurveys = getSurveys();      
      frmChallenges=getChallenges();
      frmResources = getResources();      
      frmMessages = getMessages();      
      frmSupportMe =getSupportMe();      
      frmAboutUs = getAboutUs();        
      frmAboutYou=getAboutYou();
      frmCalendar=getCalendar();
      frmContacts=getContacts();
      frmToDoList=getToDoList();
      frmTorch=getTorch();  
        
        
      
      // Set up main screen.
      mHomeForm = new Form("Today");
      mHomeForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
  
      btnChallengesFrm = new Button("Challenges");
      btnChallengesFrm.addActionListener(this);
      mHomeForm.addComponent(btnChallengesFrm);
        
      btnSurveysFrm = new Button("Surveys");
      btnSurveysFrm.addActionListener(this);
      mHomeForm.addComponent(btnSurveysFrm);

      btnResourceFrm = new Button("Resources");
      btnResourceFrm.addActionListener(this);
      mHomeForm.addComponent(btnResourceFrm);

      mCubeButton = new Button("Messages");
      mCubeButton.addActionListener(this);
      mHomeForm.addComponent(mCubeButton);

      btnSupportFrm = new Button("Support Me");
      btnSupportFrm.addActionListener(this);
      mHomeForm.addComponent(btnSupportFrm);
      
      btnContactsFrm = new Button("Contacts");
      btnContactsFrm.addActionListener(this);
      mHomeForm.addComponent(btnContactsFrm);
      
      btnCalendarFrm = new Button("Calendar");
      btnCalendarFrm.addActionListener(this);
      mHomeForm.addComponent(btnCalendarFrm);
      
      btnToDoFrm = new Button("To Do");
      btnToDoFrm.addActionListener(this);
      mHomeForm.addComponent(btnToDoFrm);
      
      mFilesButton = new Button("Files");
      mFilesButton.addActionListener(this);
      mHomeForm.addComponent(mFilesButton);
      
      btnAboutUs = new Button("About Us");
      btnAboutUs.addActionListener(this);
      mHomeForm.addComponent(btnAboutUs);
      
      btnAboutYou = new Button("About You");
      btnAboutYou.addActionListener(this);
      mHomeForm.addComponent(btnAboutYou);
      
      btnTorch = new Button("Torch");
      btnTorch.addActionListener(this);
      mHomeForm.addComponent(btnTorch);
      
     

      mExitCommand = new Command("Exit");
      mHomeForm.addCommand(mExitCommand);
      mHomeForm.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
    }
    
    public Form getSurveys(){
        if (frmSurveys==null){
             frmSurveys= new Form("Surveys");
            Label sLabel = new Label("Surveys On Board");
            frmSurveys.addComponent(sLabel);

            frmSurveys.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

            RadioButton rb;
            ButtonGroup group = new ButtonGroup();

            rb = new RadioButton("Grilled chicken");
            group.add(rb);
            frmSurveys.addComponent(rb);

            rb = new RadioButton("Filet mignon");
            group.add(rb);
            frmSurveys.addComponent(rb);

            rb = new RadioButton("Mahi mahi");
            group.add(rb);
            frmSurveys.addComponent(rb);

            rb = new RadioButton("Chili");
            group.add(rb);
            frmSurveys.addComponent(rb);

            CheckBox cb;

            cb = new CheckBox("Guacamole");
            frmSurveys.addComponent(cb);

            cb = new CheckBox("Tabasco sauce");
            frmSurveys.addComponent(cb);

            cb = new CheckBox("Mango salsa");
            frmSurveys.addComponent(cb);

            cb = new CheckBox("Mayonnaise");
            frmSurveys.addComponent(cb);

            cb = new CheckBox("Whipped cream");
            frmSurveys.addComponent(cb);

            frmSurveys.addCommand(mBackCommand);
            frmSurveys.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
        }
        return frmSurveys;
    }
    
    public Form getResources(){
        if (frmResources==null){
            frmResources= new Form("Resources");

              List list = new List();
              list.addItem("uno");
              list.addItem("due");
              list.addItem("tre");
              list.addItem("quattro");
              list.addItem("cinque");
              list.addItem("sei");
              frmResources.addComponent(list);

              ComboBox comboBox = new ComboBox(list.getModel());
              frmResources.addComponent(comboBox);
              frmResources.addCommand(mBackCommand);
              frmResources.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.

        }
        return frmResources;
    }
  
    public Form getMessages(){
        if (frmMessages==null){
            // ---------------------------------------------------
            frmMessages= new Form("Messages");
            TextArea area = new TextArea("Peppino");
            frmMessages.addComponent(area);

            TextArea big = new TextArea("On February 24, 1815, the lookout at " + 
                "Notre-Dame de la Garde signalled the arrival of the three-master " +
                "Pharaon, coming from Smyrna, Trieste and Naples.", 5, 20);
            frmMessages.addComponent(big);
            frmMessages.addCommand(mBackCommand);
            frmMessages.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
            // ---------------------------------------

        }
        return frmMessages;
    }
    
    public Form getSupportMe(){
        if (frmSupportMe==null){
            // ---------------------------------------------
            // Form f = ...
            frmSupportMe= new Form("Support Me");
            frmSupportMe.setLayout(new BorderLayout());

            Image bimage = null;
            try {
                bimage = Image.createImage("/images/metaLabSmall.png");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Label bottomText = new Label(bimage);  
            bottomText.setAlignment(Component.CENTER);
            bottomText.setText("Our Logo");
            bottomText.setTextPosition(Component.BOTTOM); 

            Container buttonBar = new Container(new BoxLayout(BoxLayout.X_AXIS));
            buttonBar.addComponent(new Button("Add"));
            buttonBar.addComponent(new Button("Remove"));
            buttonBar.addComponent(new Button("Edit"));
            buttonBar.addComponent(new Button("Send"));
            buttonBar.addComponent(new Button("Exit"));

            frmSupportMe.addComponent(BorderLayout.CENTER, bottomText);
            frmSupportMe.addComponent(BorderLayout.SOUTH, buttonBar);
            frmSupportMe.addCommand(mBackCommand);
            frmSupportMe.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
        }
        return frmSupportMe;
    }
    
    public Form getAboutUs(){
        final Form f = new Form("Event counting");
        if (frmAboutUs==null){            
            f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            
            final Button pushButton = new Button("Tap me!");
            final Label countLabel = new Label(" ");
            pushButton.addActionListener(new ActionListener() {
              private int c = 0;
              public void actionPerformed(ActionEvent ae) {
                c++;
                countLabel.setText(Integer.toString(c));
                f.layoutContainer();
                pushButton.setText("Tapped " + Integer.toString(c) + " times");
                //System.out.println(Integer.toString(c));
              }
            });
            f.addComponent(pushButton);
            f.addCommand(mBackCommand);
            f.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
        }
        return f;
    }
    
    public Form getAboutYou(){
        Form f=new Form("...placeholder ...");
        if (frmAboutYou==null){            
            f.addComponent(new Label("Choose Back to return to the home screen."));

            f.addCommand(mBackCommand);
            f.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
        }
        return f;
    }

    public Form getChallenges() {
        Form f = new Form("Top Challenges");
       
        if (frmChallenges==null){            
            f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            
            
            
            
            
            
            
            
            
            
            
class Node {
    Object[] children;
    String value;
 
    public Node(String value, Object[] children) {
        this.children = children;
        this.value = value;
    }
 
    public String toString() {
        return value;
    }
}
TreeModel model = new TreeModel() {
    Node[] sillyTree =  {
        new Node("Energy Banner: 23 completed: 77 Rewards available", new Node[] {
           new Node("CHALLENGE: Get together a minimum of 5 friends or family, create a Banner with energy-saving awareness message, display in your community", new Node[] {
           }),
           new Node("REWARD: R15 Airtime (only 77 Rewards remaining)", new Node[] {
           }),
           new Node("TO WIN: Send in a photo of you and your friends holding the banner in your community", new Node[] {
           }),
           new Node("CLOSES: 21 June 2013", new Node[] {
           }),
       }),
       new Node("Well Watch", new Node[] {
           new Node("A", new Node[] {
           })
       }),
       new Node("Hear Me, My Friend", new Node[] {
           new Node("A", new Node[] {
           }),
       }),
    };
 
    public Vector getChildren(Object parent) {
        Node n = (Node)parent;
        Object[] nodes;
        if(parent == null) {
            nodes = sillyTree;
        } else {
            nodes = n.children;
        }
        Vector v = new Vector();
        for(int iter = 0 ; iter < nodes.length ; iter++) {
            v.addElement(nodes[iter]);
        }
        return v;
    }
 
    public boolean isLeaf(Object node) {
        Node n = (Node)node;
        return n.children == null || n.children.length == 0;
    }
};
 
f.setLayout(new BorderLayout());
f.addComponent(BorderLayout.CENTER, new Tree(model));
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
           /*
            f.addComponent(area);

            TextArea big = new TextArea("Keep your community clean and win lots of STUFF");
            big.setGrowByContent(true);
            big.setEditable(false);
            f.addComponent(big);
            
              
            List list = new List();
              list.addItem("Street Clean Up");
              list.addItem("Drinking Water Clean-Up");
              list.addItem("Deadland Tidy");
              list.addItem("Tree Rescue");
              f.addComponent(list);              
              
            f.addComponent(new Tree(list));
             */

            f.addCommand(mBackCommand);
            f.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
            f.setTransitionInAnimator(Transition3D.createCube(300, false));
            f.setTransitionOutAnimator(Transition3D.createCube(300, true)); 
        }        
        return f;
    }
    
    public Form getCalendar(){
        Form f = new Form("Calendar");
       
        if (frmCalendar==null){            
             f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            
            Calendar cal = new Calendar();
            f.addComponent(cal);
            f.addCommand(mBackCommand);
            f.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
            f.setTransitionInAnimator(Transition3D.createCube(300, false));
            f.setTransitionOutAnimator(Transition3D.createCube(300, true)); 
        }        
        return f;
    }
        
    
    public Form getContacts(){
         Form f = new Form("Contacts List");
        if (frmContacts==null){
            f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            Label sLabel = new Label("Contacts List appears here");
            f.addComponent(sLabel);
            
            
            
            f.addCommand(mBackCommand);
            f.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
            f.setTransitionInAnimator(Transition3D.createCube(300, false));
            f.setTransitionOutAnimator(Transition3D.createCube(300, true));  
        }
        return f;
    }
    
    public Form fullgetContacts(){
        Form f= new Form("Contacts");
        if (frmContacts==null){
                f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                contactList = new List();
                pim = PIM.getInstance();
                f.addComponent(contactList);

                final TextArea searchField = TextField.create();
                f.addComponent(searchField);

                Button searchButton = new Button("Search");
                searchButton.setPreferredW(f.getWidth() / 2 - 5);
                searchButton.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        populateContactList(searchField.getText());
                    }
                });
                Button clearButton = new Button("Clear");
                clearButton.setPreferredW(f.getWidth() / 2 - 5);
                clearButton.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        searchField.setText("");
                        populateContactList("");
                    }
                });
                Container buttonContainer = new Container();
                buttonContainer.setLayout(new BorderLayout());
                buttonContainer.addComponent(BorderLayout.WEST, searchButton);
                buttonContainer.addComponent(BorderLayout.EAST, clearButton);
                f.addComponent(buttonContainer);

                populateContactList(searchField.getText());

               
                
            f.addCommand(mBackCommand);
            f.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
            f.setTransitionInAnimator(Transition3D.createCube(300, false));
            f.setTransitionOutAnimator(Transition3D.createCube(300, true));  


        }
        return f;
    }   
   private void populateContactList(String searchTerm) {
        contactList.setModel(new DefaultListModel());
        try {
            String[] pimListNames = pim.listPIMLists(PIM.CONTACT_LIST);
            for (int i = 0; i < pimListNames.length; ++i) {
                ContactList cl = (ContactList) pim.openPIMList(
                    PIM.CONTACT_LIST, PIM.READ_ONLY, pimListNames[i]);
                Enumeration items = cl.items(searchTerm);
                while (items.hasMoreElements()) {
                    Contact c = (Contact) items.nextElement();
                    //System.out.println(Contact.NAME_FAMILY + Contact.NAME_GIVEN);
                        contactList.addItem(c.getString(Contact.FORMATTED_NAME, 0));
                    
                }
            }
        }
        catch (PIMException ex) {
            ex.printStackTrace();
        }
        if (contactList.getModel().getSize() == 0) {
            contactList.addItem("No matches");
        }
    }


    public Form getToDoList(){
        Form f = new Form("To Do List");
        if (frmToDoList==null){
            f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            Label sLabel = new Label("To Do List appears here");
            f.addComponent(sLabel);
            
            
            
            f.addCommand(mBackCommand);
            f.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
            f.setTransitionInAnimator(Transition3D.createCube(300, false));
            f.setTransitionOutAnimator(Transition3D.createCube(300, true));  
        }
        return f;
    }
    /*
     public PIMBrowser getPimToDo() {
        if (pimToDo == null) {                                   
            // write pre-init user code here
            pimToDo = new PIMBrowser(getDisplay(), PIM.TODO_LIST);                                      
            pimToDo.setTitle("To Do List");
            pimToDo.addCommand(PIMBrowser.SELECT_PIM_ITEM);
            pimToDo.addCommand(getBackCommand5());
            pimToDo.setCommandListener(this);                                    
            // write post-init user code here
        }                          
        return pimToDo;
    }
    */
    public Form fullgetToDoList(){
        Form f= new Form("To Do List");
        if (frmToDoList==null){
                f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                toDoList = new List();
                pim = PIM.getInstance();
                f.addComponent(toDoList);

                final TextArea searchField = TextField.create();
                f.addComponent(searchField);

                Button searchButton = new Button("Search");
                searchButton.setPreferredW(f.getWidth() / 2 - 5);
                searchButton.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        populateToDoList(searchField.getText());
                    }
                });
                Button clearButton = new Button("Clear");
                clearButton.setPreferredW(f.getWidth() / 2 - 5);
                clearButton.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        searchField.setText("");
                        populateToDoList("");
                    }
                });
                Container buttonContainer = new Container();
                buttonContainer.setLayout(new BorderLayout());
                buttonContainer.addComponent(BorderLayout.WEST, searchButton);
                buttonContainer.addComponent(BorderLayout.EAST, clearButton);
                f.addComponent(buttonContainer);

                populateToDoList(searchField.getText());

               
                
            f.addCommand(mBackCommand);
            f.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
            f.setTransitionInAnimator(Transition3D.createCube(300, false));
            f.setTransitionOutAnimator(Transition3D.createCube(300, true));  


        }
        return f;
    }
    private void populateToDoList(String searchTerm) {
        toDoList.setModel(new DefaultListModel());
        try {
            String[] pimListNames = pim.listPIMLists(PIM.TODO_LIST);
            for (int i = 0; i < pimListNames.length; ++i) {
                ContactList cl = (ContactList) pim.openPIMList(
                    PIM.TODO_LIST, PIM.READ_ONLY, pimListNames[i]);
                Enumeration items = cl.items(searchTerm);
                while (items.hasMoreElements()) {
                    Contact c = (Contact) items.nextElement();
                    toDoList.addItem(c.getString(Contact.FORMATTED_NAME, 0));
                }
            }
        }
        catch (PIMException ex) {
            ex.printStackTrace();
        }
        if (toDoList.getModel().getSize() == 0) {
            toDoList.addItem("No matches");
        }
    }
    
    
    public class FileTreeModel implements TreeModel {             
        public Vector getChildren(Object parent) {
            Vector response = new Vector();
            if(parent == null) {
                response.addElement("Step One");
                response.addElement("Step Two");
                response.addElement("Step Three");
            } else {
                if(!isLeaf(parent)) {
                    response.addElement("The Challenge");
                    response.addElement("Rewards");
                    response.addElement("How");
                    response.addElement("Closes Soon");                    
                }
            }
            return response;
        }
        
        

        public boolean isLeaf(Object node) {
            return ((String)node).indexOf("Child") > -1;
        }
    }
    
    public Form getTorch(){
        Form f = new Form("Torch");
        if (frmTorch==null){
            f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            Label sLabel = new Label("Torch");
            f.addComponent(sLabel);
            /*
            UIManager uim = UIManager.getInstance();
            Hashtable ht = new Hashtable();
            ht.put("sel#" + Style.BG_COLOR, "d0d0ed");
            ht.put(Style.BG_COLOR, "ffffff");
            ht.put(Style.FG_COLOR, "000056");
            uim.setThemeProps(ht);
*/
            
            
            f.addCommand(mBackCommand);
            f.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
            f.setTransitionInAnimator(Transition3D.createCube(300, false));
            f.setTransitionOutAnimator(Transition3D.createCube(300, true));  
        }
        return f;
    }
    
}
