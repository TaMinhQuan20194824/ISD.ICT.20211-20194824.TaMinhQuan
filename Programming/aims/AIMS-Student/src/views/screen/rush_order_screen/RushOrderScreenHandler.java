package views.screen.rush_order_screen;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import common.exception.InvalidDeliveryInfoException;
import controller.PlaceOrderController;
import controller.PlaceRushOrderController;
import entity.invoice.Invoice;
import entity.order.Order;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;

public class RushOrderScreenHandler extends BaseScreenHandler implements Initializable {

  @FXML
  private TextField name;

  @FXML
  private TextField phone;

  @FXML
  private TextField address;

  @FXML
  private TextField instructions;

  @FXML
  private ComboBox<String> province;

  @FXML
  private TextField earliestArrivalTime;

  @FXML
  private TextField latestArrivalTime;

  @FXML
  private Button btnConfirmDelivery;

  private Order order;

  public RushOrderScreenHandler(Stage stage, String screenPath, HashMap messages, Order order) throws IOException {
    super(stage, screenPath);
    this.order = order;
    setRushOrderInfo(messages);

    btnConfirmDelivery.setOnMouseClicked(e -> {
      try {
        confirmToPlaceRushOrder(messages);
      } catch (IOException | InterruptedException | SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    });
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
    name.focusedProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue && firstTime.get()) {
        content.requestFocus(); // Delegate the focus to container
        firstTime.setValue(false); // Variable value changed for future references
      }
    });
    this.province.getItems().addAll(Configs.PROVINCES);
  }

  private void setRushOrderInfo(HashMap<String, String> messages) {
    name.setText(messages.get("name"));
    phone.setText(messages.get("phone"));
    address.setText(messages.get("address"));
    instructions.setText(messages.get("instructions"));
    province.setValue(messages.get("province"));
    earliestArrivalTime.setText("20-12-2021");
    latestArrivalTime.setText("21-12-2021");
  }

  private void confirmToPlaceRushOrder(HashMap messages) throws IOException, InterruptedException, SQLException {
    PlaceRushOrderController placeRushOrderController = new PlaceRushOrderController();
    placeRushOrderController.placeRushOrder();

    // calculate shipping fees
    int shippingFees = getBController().calculateShippingFee(order);
    order.setShippingFees(shippingFees);
    order.setDeliveryInfo(messages);

    // create invoice screen
    Invoice invoice = getBController().createInvoice(order);
    BaseScreenHandler InvoiceScreenHandler = new InvoiceScreenHandler(this.stage, Configs.INVOICE_SCREEN_PATH, invoice);
    InvoiceScreenHandler.setPreviousScreen(this);
    InvoiceScreenHandler.setHomeScreenHandler(homeScreenHandler);
    InvoiceScreenHandler.setScreenTitle("Invoice Screen");
    InvoiceScreenHandler.setBController(getBController());
    InvoiceScreenHandler.show();
  }

  public PlaceRushOrderController getBController() {
    return (PlaceRushOrderController) super.getBController();
  }

}
