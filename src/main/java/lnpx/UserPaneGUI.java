package lnpx;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lnpx.messages.*;

public class UserPaneGUI extends AnchorPane {

    protected static VBox vBox;
    protected static Label Search;
    protected static TextField SearchBar;
    protected static Label Filters;
    protected static Label NewspaperLabel;
    protected static TextField NewspaperBar;
    protected static Label AuthorLabel;
    protected static TextField AuthorBar;
    protected static Label TopicLabel;
    protected static TextField TopicBar;
    protected static Label CityLabel;
    protected static TextField CityBar;
    protected static Label ErrorLabel;
    protected static Button SearchButton;
    protected static Label RecommendedLabel;
    protected static ArticlesTable RecommendedTable;
    protected static Label TrendingLabel;
    protected static TrendingKeywordsTable TrendingTable;
    protected static Label ChartLabel;
    protected static PieChart PieChart;
    protected static Label ResultLabel;
    protected static ArticlesTable ResultTable;
    protected static Label ArticleoverLabel;
    protected static ArticleOverviewTable SingleArticleTable;
    protected static ObservableList<PieChart.Data> PieChartData;
    protected static Map<String, String> LastUsedFilter;

    public static void addSearchResults(List<Article> list) {

        if (!ResultTable.isVisible()) {
            ResultTable.setVisible(true);
            ResultLabel.setVisible(true);
        }
        ResultTable.setItems(list);
        if (list.isEmpty()) {
            ErrorLabel.setText("No articles found!");
        }

    }

    public static void addRecommendedResults(List<Article> list) {

        RecommendedTable.setItems(list);

    }

    public static void addTrendingKeywords(LinkedHashMap<String, Long> trendMap) {
        
        List<Trend> trKey = new ArrayList<>();
        
        double somma = 0;
        
        for (Map.Entry<String, Long> entry : trendMap.entrySet()) {
            
            Double d = new Double(entry.getValue());
            somma+=d;
            
        }
        
        for (Map.Entry<String, Long> entry : trendMap.entrySet()) {

            String key = entry.getKey();
           
            //Long value = entry.getValue();
            
            Double d = new Double(entry.getValue());
            Double perc = new Double((d/somma)*100);
            
            Long value = (perc.longValue())+1;
            
            Trend t = new Trend(key, value);
            trKey.add(t);

        }
        TrendingTable.setItems(trKey);

        PieChartData = FXCollections.observableArrayList();
        List<PieChart.Data> content = new ArrayList<>();
        for (int i = 0; i < trKey.size(); i++) {
            
            if(i==25){
                break;
            }
            String word = trKey.get(i).getKeyword();
            Long value = trKey.get(i).getPercentage();
            content.add(new PieChart.Data(word, value));

        }
        PieChartData.clear();
        PieChartData.addAll(content);
        PieChart.setData(PieChartData);
        
    }

    private void buildSearchButton() {

        SearchButton.setMnemonicParsing(false);
        SearchButton.setText("Search");

        SearchButton.setOnAction(e -> {

            String keyword = SearchBar.getText();
            String journal = NewspaperBar.getText();
            String author = AuthorBar.getText();
            String topic = TopicBar.getText();
            String country ="";
            String region = "";
            String city = CityBar.getText();

            
                ErrorLabel.setText("");
                HashMap<String, String> filt = new HashMap();
                filt.put("Newspaper", journal);
                filt.put("Author", author);
                filt.put("Topic", topic);
                filt.put("Country",country);
                filt.put("Region", region);
                filt.put("City", city);
                
                LastUsedFilter = new HashMap<>(filt);
                ///LastUsedFilter.put("Keyword", keyword);

                MainClass.searchContent(keyword, filt);

            
            
        });

    }

    private void initializationPane() {

        MainClass.requestTrendingKeywords();
        MainClass.requestRecommendedArticles();

    }

    private void setBehaviour() {

        TrendingTable.setOnMouseClicked(e -> {

            Trend selected = TrendingTable.getSelected();
            if (selected != null) {
                SearchBar.setText(selected.getKeyword());
                NewspaperBar.setText("");
                AuthorBar.setText("");
                TopicBar.setText("");
                CityBar.setText("");
                ErrorLabel.setText("");
            }

        });

        RecommendedTable.setRowFactory(tableView -> {
            final TableRow<Article> row = new TableRow<>();
            row.hoverProperty().addListener((observable) -> {

                final Article art = row.getItem();
                if (row.isHover() && art != null) {

                    List<SingleWordAnalysis> overview = new ArrayList<>();
                    for (Map.Entry<String, Integer> entry : art.keyWordAnalysis.entrySet()) {

                        String key = entry.getKey();
                        int value = entry.getValue();
                        SingleWordAnalysis swa = new SingleWordAnalysis(key, value);
                        overview.add(swa);

                    }
                    SingleArticleTable.setItems(overview);
                }
            });

            return row;

        });

        ResultTable.setRowFactory(tableView -> {
            final TableRow<Article> row = new TableRow<>();
            row.hoverProperty().addListener((observable) -> {

                final Article art = row.getItem();
                if (row.isHover() && art != null) {

                    List<SingleWordAnalysis> overview = new ArrayList<>();
                    for (Map.Entry<String, Integer> entry : art.keyWordAnalysis.entrySet()) {

                        String key = entry.getKey();
                        int value = entry.getValue();
                        SingleWordAnalysis swa = new SingleWordAnalysis(key, value);
                        overview.add(swa);

                    }
                    SingleArticleTable.setItems(overview);
                }
            });

            return row;

        });

        RecommendedTable.setOnMouseClicked(e -> {

            Article selected = RecommendedTable.getSelected();
            if (selected != null) {
                try {
                    Desktop.getDesktop().browse(new URI(selected.Link));
                    RecommendedTable.getSelectionModel().clearSelection();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e2) {
                    e2.printStackTrace();
                }
            }

        });

        ResultTable.setOnMouseClicked(e -> {

            Article selected = ResultTable.getSelected();
            if (selected != null) {

                MainClass.sendViewedArticle(selected.Link, LastUsedFilter);

                try {
                    Desktop.getDesktop().browse(new URI(selected.Link));
                    ResultTable.getSelectionModel().clearSelection();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e2) {
                    e2.printStackTrace();
                }
            }

        });

    }

    public UserPaneGUI() {

        vBox = new VBox();
        Search = new Label();
        SearchBar = new TextField();
        Filters = new Label();
        TopicLabel = new Label();
        TopicBar = new TextField();
        NewspaperLabel = new Label();
        NewspaperBar = new TextField();
        CityLabel = new Label();
        CityBar = new TextField();
        AuthorLabel = new Label();
        AuthorBar = new TextField();
        ErrorLabel = new Label();
        SearchButton = new Button();
        RecommendedLabel = new Label();
        RecommendedTable = new ArticlesTable();
        TrendingLabel = new Label();
        TrendingTable = new TrendingKeywordsTable();
        ChartLabel = new Label();
        ResultLabel = new Label();
        ResultTable = new ArticlesTable();
        ArticleoverLabel = new Label();
        SingleArticleTable = new ArticleOverviewTable();

        ResultLabel.setVisible(false);
        ResultTable.setVisible(false);
        
        PieChartData = FXCollections.observableArrayList();
        PieChart = new PieChart(PieChartData);
        
        buildSearchButton();
        initializationPane();
        setBehaviour();

        setId("AnchorPane");
        setPrefHeight(651.0);
        setPrefWidth(821.0);

        vBox.setLayoutX(7.0);
        vBox.setLayoutY(12.0);
        vBox.setPrefHeight(242.0);
        vBox.setPrefWidth(228.0);

        Search.setPrefHeight(17.0);
        Search.setPrefWidth(125.0);
        Search.setText("Search keyword:");
        Search.setFont(new Font("System Bold", 12.0));

        SearchBar.setPrefHeight(23.0);
        SearchBar.setPrefWidth(228.0);

        Filters.setPrefHeight(17.0);
        Filters.setPrefWidth(97.0);
        Filters.setText("Filters:");
        Filters.setFont(new Font("System Bold", 12.0));

        TopicLabel.setPrefHeight(17.0);
        TopicLabel.setPrefWidth(50.0);
        TopicLabel.setText("Topic");


        CityLabel.setPrefHeight(17.0);
        CityLabel.setPrefWidth(50.0);
        CityLabel.setText("City");

        NewspaperLabel.setPrefHeight(17.0);
        NewspaperLabel.setPrefWidth(82.0);
        NewspaperLabel.setText("Newspaper");

        AuthorLabel.setPrefHeight(17.0);
        AuthorLabel.setPrefWidth(61.0);
        AuthorLabel.setText("Author");

        ErrorLabel.setText("");
        ErrorLabel.setTextFill(Color.RED);

        RecommendedLabel.setLayoutX(258.0);
        RecommendedLabel.setLayoutY(14.0);
        RecommendedLabel.setPrefHeight(17.0);
        RecommendedLabel.setPrefWidth(139.0);
        RecommendedLabel.setText("Recommended articles");
        RecommendedLabel.setFont(new Font("System Bold", 12.0));

        RecommendedTable.setLayoutX(258.0);
        RecommendedTable.setLayoutY(39.0);
        RecommendedTable.setPrefHeight(227.0);
        RecommendedTable.setPrefWidth(258.0);

        TrendingLabel.setLayoutX(549.0);
        TrendingLabel.setLayoutY(14.0);
        TrendingLabel.setPrefHeight(17.0);
        TrendingLabel.setPrefWidth(115.0);
        TrendingLabel.setText("Trending keywords");
        TrendingLabel.setFont(new Font("System Bold", 12.0));

        TrendingTable.setLayoutX(549.0);
        TrendingTable.setLayoutY(39.0);
        TrendingTable.setPrefHeight(227.0);
        TrendingTable.setPrefWidth(258.0);

        ChartLabel.setLayoutX(549.0);
        ChartLabel.setLayoutY(310.0);
        ChartLabel.setPrefHeight(17.0);
        ChartLabel.setPrefWidth(149.0);
        ChartLabel.setText("Trending keywords chart");
        ChartLabel.setFont(new Font("System Bold", 12.0));

        PieChart.setLayoutX(541.0);
        PieChart.setLayoutY(315.0);
        PieChart.setMinHeight(390.0);
        PieChart.setMinWidth(370.0);
        PieChart.setLegendSide(Side.RIGHT);
        PieChart.setMaxWidth(290);
        PieChart.setMaxHeight(200);
        PieChart.setLegendVisible(false);

        ResultLabel.setLayoutX(14.0);
        ResultLabel.setLayoutY(325.0);
        ResultLabel.setPrefHeight(17.0);
        ResultLabel.setPrefWidth(74.0);
        ResultLabel.setText("Results");
        ResultLabel.setFont(new Font("System Bold", 12.0));

        ResultTable.setLayoutX(7.0);
        ResultTable.setLayoutY(344.0);
        ResultTable.setPrefHeight(283.0);
        ResultTable.setPrefWidth(258.0);

        ArticleoverLabel.setLayoutX(278.0);
        ArticleoverLabel.setLayoutY(318.0);
        ArticleoverLabel.setPrefHeight(17.0);
        ArticleoverLabel.setPrefWidth(139.0);
        ArticleoverLabel.setText("Single article overview");
        ArticleoverLabel.setFont(new Font("System Bold", 12.0));

        SingleArticleTable.setLayoutX(278.0);
        SingleArticleTable.setLayoutY(344.0);
        SingleArticleTable.setPrefHeight(283.0);
        SingleArticleTable.setPrefWidth(258.0);

        vBox.getChildren().add(Search);
        vBox.getChildren().add(SearchBar);
        vBox.getChildren().add(Filters);
        vBox.getChildren().add(NewspaperLabel);
        vBox.getChildren().add(NewspaperBar);
        vBox.getChildren().add(AuthorLabel);
        vBox.getChildren().add(AuthorBar);
        vBox.getChildren().add(TopicLabel);
        vBox.getChildren().add(TopicBar);
        vBox.getChildren().add(CityLabel);
        vBox.getChildren().add(CityBar);
        vBox.getChildren().add(SearchButton);
        vBox.getChildren().add(ErrorLabel);

        getChildren().add(vBox);
        getChildren().add(RecommendedLabel);
        getChildren().add(RecommendedTable);
        getChildren().add(TrendingLabel);

        getChildren().add(TrendingTable);
        getChildren().add(ChartLabel);
        getChildren().add(PieChart);
        getChildren().add(ResultLabel);
        getChildren().add(ResultTable);
        getChildren().add(ArticleoverLabel);
        getChildren().add(SingleArticleTable);

    }
}
