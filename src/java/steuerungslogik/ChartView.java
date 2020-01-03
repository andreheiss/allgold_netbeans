/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steuerungslogik;

import datenlogik.Inventar;
import datenlogik.Artikel;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;


@ManagedBean
public class ChartView implements Serializable {

    private PieChartModel pieModel;
    private BarChartModel barModel;
    private LineChartModel animatedModel;

    @EJB
    private geschaeftslogik.ArtikelFacade productFacade;
    @EJB
    private geschaeftslogik.InventarFacade inventoryFacade;
    
    @PostConstruct
    public void init(){
       createPieModels();
       createBarModels();
       createAnimatedModels();
    }
    
    public PieChartModel getPieModel(){
      return pieModel;
    }
    
    private void createPieModels(){
      createPieModel();
    }
    
    private void createPieModel(){
      pieModel = new PieChartModel();
      
      List<Artikel> inventoryList = productFacade.allProducts();
      
      Iterator<Artikel> iterator = inventoryList.iterator();
      while(iterator.hasNext()){
        Artikel prod = (Artikel) iterator.next();
        pieModel.set(prod.getName(), getSelectedProductAmount(prod.getArtikelNr()));
      }
      
      pieModel.setTitle("Anteile eines Produktes am Gesamtlagerbestand");
      pieModel.setLegendPosition("e");
      pieModel.setFill(true);
      pieModel.setShowDataLabels(true);
      pieModel.setDiameter(150);
    }
    
    public BarChartModel getBarModel(){
      return barModel;
    }
    
    private void createBarModels(){
      createBarModel();
    }
    
    private void createBarModel(){
      barModel = initBarModel();
      
      barModel.setTitle("Produktinformationen");
      barModel.setLegendPosition("ne");
      
      Axis xAxis = barModel.getAxis(AxisType.X);
      xAxis.setLabel("Produkte");
      
      Axis yAxis = barModel.getAxis(AxisType.Y);
      yAxis.setLabel("Preise / Kosten");
      yAxis.setMin(0);
      yAxis.setMax(6);
      
    }
    
    private BarChartModel initBarModel(){
      BarChartModel model = new BarChartModel();
      
      ChartSeries price = new ChartSeries();
      price.setLabel("Preis");
      
      ChartSeries cost = new ChartSeries();
      cost.setLabel("Herstellkosten");
      
      List<Artikel> inventoryList = productFacade.allProducts();
      
      Iterator<Artikel> iterator = inventoryList.iterator();
      while(iterator.hasNext())
      {
        Artikel prod = (Artikel) iterator.next();
        price.set(prod.getName(), prod.getPreis());
        
        String randomnumber = "";
        Random randomgenerator = new Random();
        
        for(int i=0; i<1; i++)
        {
          int zahl = randomgenerator.nextInt(2);
          randomnumber += zahl;
        }
        for(int i=0; i<1; i++)
        {
          int zahl = randomgenerator.nextInt(99);
          randomnumber += "." + zahl;  
        }
        cost.set(prod.getName(), Float.parseFloat(randomnumber));
        
      }
      model.addSeries(price);
      model.addSeries(cost);
      
      return model;              
    }
    
    public LineChartModel getAnimatedModel(){
       return animatedModel;
    }
    
    private void createAnimatedModels() {
        animatedModel = initLinearModel();
        animatedModel.setTitle("Line Chart");
        animatedModel.setAnimate(true);
        animatedModel.setLegendPosition("se");
        Axis yAxis = animatedModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
 
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }

    public void itemSelect (ItemSelectEvent event){
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public Integer getSelectedProductAmount(java.lang.Integer artikelNr){
       List<Inventar> inventoryList = inventoryFacade.inventoryByProduct(artikelNr);
       
       int amount = 0;
       Iterator<Inventar> iterator = inventoryList.iterator();
       while(iterator.hasNext())
       {
          Inventar inv = (Inventar) iterator.next();
          amount += inv.getStueckzahlIST();
       }
       return amount;
    }
}