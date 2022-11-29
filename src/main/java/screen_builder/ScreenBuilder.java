package screen_builder;

import view_model.Table;
import view_model.UIDataModel;
import ui.Integration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public abstract class ScreenBuilder {
    private UIDataModel dataModel;
    private final Integration view = new Integration();

    public ScreenBuilder(UIDataModel dataModel) {

        this.dataModel = dataModel;
        // Add view to the dataModel observable.
        this.dataModel.addObserver(view);

    }

    public void setDataModel(UIDataModel dataModel) {
        this.dataModel = dataModel;
    }

    /**
     * This method initialize the frame.
     */
    protected void initialization(){
//        try {
//            view.setIconImage(ImageIO.read(new File("java/ui/logo.png")));
//        } catch (IOException e) {
//            throw new RuntimeException("Didn't find Logo!", e);
//        }
    }

    /**
     * This method set the Introduction part of the frame.
     * @return is a long and well-formed string that contain all detail of the user or organization.
     */
    protected String setIntro() {
        return "IntegrationIntro";
    }

    /**
     * This method set the Introduction title.
     * @return is a string that at least have the name of the user or organization.
     */
    protected String setInfoTitle() {
        return "Integration ObjectName";
    }

    /**
     * This method set the Frame name in the top. Default is HR system.
     * @return is a string of the frame name
     */
    protected String setFrameName() {
        return "HR System";
    }

    /**
     * This method set the Left table using Table model.
     * @return a Table that will be present in left JTable.
     */
    protected Table setLeftTable() {
        return new Table(new String[]{"Left Table need to be override!"}, new Object[][]{new Object[]{"Leon"}, new Object[]{"Alice"}}, new Object[]{10,20});
    }

    /**
     * This method set the Right table using Table model.
     * @return a Table that will be present in right JTable.
     */
    protected Table setRightTable(){
        return new Table(new String[]{"Right Table need to be override!"}, new Object[][]{new Object[]{"Leon"}, new Object[]{"Alice"}}, new Object[]{10,20});
    }

    protected String setLeftButtonLabel() {
        return "Create a useless dialog";
    }
    /**
     * This method will be invoked after the left button is clicked.
     */
    protected void customizeLeftButton(){

        JOptionPane.showMessageDialog(view,
                "Left button haven't customized",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
    }

    protected String setRightButtonLabel() {
        return "Add select row into introduction";
    }
    /**
     * This method will be invoked after the right button is clicked.
     */
    protected void customizeRightButton(){
        int[] nums = view.getRightTable().getSelectedRows();
        for (int num : nums){
            String name = (String) dataModel.getRightTable().getData()[num][0];
            String reference = (String) dataModel.getRightTable().getReference()[num];
            dataModel.updateIntro(dataModel.getIntro() + name + reference + " have been selected\n");
        }

    }

    protected JPanel customizeLeftPanel(){
        JPanel jPanel = new JPanel(new GridBagLayout());
        jPanel.add(new JLabel("You need to add customized Left Panel here!"));
        return jPanel;
    }

    protected JPanel customizeRightPanel(){
        JPanel jPanel = new JPanel(new GridBagLayout());
        jPanel.add(new JLabel("You need to add customized Right Panel here!"));
        return jPanel;
    }

    public void addConnection(UIDataModel dataModel) {
        dataModel.addObserver(this.view);
    }
    private void addLeftTable(Table table) {
        view.getLeftTable().setModel(new DefaultTableModel(table.getData(), table.getColumnName()));
    }
    private void addRightTable(Table table) {
        view.getRightTable().setModel(new DefaultTableModel(table.getData(), table.getColumnName()));
    }


    /**
     * get the frame after build.
     * @return Integration frame
     */
    public Integration getView(){

        // Initialize the front data.
        initialization();
        view.setTitle(setFrameName());
        view.setNameLabel(setInfoTitle());
        view.getDetailLabel().setText(setIntro());
        view.getLeftButton().setText(setLeftButtonLabel());
        view.getRightButton().setText(setRightButtonLabel());
        addLeftTable(setLeftTable());
        addRightTable(setRightTable());

        // Plug in Buttons
        view.getLeftButton().addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeLeftButton();
            }
        });
        view.getRightButton().addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeRightButton();
            }
        });

        // Plug in customized Panels
        view.setLeftControllerPanel(customizeLeftPanel());
        view.setRightControllerPanel(customizeRightPanel());
        return view;
    }

    public Integration getIntroOnly(){
        initialization();
        view.setTitle(setFrameName());
        view.setNameLabel(setInfoTitle());
        view.getDetailLabel().setText(setIntro());

        removeDataPanels();
        removeButtons();
        removeControllerPanels();

        view.validate();

        return view;
    }

    public Integration getIntroAndTable(){
        initialization();
        view.setTitle(setFrameName());
        view.setNameLabel(setInfoTitle());
        view.getDetailLabel().setText(setIntro());

        removeButtons();
        removeControllerPanels();

        view.validate();


        return view;
    }
    public Integration getIntroTableAndButton(){
        initialization();
        view.setTitle(setFrameName());
        view.setNameLabel(setInfoTitle());
        view.getDetailLabel().setText(setIntro());

        removeControllerPanels();

        view.validate();

        return view;
    }
    void removeButtons() {
        view.getLeftPanel().remove(view.getLeftButton());
        view.getRightPanel().remove(view.getRightPanel());
        view.getLeftPanel().invalidate();
        view.getRightPanel().invalidate();
    }
    void removeDataPanels() {
        view.getRootPanel().remove(view.getLeftPanel());
        view.getRootPanel().remove(view.getRightPanel());
        view.getRootPanel().invalidate();
    }
    void removeControllerPanels() {
        view.getRootPanel().remove(view.getLeftCustomizedPanel());
        view.getRootPanel().remove(view.getRightCustomizedPanel());
        view.getRootPanel().invalidate();
    }

    public UIDataModel getDataModel() {
        return dataModel;
    }



    //APIs for get the data in the GUI.

    public Object[][] getLeftSelectedRows() {
        JTable jTable= view.getLeftTable();
        if (jTable == null) {
            return null;
        }
        Object[][] result = new Object[jTable.getSelectedRowCount()][jTable.getColumnCount()];
        for (int i= 0; i < result.length; i ++) {
            result[i] = this.dataModel.getLeftTable().getData()[i];
        }
        return result;
    }
    public Object[][] getRightSelectedRows() {
        JTable jTable= view.getRightTable();
        if (jTable == null) {
            return null;
        }
        Object[][] result = new Object[jTable.getSelectedRowCount()][jTable.getColumnCount()];
        for (int i= 0; i < result.length; i ++) {
            result[i] = this.dataModel.getLeftTable().getData()[i];
        }
        return result;
    }


    public JFrame getNotVisible() {
        // TODO: add a notification dialog to show this screen is not visible.
        return null;
    }
}
