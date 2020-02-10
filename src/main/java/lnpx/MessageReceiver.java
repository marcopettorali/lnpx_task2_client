/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnpx;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import lnpx.messages.*;

/**
 *
 * @author rnoce
 */
public class MessageReceiver extends Thread {

    private boolean endExecution;
    private ObjectInputStream ois;
    private DataInputStream dis;
    private Socket socket;

    public MessageReceiver(Socket sock) {

        socket = sock;
        endExecution = false;
        try {
            ois = new ObjectInputStream(sock.getInputStream());
            dis = new DataInputStream(sock.getInputStream());
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }

    }

    private void receiveSignIn() {

        SignInResponseMsg rec = null;
        try {
            rec = (SignInResponseMsg) ois.readObject();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (ClassNotFoundException cfe) {
            System.out.println(cfe.getMessage());
        }

        final int code = rec.getCode();
        Platform.runLater(() -> (SignInPaneGUI.updateSignInPane(code)));

    }

    private void receiveLogin() {

        LoginResponseMsg rec = null;
        try {
            rec = (LoginResponseMsg) ois.readObject();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (ClassNotFoundException cfe) {
            System.out.println(cfe.getMessage());
        }
        final int code = rec.getCode();
        Platform.runLater(() -> (LoginPaneGUI.updateLoginPane(code)));

    }

    private void receiveTrend() {

        TrendResponseMsg res = null;
        try {
            res = (TrendResponseMsg) ois.readObject();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (ClassNotFoundException cfe) {
            System.out.println(cfe.getMessage());
        }
        final LinkedHashMap<String, Long> map = res.getTrendingKeywords();

        Platform.runLater(() -> (UserPaneGUI.addTrendingKeywords(map)));

    }

    private void receiveRecommended() {

        ArticlesResponseMsg res = null;
        try {
            res = (ArticlesResponseMsg) ois.readObject();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (ClassNotFoundException cfe) {
            System.out.println(cfe.getMessage());
        }
        final List<Article> articlesExtracted = res.getArticles();
        Platform.runLater(() -> (UserPaneGUI.addRecommendedResults(articlesExtracted)));

    }

    private void receiveSearchResult() {

        ArticlesResponseMsg res = null;
        try {
            res = (ArticlesResponseMsg) ois.readObject();
            System.out.println(res);
            final List<Article> articlesExtracted = res.getArticles();
            Platform.runLater(() -> (UserPaneGUI.addSearchResults(articlesExtracted)));

        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (ClassNotFoundException cfe) {
            System.out.println(cfe.getMessage());
        }

    }

    private void receiveUsersList() {

        ClientsResponseMsg ret = null;
        try {
            ret = (ClientsResponseMsg) ois.readObject();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (ClassNotFoundException cfe) {
            System.out.println(cfe.getMessage());
        }
        final List<User> list = ret.getClients();
        Platform.runLater(() -> (AdminPaneGUI.addUsersList(list)));
    }

    private void receiveACK() {

        ACKMsg msg = null;
        try {
            msg = (ACKMsg) ois.readObject();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (ClassNotFoundException cfe) {
            System.out.println(cfe.getMessage());
        }

        System.out.println(msg.getMessage());

    }

    public void endReception() {

        endExecution = true;
        try {
            socket.close();
            ois.close();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }

    }

    @Override
    public void run() {

        String command = null;
        while (!endExecution) {

            try {
                command = dis.readUTF();
            } catch (IOException io) {
                System.out.println(io.getMessage());
            }
            System.out.println("Received " + command);
            switch (command) {
                case "SIGN_IN_R":
                    receiveSignIn();
                    break;
                case "LOGIN_R":
                    receiveLogin();
                    break;
                case "TREND_R":
                    receiveTrend();
                    break;
                case "RECOMMENDED_R":
                    receiveRecommended();
                    break;
                case "SEARCH_R":
                    receiveSearchResult();
                    break;
                case "CLIENTS_R":
                    receiveUsersList();
                    break;
                case "ACK":
                    receiveACK();
                    break;
            }
        }
    }
}
