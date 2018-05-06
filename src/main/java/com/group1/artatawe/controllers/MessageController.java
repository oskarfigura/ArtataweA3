package com.group1.artatawe.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oskar Figura (915070)
 */
public class MessageController {

    @FXML
    TextFlow txtAreaMsgs;
    @FXML
    Button btnSend;
    @FXML
    TextArea txtUserMsg;
    @FXML
    TextField txtRecipient;
    @FXML
    ListView listUser;
    private ObservableList<String> usersMessages;
    private Text authorMsgs;
    private Text recipientMsgs;

    public void initialize() {
        txtAreaMsgs.setPrefWidth(600);

        authorMsgs = new Text();
        authorMsgs.setStyle("-fx-fill: RED;-fx-font-weight:normal;");

        recipientMsgs = new Text();
        recipientMsgs.setStyle("-fx-fill: BLUE;-fx-font-weight:normal;");
    }



//    /**
//     * To start the dialog
//     */
//    public void start() {
//        String msg = "Random message";
//        display(msg);
//    }
//
//    /**
//     * Send a message to the GUI
//     * @param msg The message
//     */
//    private void display(String msg) {
//        txtAreaServerMsgs.appendText(msg + "\n"); // append to the Chat Area
//    }

    public void sendMessage() {
        String message = ">> Hello \n";
        String message2 = ">> Hey! \n";
        String message1 = ">> Welcome! \n";

//        authorMsgs.setText(message);
//        recipientMsgs.setText(message2);
//        txtAreaMsgs.getChildren().addAll(authorMsgs, recipientMsgs);
//        authorMsgs.setText(message1);
//
//
//
        String tmp = "Fragment 1.10.32 z \"de Finibus Bonorum et Malorum\", napisanej przez Cycerona w 45 r.p.n.e.\n" +
                "\"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\"\n" +
                "\n" +
                "Tłumaczenie H. Rackhama z 1914 roku\n" +
                "\"But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?\"\n" +
                "\n" +
                "Fragment 1.10.33 z \"de Finibus Bonorum et Malorum\", napisanej przez Cycerona w 45 r.p.n.e.\n" +
                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\"\n" +
                "\n" +
                "Tłumaczenie H. Rackhama z 1914 roku\n" +
                "\"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.\"";
        authorMsgs.setText(tmp);
        txtAreaMsgs.getChildren().add(authorMsgs);
    }
}
