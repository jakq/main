package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.eatery.Eatery;

/**
 * Panel containing the list of eateries.
 */
public class EateryListPanel extends UiPart<Region> {
    private static final String FXML = "EateryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EateryListPanel.class);

    @FXML
    private ListView<Eatery> eateryListView;

    public EateryListPanel(ObservableList<Eatery> eateryList) {
        super(FXML);
        eateryListView.setItems(eateryList);
        eateryListView.setCellFactory(listView -> new EateryListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Eatery} using a {@code EateryCard}.
     */
    class EateryListViewCell extends ListCell<Eatery> {
        @Override
        protected void updateItem(Eatery eatery, boolean empty) {
            super.updateItem(eatery, empty);

            if (empty || eatery == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EateryCard(eatery, getIndex() + 1).getRoot());
            }
        }
    }

}
