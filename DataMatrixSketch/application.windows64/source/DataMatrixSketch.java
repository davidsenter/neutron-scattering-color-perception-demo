import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import org.apache.commons.math3.stat.descriptive.*; 
import java.io.File; 
import java.util.TreeMap; 

import demo.*; 
import org.jcolorbrewer.*; 
import org.jcolorbrewer.ui.*; 
import org.apache.commons.math3.ml.neuralnet.*; 
import org.apache.commons.math3.ml.neuralnet.twod.*; 
import org.apache.commons.math3.ml.neuralnet.twod.util.*; 
import org.apache.commons.math3.ml.neuralnet.oned.*; 
import org.apache.commons.math3.ml.neuralnet.sofm.*; 
import org.apache.commons.math3.ml.neuralnet.sofm.util.*; 
import org.apache.commons.math3.ml.clustering.*; 
import org.apache.commons.math3.ml.clustering.evaluation.*; 
import org.apache.commons.math3.ml.distance.*; 
import org.apache.commons.math3.analysis.*; 
import org.apache.commons.math3.analysis.differentiation.*; 
import org.apache.commons.math3.analysis.integration.*; 
import org.apache.commons.math3.analysis.integration.gauss.*; 
import org.apache.commons.math3.analysis.function.*; 
import org.apache.commons.math3.analysis.polynomials.*; 
import org.apache.commons.math3.analysis.solvers.*; 
import org.apache.commons.math3.analysis.interpolation.*; 
import org.apache.commons.math3.stat.interval.*; 
import org.apache.commons.math3.stat.ranking.*; 
import org.apache.commons.math3.stat.clustering.*; 
import org.apache.commons.math3.stat.*; 
import org.apache.commons.math3.stat.inference.*; 
import org.apache.commons.math3.stat.correlation.*; 
import org.apache.commons.math3.stat.descriptive.*; 
import org.apache.commons.math3.stat.descriptive.rank.*; 
import org.apache.commons.math3.stat.descriptive.summary.*; 
import org.apache.commons.math3.stat.descriptive.moment.*; 
import org.apache.commons.math3.stat.regression.*; 
import org.apache.commons.math3.linear.*; 
import org.apache.commons.math3.*; 
import org.apache.commons.math3.distribution.*; 
import org.apache.commons.math3.distribution.fitting.*; 
import org.apache.commons.math3.complex.*; 
import org.apache.commons.math3.ode.*; 
import org.apache.commons.math3.ode.nonstiff.*; 
import org.apache.commons.math3.ode.events.*; 
import org.apache.commons.math3.ode.sampling.*; 
import org.apache.commons.math3.random.*; 
import org.apache.commons.math3.primes.*; 
import org.apache.commons.math3.optim.*; 
import org.apache.commons.math3.optim.linear.*; 
import org.apache.commons.math3.optim.nonlinear.vector.*; 
import org.apache.commons.math3.optim.nonlinear.vector.jacobian.*; 
import org.apache.commons.math3.optim.nonlinear.scalar.*; 
import org.apache.commons.math3.optim.nonlinear.scalar.gradient.*; 
import org.apache.commons.math3.optim.nonlinear.scalar.noderiv.*; 
import org.apache.commons.math3.optim.univariate.*; 
import org.apache.commons.math3.exception.*; 
import org.apache.commons.math3.exception.util.*; 
import org.apache.commons.math3.fitting.leastsquares.*; 
import org.apache.commons.math3.fitting.*; 
import org.apache.commons.math3.dfp.*; 
import org.apache.commons.math3.fraction.*; 
import org.apache.commons.math3.special.*; 
import org.apache.commons.math3.geometry.*; 
import org.apache.commons.math3.geometry.hull.*; 
import org.apache.commons.math3.geometry.enclosing.*; 
import org.apache.commons.math3.geometry.spherical.twod.*; 
import org.apache.commons.math3.geometry.spherical.oned.*; 
import org.apache.commons.math3.geometry.euclidean.threed.*; 
import org.apache.commons.math3.geometry.euclidean.twod.*; 
import org.apache.commons.math3.geometry.euclidean.twod.hull.*; 
import org.apache.commons.math3.geometry.euclidean.oned.*; 
import org.apache.commons.math3.geometry.partitioning.*; 
import org.apache.commons.math3.geometry.partitioning.utilities.*; 
import org.apache.commons.math3.optimization.*; 
import org.apache.commons.math3.optimization.linear.*; 
import org.apache.commons.math3.optimization.direct.*; 
import org.apache.commons.math3.optimization.fitting.*; 
import org.apache.commons.math3.optimization.univariate.*; 
import org.apache.commons.math3.optimization.general.*; 
import org.apache.commons.math3.util.*; 
import org.apache.commons.math3.genetics.*; 
import org.apache.commons.math3.transform.*; 
import org.apache.commons.math3.filter.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class DataMatrixSketch extends PApplet {




DescriptiveStatistics stats = new DescriptiveStatistics();
DescriptiveStatistics stats2 = new DescriptiveStatistics();
DescriptiveStatistics stats3 = new DescriptiveStatistics();

// data variables
float[][] dataValues;
int[][] dataColors;
float mouseRow, mouseCol;
float mouseValue;
int numRows = 150;
int numCols = 150;
float minValue = Float.NaN;
float maxValue = Float.NaN;


float minValueUp = Float.NaN;
float maxValueUp = Float.NaN;
float minValueDown = Float.NaN;
float maxValueDown = Float.NaN;

// colors
//color c1 = color(0);
//color c2 = color(255);
//color c1 = color(255, 0, 0);
//color c2 = color(0, 255, 0);
int c1, c2, c3, c4, c5;
int borderColor = color(0);
int mouseColor;

int colorScheme = 4;
int dataSet = 0;

int sliderRightX, sliderRightY, sliderRightXUp, sliderRightXDown;
int sliderLeftX, sliderLeftY, sliderLeftXUp, sliderLeftXDown;
int sliderMidX, sliderMidY, sliderMidXUp, sliderMidXDown;

// plot margins
int plotMarginLeft = 10;
int plotMarginRight = 10;
int plotMarginTop = 10;
int plotMarginBottom = 170;

// plot layout variables
int plotLeft;
int plotRight;
int plotTop;
int plotBottom;
int plotHeight;
int plotWidth;

int startX, startY;
int selectionTop = 0;
int selectionLeft = 0;
int selectionBottom = 0;
int selectionRight = 0;

Table table, data, upTable, downTable;

float minX = Float.NaN;
float maxX = Float.NaN;
float minY = Float.NaN;
float maxY = Float.NaN;

float minXUp = Float.NaN;
float maxXUp = Float.NaN;
float minYUp = Float.NaN;
float maxYUp = Float.NaN;

float minXDown = Float.NaN;
float maxXDown = Float.NaN;
float minYDown = Float.NaN;
float maxYDown = Float.NaN;

float deltaX;
float numColumns;
float cellSize;
float cellRow, cellCol;

float deltaXUp, deltaXDown;
float cellRowUp, cellColUp, cellRowDown, cellColDown;

float leftValue, midValue, rightValue;

double[] histogramVals;
Histogram histogram;

int histTop, histBottom, histRight, histLeft;

boolean showGridLines = false;
boolean animating = false;
boolean colorAnimating = true;
boolean scaleAnimating = true;
boolean selecting = false;
boolean inverted = false;

Button animButton, resetButton, exportButton, rightButton, leftButton, colorAnimButton, scaleAnimButton, invertButton;

float animCounter = 0;

PrintWriter output, sliceExtractor;

float zSelector = 17;

float rightX, leftX, midX;
float max2;
int sliderRightXOut, sliderLeftXOut, sliderMidXOut;

TreeMap slices = new TreeMap();

Slice slice, upSlice, downSlice;

public void setup() {
  
  layoutPlot();
  layoutColorSlider();
  loadSlice();
  loadData();
  loadStats();
  loadHistogram();
  //readData();
}

public void draw() {
  fillPlotBackground();
  colorData();
  drawHistogram();
  drawData();
  drawPlotBorder();
  drawColorScale();
  drawColorSlider();
  drawMouseLocation();
  drawButtons();
  if(animating)
    animate();
  if(selecting)
    select();
}

public void loadSlice() {
  data = loadTable("data.csv");
  for (int i = -1; i < 33; i++) {
    sliceExtractor = createWriter("slice.csv");
    
    float zValue = 8.5f + i;
    FloatList x = new FloatList();
    FloatList y = new FloatList();
    FloatList signal = new FloatList();
    
    for (int j = 0; j < data.getRowCount(); j++) {
      if (data.getFloat(j,2) == zValue) {
        x.append(data.getFloat(j,0));
        y.append(data.getFloat(j,1));
        signal.append(data.getFloat(j,3));
      }
    }
    int errorSelect = 0;
    if (zValue == 14.5f)
      errorSelect = 1;
    for (int k = 10 + errorSelect; k < x.size(); k++) {
      sliceExtractor.println(x.get(k) + "," + y.get(k) + "," + signal.get(k));
    }
    
    sliceExtractor.flush();
    sliceExtractor.close();
    
    slices.put((float)i, new Slice(zValue,loadTable("slice.csv")));
    println("Done with " + i);
  }
}

public void loadData() {
  slice = (Slice)slices.get(zSelector);
  table = slice.getTable();
  maxX = slice.getMaxX();
  maxY = slice.getMaxY();
  minX = slice.getMinX();
  minY = slice.getMinY();
  deltaX = slice.getDeltaX();
  minValue = slice.getMinValue();
  maxValue = slice.getMaxValue();
  
  numColumns = ((maxX - minX)/deltaX);
  cellSize = (int)((plotRight - plotLeft)/numColumns);
  
  upSlice = (Slice)slices.get(zSelector + 1);
  upTable = upSlice.getTable();
  maxXUp = upSlice.getMaxX();
  maxYUp = upSlice.getMaxY();
  minXUp = upSlice.getMinX();
  minYUp = upSlice.getMinY();
  deltaXUp = upSlice.getDeltaX();
  minValueUp = upSlice.getMinValue();
  maxValueUp = upSlice.getMaxValue();
  
  downSlice = (Slice)slices.get(zSelector - 1);
  downTable = downSlice.getTable();
  maxXDown = downSlice.getMaxX();
  maxYDown = downSlice.getMaxY();
  minXDown = downSlice.getMinX();
  minYDown = downSlice.getMinY();
  deltaXDown = downSlice.getDeltaX();
  minValueDown = downSlice.getMinValue();
  maxValueDown = downSlice.getMaxValue();
}

public void loadStats() {
  sliderLeftX = (int)(map(slice.getLeftValue(),minValue,maxValue,plotLeft,plotLeft + 300));
  leftValue = slice.getLeftValue();
  sliderRightX = (int)(map(slice.getRightValue(),minValue,maxValue,plotLeft,plotLeft + 300));
  rightValue = slice.getRightValue();
  sliderMidX = (int)(map(slice.getMidValue(),minValue,maxValue,plotLeft,plotLeft + 300));
  midValue = slice.getMidValue();
  
  sliderLeftXUp = (int)(map(upSlice.getLeftValue(),minValueUp,maxValueUp,plotLeft,plotLeft + 300));
  sliderRightXUp = (int)(map(upSlice.getRightValue(),minValueUp,maxValueUp,plotLeft,plotLeft + 300));
  sliderMidXUp = (int)(map(upSlice.getMidValue(),minValueUp,maxValueUp,plotLeft,plotLeft + 300));
  
  sliderLeftXDown = (int)(map(downSlice.getLeftValue(),minValueDown,maxValueDown,plotLeft,plotLeft + 300));
  sliderRightXDown = (int)(map(downSlice.getRightValue(),minValueDown,maxValueDown,plotLeft,plotLeft + 300));
  sliderMidXDown = (int)(map(downSlice.getMidValue(),minValueDown,maxValueDown,plotLeft,plotLeft + 300));
}

public void loadHistogram() {

  histogramVals = slice.getStatsValues();
  histogram = new Histogram("data", histogramVals, 75);
  
  histTop = plotBottom + 10;
  histBottom = histTop + 90;
  histLeft = plotLeft;
  histRight = plotLeft + 300;
}

public void drawHistogram() {
  int top;
  stroke(50);
  strokeWeight(2);
  fill(175);
  rect(histLeft - 2, histTop - 2, histRight + 2, histBottom);
  
  noStroke();
  fill(75);
  int binWidth = (int)((histRight - histLeft)/histogram.getNumBins());
  
  for(int i = 0; i < histogram.getNumBins(); i++) {
    top = (int)map(histogram.getBinCount(i), 0, histogram.getMaxBinCount(), histBottom, histTop);
    rect(histLeft + binWidth*i, top, histLeft + binWidth*i + binWidth, histBottom);
  }
  
  stroke(50, 50);
  strokeWeight(1);
  for(int i = 0; i < histogram.getNumBins(); i++) {
    line(histLeft + binWidth*i, histTop, histLeft + binWidth*i, histBottom);
  }
  fill(200);
  textSize(14);
  textAlign(CENTER);
  text("0", histRight + 15, histBottom);
  text(String.valueOf(histogram.getMaxBinCount()), histRight + 30, histTop + 13);
}

public void drawData() {
  int c = color(0);
  for(int i = 0; i < table.getRowCount(); i++) {
    cellCol = ((table.getFloat(i,0) - minX)/deltaX);
    cellRow = (((table.getFloat(i,1)) - minY)/deltaX);
    
    float n = map(table.getFloat(i,2), minValue, maxValue, plotLeft, plotLeft + 300);
    if (colorScheme ==4) {
      if (n <= sliderLeftX) {
        c = c1;
      }
      if (n > sliderLeftX && n <= sliderLeftX + (sliderMidX - sliderLeftX)/2) {
        float b = norm(n, sliderLeftX, sliderLeftX + (sliderMidX - sliderLeftX)/2);
        c = lerpColor(c1, c2, b);
      }
      if (n > sliderLeftX + (sliderMidX - sliderLeftX)/2 && n <= sliderMidX) {
        float b = norm(n, sliderLeftX + (sliderMidX - sliderLeftX)/2, sliderMidX);
        c = lerpColor(c2, c5, b);
      }
      if (n > sliderMidX && n <= sliderMidX + (sliderRightX - sliderMidX)/2) {
        float b = norm(n, sliderMidX, sliderMidX + (sliderRightX - sliderMidX)/2);
        c = lerpColor(c5, c3, b);
      }
      if (n > sliderMidX + (sliderRightX - sliderMidX)/2 && n <= sliderRightX) {
        float b = norm(n, sliderMidX + (sliderRightX - sliderMidX)/2, sliderRightX);
        c = lerpColor(c3, c4, b);
      }
      if (n > sliderRightX) {
        c = c4;
      }
    }
    else {
      n = norm(map(table.getFloat(i,2), minValue, maxValue, plotLeft, plotLeft + 300),sliderLeftX,sliderMidX);
      if (n < 1) {
        c = lerpColor(c1, c3, n, HSB);
      }
      else {
        n = norm(map(table.getFloat(i,2), minValue, maxValue, plotLeft, plotLeft + 300),sliderMidX,sliderRightX); 
        c = lerpColor(c3, c2, n, HSB);
      }
    }
      
      rectMode(CORNERS);
      noStroke();
      fill(c);
      rect(plotLeft + cellCol*cellSize, plotBottom - 35 - cellRow*cellSize, 
           plotLeft + cellCol*cellSize + cellSize + 2, plotBottom - 35 - cellRow*cellSize + cellSize + 2);
           
      if(selecting && (selectionTop != 0) && inSelection((int)(plotLeft + cellCol*cellSize), (int)(plotBottom - 35 - cellRow*cellSize), (int)(plotLeft + cellCol*cellSize + cellSize + 2), (int)(plotBottom - 35 - cellRow*cellSize + cellSize + 2)) && !(slice.isOutlier(table.getFloat(i,2)))) {
        stats3.addValue(table.getFloat(i,2));
      }
      
      if((mouseX > plotLeft + cellCol*cellSize) && (mouseX < plotLeft + cellCol*cellSize + cellSize + 2) 
        && (mouseY > plotBottom - 35 - cellRow*cellSize) && (mouseY < plotBottom - 35 - cellRow*cellSize + cellSize + 2)) {
        mouseColor = c;
        mouseCol = table.getFloat(i,0);
        mouseRow = table.getFloat(i,1);
        mouseValue = table.getFloat(i,2);
        if (mousePressed && !selecting) {
          selecting = true;
          startX = mouseX;
          startY = mouseY;
        }
      }
   }
   for (int j = 0; j < upTable.getRowCount(); j++) {
    cellColUp = ((upTable.getFloat(j,0) - minXUp)/deltaXUp);
    cellRowUp = (((upTable.getFloat(j,1)) - minYUp)/deltaXUp);
    
    float n = map(upTable.getFloat(j,2), minValueUp, maxValueUp, plotLeft, plotLeft + 300);
    if (colorScheme ==4) {
      if (n <= sliderLeftXUp) {
        c = c1;
      }
      if (n > sliderLeftXUp && n <= sliderLeftXUp + (sliderMidXUp - sliderLeftXUp)/2) {
        float b = norm(n, sliderLeftXUp, sliderLeftXUp + (sliderMidXUp - sliderLeftXUp)/2);
        c = lerpColor(c1, c2, b);
      }
      if (n > sliderLeftXUp + (sliderMidXUp - sliderLeftXUp)/2 && n <= sliderMidXUp) {
        float b = norm(n, sliderLeftXUp + (sliderMidXUp - sliderLeftXUp)/2, sliderMidXUp);
        c = lerpColor(c2, c5, b);
      }
      if (n > sliderMidXUp && n <= sliderMidXUp + (sliderRightXUp - sliderMidXUp)/2) {
        float b = norm(n, sliderMidXUp, sliderMidXUp + (sliderRightXUp - sliderMidXUp)/2);
        c = lerpColor(c5, c3, b);
      }
      if (n > sliderMidXUp + (sliderRightXUp - sliderMidXUp)/2 && n <= sliderRightXUp) {
        float b = norm(n, sliderMidXUp + (sliderRightXUp - sliderMidXUp)/2, sliderRightXUp);
        c = lerpColor(c3, c4, b);
      }
      if (n > sliderRightXUp) {
        c = c4;
      }
    }
    else {
      n = norm(map(upTable.getFloat(j,2), minValueUp, maxValueUp, plotLeft, plotLeft + 300),sliderLeftXUp,sliderMidXUp);
      if (n < 1) {
        c = lerpColor(c1, c3, n, HSB);
      }
      else {
        n = norm(map(upTable.getFloat(j,2), minValueUp, maxValueUp, plotLeft, plotLeft + 300),sliderMidXUp,sliderRightXUp); 
        c = lerpColor(c3, c2, n, HSB);
      }
    }
      
      rectMode(CORNERS);
      noStroke();
      fill(c);
      rect(plotLeft + 30 + 1.5f*cellColUp, plotTop + 265 - 1.5f*cellRowUp, plotLeft + 30 + 1.5f*cellColUp + 1.5f, plotTop + 265 - 1.5f*cellRowUp + 1.5f);
   }
   for (int k = 0; k < downTable.getRowCount(); k++) {
    cellColDown = ((downTable.getFloat(k,0) - minXDown)/deltaXDown);
    cellRowDown = (((downTable.getFloat(k,1)) - minYDown)/deltaXDown);
    
    float n = map(downTable.getFloat(k,2), minValueDown, maxValueDown, plotLeft, plotLeft + 300);
    if (colorScheme ==4) {
      if (n <= sliderLeftXDown) {
        c = c1;
      }
      if (n > sliderLeftXDown && n <= sliderLeftXDown + (sliderMidXDown - sliderLeftXDown)/2) {
        float b = norm(n, sliderLeftXDown, sliderLeftXDown + (sliderMidXDown - sliderLeftXDown)/2);
        c = lerpColor(c1, c2, b);
      }
      if (n > sliderLeftXDown + (sliderMidXDown - sliderLeftXDown)/2 && n <= sliderMidXDown) {
        float b = norm(n, sliderLeftXDown + (sliderMidXDown - sliderLeftXDown)/2, sliderMidXDown);
        c = lerpColor(c2, c5, b);
      }
      if (n > sliderMidXDown && n <= sliderMidXDown + (sliderRightXDown - sliderMidXDown)/2) {
        float b = norm(n, sliderMidXDown, sliderMidXDown + (sliderRightXDown - sliderMidXDown)/2);
        c = lerpColor(c5, c3, b);
      }
      if (n > sliderMidXDown + (sliderRightXDown - sliderMidXDown)/2 && n <= sliderRightXDown) {
        float b = norm(n, sliderMidXDown + (sliderRightXDown - sliderMidXDown)/2, sliderRightXDown);
        c = lerpColor(c3, c4, b);
      }
      if (n > sliderRightXDown) {
        c = c4;
      }
    }
    else {
      n = norm(map(downTable.getFloat(k,2), minValueDown, maxValueDown, plotLeft, plotLeft + 300),sliderLeftXDown,sliderMidXDown);
      if (n < 1) {
        c = lerpColor(c1, c3, n, HSB);
      }
      else {
        n = norm(map(downTable.getFloat(k,2), minValueDown, maxValueDown, plotLeft, plotLeft + 300),sliderMidXDown,sliderRightXDown); 
        c = lerpColor(c3, c2, n, HSB);
      }
    }
      
      rectMode(CORNERS);
      noStroke();
      fill(c);
      rect(plotLeft + 500 + 1.5f*cellColDown, plotBottom - 1.5f*cellRowDown, plotLeft + 500 + 1.5f*cellColDown + 1.5f, plotBottom - 1.5f*cellRowDown + 1.5f);
   }   
}

public void select() {
  stroke(230);
  strokeWeight(2);
  noFill();
  rect(startX, startY, mouseX, mouseY);
}

public void selectionStats() {
  sliderLeftX = (int)(map((float)stats3.getPercentile(25),minValue,maxValue,plotLeft,plotLeft + 300));
  leftValue = (float)stats3.getPercentile(25);
  sliderRightX = (int)(map((float)stats3.getPercentile(99),minValue,maxValue,plotLeft,plotLeft + 300));
  rightValue = (float)stats3.getPercentile(99);
  sliderMidX = (int)(map((float)stats3.getPercentile(85),minValue,maxValue,plotLeft,plotLeft + 300));
  midValue = (float)stats3.getPercentile(85);
}

public void mouseReleased() {
  if (selecting) {
    stats3.clear();
    selectionLeft = startX;
    selectionTop = startY;
    selectionRight = mouseX;
    selectionBottom = mouseY;
    drawData();
    if (stats3.getN() != 0)
      selectionStats();
    selecting = false;
    drawData();
  }
}

public boolean inSelection(int x1, int y1, int x2, int y2) {
  boolean result = false;
  if ((selectionTop <= selectionBottom) && (selectionLeft <= selectionRight)) {
    if ((x1 >= selectionLeft) && (y1 >= selectionTop) && (x2 <= selectionRight) && (y2 <= selectionBottom))
      result = true;
  }
  else {
    if ((selectionTop >= selectionBottom) && (selectionLeft <= selectionRight)) {
      if ((x1 >= selectionLeft) && (y1 >= selectionBottom) && (x2 <= selectionRight) && (y2 <= selectionTop))
        result = true;
    }
    else {
      if ((selectionTop >= selectionBottom) && (selectionLeft >= selectionRight)) {
        if ((x1 >= selectionRight) && (y1 >= selectionBottom) && (x2 <= selectionLeft) && (y2 <= selectionTop))
          result = true;
      }
      else {
        if ((selectionTop <= selectionBottom) && (selectionLeft >= selectionRight)) {
          if((x1 >= selectionRight) && (y1 >= selectionTop) && (x2 <= selectionLeft) && (y2 <= selectionBottom))
            result = true;
        }
      }
    }
  }
  return result;
}

public void layoutColorSlider() {
  sliderRightX = plotLeft + 300;
  sliderRightY = plotBottom + 131;
  
  sliderLeftX = plotLeft;
  sliderLeftY = sliderRightY;
  
  sliderMidX = plotLeft + 150;
  sliderMidY = sliderRightY - 16;
}

public void drawColorSlider() {
  
  //right slider
  fill(255);
  noStroke();
  rect(sliderRightX - 5,sliderRightY,sliderRightX + 5,sliderRightY + 10);
  triangle(sliderRightX - 5, sliderRightY, sliderRightX, sliderRightY - 10, sliderRightX + 5, sliderRightY);
  
  if (mousePressed && (Math.abs(sliderRightX - pmouseX) <= 15) && (Math.abs(sliderRightY - pmouseY) <= 10) 
     && (mouseX < plotLeft + 300) && (mouseX > plotLeft)) {
      moveColorSlider(2);
  }
    
  //left slider
  rect(sliderLeftX - 5, sliderLeftY, sliderLeftX + 5, sliderLeftY + 10);
  triangle(sliderLeftX - 5, sliderLeftY, sliderLeftX, sliderLeftY - 10, sliderLeftX + 5, sliderLeftY);
  
  if (mousePressed && (Math.abs(sliderLeftX - pmouseX) <= 15) && (Math.abs(sliderLeftY - pmouseY) <= 10) 
    && (mouseX < plotLeft + 300) && (mouseX > plotLeft)) {
      moveColorSlider(1);
  }
  
  // mid slider
  stroke(0);
  ellipseMode(CENTER);
  ellipse(sliderMidX, sliderMidY, 10, 10);
  noFill();
  if (mousePressed && (Math.abs(sliderMidX - pmouseX) <= 5) && (Math.abs(sliderMidY - pmouseY) <= 5) 
    && (mouseX < plotLeft + 300) && (mouseX > plotLeft)) {
      moveColorSlider(3);
  }  
}

public void moveColorSlider(int slider) {
  if (slider == 1 && mouseX <= sliderRightX && mouseX <= sliderMidX) {
    sliderLeftX = mouseX;
    leftValue = map(sliderLeftX, plotLeft, plotLeft+300, minValue, maxValue);
  }
  if (slider == 2 && mouseX >= sliderLeftX && mouseX >= sliderMidX) {
    sliderRightX = mouseX;
    rightValue = map(sliderRightX, plotLeft, plotLeft + 300, minValue, maxValue);
  }
  if (slider == 3 && mouseX >= sliderLeftX && mouseX <= sliderRightX) {
    sliderMidX = mouseX;
  }
}

public void drawColorScale() {
  strokeWeight(1);
  int barRight = plotLeft + 300;
  int barTop = plotBottom + 110;
  int barBottom = barTop + 10;
  
  if(colorScheme == 4) {
    for (int k = plotLeft; k < sliderLeftX; k++) {
      stroke(c1);
      line(k, barTop, k, barBottom);
    }
    for (int m = sliderLeftX; m < sliderLeftX + (sliderMidX - sliderLeftX)/2; m++) {
      float n = norm(m, sliderLeftX, sliderLeftX + (sliderMidX - sliderLeftX)/2);
      int c = lerpColor(c1, c2, n);
      stroke(c);
      line(m, barTop, m, barBottom);
    }
    for (int m = sliderLeftX + (sliderMidX - sliderLeftX)/2; m < sliderMidX; m++) {
      float n = norm(m, sliderLeftX + (sliderMidX - sliderLeftX)/2, sliderMidX);
      int c = lerpColor(c2, c5, n);
      stroke(c);
      line(m, barTop, m, barBottom);
    }
    for (int m = sliderMidX; m < sliderMidX + (sliderRightX - sliderMidX)/2; m++) {
      float n = norm(m, sliderMidX, sliderMidX + (sliderRightX - sliderMidX)/2);
      int c = lerpColor(c5, c3, n);
      stroke(c);
      line(m, barTop, m, barBottom);
    }
    for (int q = sliderMidX + (sliderRightX - sliderMidX)/2; q < sliderRightX; q++) {
      float n = norm(q, sliderMidX + (sliderRightX - sliderMidX)/2, sliderRightX);
      int c = lerpColor(c3, c4, n);
      stroke(c);
      line(q, barTop, q, barBottom);
    }
    for (int r = sliderRightX; r < barRight; r++) {
      stroke(c4);
      line(r, barTop, r, barBottom);
    }
  }
  else {
    for (int k = plotLeft; k < sliderLeftX; k++) {
      stroke(c1);
      line(k, barTop, k, barBottom);    
    }
    for (int i = sliderLeftX; i < sliderMidX; i++) {      
      float n = norm(i, sliderLeftX, sliderMidX);
      int c = lerpColor(c1, c3, n, HSB);
      stroke(c);
      line(i, barTop, i, barBottom);
    }
    for (int j = sliderMidX; j < sliderRightX; j++) {      
      float n = norm(j, sliderMidX, sliderRightX);
      int c = lerpColor(c3, c2, n, HSB);
      stroke(c);
      line(j, barTop, j, barBottom);
    }
    for (int m = sliderRightX; m < plotLeft + 300; m++) {
      stroke(c2);
      line(m, barTop, m, barBottom);    
    }
  }
  
  stroke(0);
  strokeWeight(1);
  rect(plotLeft, barTop, barRight, barBottom);
  
  fill(200);
  textSize(14);
  textAlign(LEFT);
  text(String.valueOf(leftValue), plotLeft, barBottom + 36);
  textAlign(RIGHT);
  text(String.valueOf(rightValue), barRight, barBottom + 36);
}

public void fillPlotBackground() {
  background(100);
  // fill plot area with near white
  fill(175);
  noStroke();
  rectMode(CORNERS);
  rect(plotLeft, plotTop, plotRight, plotBottom);
}

public void drawPlotBorder() {
  // draw plot area border
  stroke(30);
  strokeWeight(2);
  noFill();
  rectMode(CORNERS);
  strokeCap(SQUARE);
  rect(plotLeft, plotTop, plotRight, plotBottom);  
}

public void layoutPlot() {
  // layout plot boundaries
  plotWidth = width - (plotMarginLeft + plotMarginRight);
  plotHeight = height - (plotMarginTop + plotMarginBottom);

  plotLeft = plotMarginLeft;
  plotRight = plotLeft + plotWidth;
  plotTop = plotMarginTop;
  plotBottom = plotTop + plotHeight;
}
  
public void colorData() {  
  //set color scheme
  
  if (colorScheme < 1) {
    colorScheme = 4;
  }
  if (colorScheme > 4) {
    colorScheme = 1;
  }
  if (colorScheme == 1) {
    //final ColorPaletteChooserDialog dialog = new ColorPaletteChooserDialog();
    //dialog.show();
    //if(dialog.wasOKPressed()) {
    //    java.awt.Color c = dialog.getColor();
    //}
    if (!inverted) {
      c1 = color(255);
      c2 = color(0);
      c3 = lerpColor(c1, c2, 0.5f);
    }
    else {
      c2 = color(255);
      c1 = color(0);
      c3 = lerpColor(c1, c2, 0.5f);
    }
  }
  if (colorScheme == 2) {
    if (!inverted) {
      c1 = color(222,235,247);
      c2 = color(49,130,189);
      c3 = lerpColor(c1, c2, 0.5f);
    }
    else {
      c2 = color(222,235,247);
      c1 = color(49,130,189);
      c3 = lerpColor(c1, c2, 0.5f);
    }
  }
  if (colorScheme == 3) {
    if (!inverted) {
      c1 = color(0,0,255);
      c2 = color(255,0,0);
      //c4 = color(255,255,255);
      c3 = lerpColor(c1, c2, 0.5f, HSB);
    }
    else {
      c2 = color(0,0,255);
      c1 = color(255,0,0);
      //c4 = color(255,255,255);
      c3 = lerpColor(c1, c2, 0.5f, HSB);
    }
  }
  if (colorScheme == 4) {
    if (!inverted) {
      c1 = color(0,0,0);
      c2 = color(122,34,23);
      c3 = color(215,171,80);
      c4 = color(255,255,255);
      c5 = lerpColor(c2, c3, 0.5f);
    }
    else {
      c4 = color(0,0,0);
      c3 = color(122,34,23);
      c2 = color(215,171,80);
      c1 = color(255,255,255);
      c5 = lerpColor(c2, c3, 0.5f);
    }
  }
}

public void drawButtons() {
  
  int colorAnimButtonLeft = plotRight - 110;
  int colorAnimButtonTop = height - 20;
  int colorAnimButtonRight = plotRight;
  int colorAnimButtonBottom = colorAnimButtonTop + 16;
  int colorAnimButtonTextSize = 9;
  String colorAnimButtonText;
  
  if (colorAnimating)
    colorAnimButtonText = "Change Color Map ON";
  else
    colorAnimButtonText = "Change Color Map OFF";
  
  colorAnimButton = new Button(colorAnimButtonText, colorAnimButtonLeft, colorAnimButtonTop, colorAnimButtonRight, colorAnimButtonBottom, colorAnimButtonTextSize);
  colorAnimButton.drawButton();
  
  int scaleAnimButtonLeft = plotRight - 110;
  int scaleAnimButtonTop = colorAnimButtonTop - 21;
  int scaleAnimButtonRight = plotRight;
  int scaleAnimButtonBottom = colorAnimButtonTop - 5;
  int scaleAnimButtonTextSize = 9;
  String scaleAnimButtonText;
  
  if (scaleAnimating)
    scaleAnimButtonText = "Change Scale ON";
  else
    scaleAnimButtonText = "Change Scale OFF";
  
  scaleAnimButton = new Button(scaleAnimButtonText, scaleAnimButtonLeft, scaleAnimButtonTop, scaleAnimButtonRight, scaleAnimButtonBottom, scaleAnimButtonTextSize);
  scaleAnimButton.drawButton();
  
  fill(200);
  textSize(9);
  textAlign(CENTER);
  text("Animation Settings:", plotRight - 110, scaleAnimButtonTop - 16, plotRight, scaleAnimButtonTop - 5);
  
  int animButtonLeft = plotLeft + 375;
  int animButtonTop = plotBottom + 10;
  int animButtonRight = animButtonLeft + 110;
  int animButtonBottom = animButtonTop + 20;
  int animButtonTextSize = 11;
  String animButtonText;
  
  if (animating)
    animButtonText = "Animation ON";
  else
    animButtonText = "Animation OFF";
  
  animButton = new Button(animButtonText, animButtonLeft, animButtonTop, animButtonRight, animButtonBottom, animButtonTextSize);
  animButton.drawButton();
  
  int resetButtonLeft = animButtonLeft;
  int resetButtonTop = animButtonBottom + 10;
  int resetButtonRight = resetButtonLeft + 110;
  int resetButtonBottom = resetButtonTop + 20;
  int resetButtonTextSize = 11;
  String resetButtonText = "Reset Scale";

  resetButton = new Button(resetButtonText, resetButtonLeft, resetButtonTop, resetButtonRight, resetButtonBottom, resetButtonTextSize);
  resetButton.drawButton();
  
  int invertButtonLeft = resetButtonLeft;
  int invertButtonTop = resetButtonBottom + 10;
  int invertButtonRight = invertButtonLeft + 110;
  int invertButtonBottom = invertButtonTop + 20;
  int invertButtonTextSize = 11;
  String invertButtonText = "Invert Scale";
  
  invertButton = new Button(invertButtonText, invertButtonLeft, invertButtonTop, invertButtonRight, invertButtonBottom, invertButtonTextSize);
  invertButton.drawButton();
  
  int exportButtonLeft = invertButtonLeft;
  int exportButtonTop = invertButtonBottom + 10;
  int exportButtonRight = exportButtonLeft + 110;
  int exportButtonBottom = exportButtonTop + 20;
  int exportButtonTextSize = 11;
  String exportButtonText = "Export Color Map";
  
  exportButton = new Button(exportButtonText, exportButtonLeft, exportButtonTop, exportButtonRight, exportButtonBottom, exportButtonTextSize);
  exportButton.drawButton();  
  
  int leftButtonLeft = exportButtonLeft;
  int leftButtonTop = exportButtonBottom + 10;
  int leftButtonRight = leftButtonLeft + 50;
  int leftButtonBottom = leftButtonTop + 30;
  int leftButtonTextSize = 13;
  String leftButtonText = "<--";
  
  leftButton = new Button(leftButtonText, leftButtonLeft, leftButtonTop, leftButtonRight, leftButtonBottom, leftButtonTextSize);
  leftButton.drawButton();
  
  int rightButtonLeft = leftButtonRight + 10;
  int rightButtonTop = exportButtonBottom + 10;
  int rightButtonRight = rightButtonLeft + 50;
  int rightButtonBottom = rightButtonTop + 30;
  int rightButtonTextSize = 13;
  String rightButtonText = "-->";
  
  rightButton = new Button(rightButtonText, rightButtonLeft, rightButtonTop, rightButtonRight, rightButtonBottom, rightButtonTextSize);
  rightButton.drawButton();
}

public void keyPressed() {
  if (keyCode == LEFT) {
    colorScheme--;
    draw();
  } else if (keyCode == RIGHT) {
    colorScheme++;
    draw();
  }
  else if (key == 'r') {
    minValue = Float.NaN;
    maxValue = Float.NaN;
    selectionTop = 0;
    selectionBottom = 0;
    selectionRight = 0;
    selectionLeft = 0;
    stats3 = new DescriptiveStatistics();
    loadData();
    loadStats();
    draw();
  }
  else if (key == 'e') {
    selectOutput("Choose output file: ", "export");
  }
  else if (key == 'p') {
    animating = true;
  }
  else if (key == 's') {
    animating = false;
  }
  else if (keyCode == UP) {
    zSelector++;
    if (zSelector > 31) {
      zSelector = 31;
      return;
    }
    loadData();
    loadStats();
    loadHistogram();
  }
  else if (keyCode == DOWN) {
    zSelector--;
    if (zSelector < 0) {
      zSelector = 0;
      return;
    }
    loadData();
    loadStats();
    loadHistogram();
  }
  else if (key == 'i') {
    inverted = !inverted;
  }
}

public void mouseClicked() {
  if(animButton.isHovered()) {
    animating = !animating;
  }
  if(resetButton.isHovered()) {
    minValue = Float.NaN;
    maxValue = Float.NaN;
    selectionTop = 0;
    selectionBottom = 0;
    selectionRight = 0;
    selectionLeft = 0;
    stats3 = new DescriptiveStatistics();
    loadData();
    loadStats();
    draw();
  }
  if(exportButton.isHovered()) {
    selectOutput("Choose color map output file: ", "export");
  }
  if(leftButton.isHovered()) {
    colorScheme--;
    draw();
  }
  if(rightButton.isHovered()) {
    colorScheme++;
    draw();
  }
  if(colorAnimButton.isHovered()) {
    colorAnimating = !colorAnimating;
  }
  if(scaleAnimButton.isHovered()) {
    scaleAnimating = !scaleAnimating;
  }
  if(invertButton.isHovered()) {
    inverted = !inverted;
  }
}

public void drawMouseLocation() {
  int colorboxLeft = plotRight - 75;
  int colorboxRight = plotRight;
  int colorboxTop = plotBottom + 15;
  int colorboxBottom = plotBottom + 75;
  stroke(50);
  strokeWeight(2);
  fill(100);
  
  line(plotLeft + 352, plotBottom, plotLeft + 352, height);
  
  rect(colorboxLeft,colorboxTop,colorboxRight,colorboxBottom);
  if ((mouseX > plotLeft) && (mouseX < plotRight) && (mouseY > plotTop) && (mouseY < plotBottom)) {
    fill(mouseColor);
    
    rect(colorboxLeft,colorboxTop,colorboxRight,colorboxBottom);
    
    fill(200);
    textSize(14);
    textAlign(RIGHT);
    text("x = " + String.valueOf(mouseCol), colorboxLeft - 5, colorboxTop + 7);
    text("y = " + String.valueOf(mouseRow), colorboxLeft - 5, colorboxBottom - 33);
    text("z = " + String.valueOf(8.5f + zSelector), colorboxLeft - 5, colorboxBottom - 15);
    text("signal = " + String.valueOf(mouseValue), colorboxLeft - 5, colorboxBottom + 5);
    //text("(r, g, b) = (" + String.valueOf((int)red(mouseColor)) + ", " + String.valueOf((int)green(mouseColor)) 
    //+ ", " + String.valueOf((int)blue(mouseColor)) + ")", colorboxLeft - 5, colorboxTop + 37);
  }
}

public void export(File selection) {
  if (selection != null) {
    output = createWriter(selection.getAbsolutePath());
    
    int barRight = plotLeft + 300;
    int barTop = plotBottom + 110;
    int barBottom = barTop + 10;
    
    max2 = norm(slice.getStatsAbsMax(), minValue, maxValue);
    
    sliderRightXOut = (int)map(sliderRightX, plotLeft, (plotLeft + 300)*max2, plotLeft, plotLeft + 300);
    sliderLeftXOut = (int)map(sliderLeftX, plotLeft, (plotLeft + 300)*max2, plotLeft, plotLeft + 300);
    sliderMidXOut = (int)map(sliderMidX, plotLeft, (plotLeft + 300)*max2, plotLeft, plotLeft + 300);
    
    if(colorScheme == 4) {
      for (int k = plotLeft; k < sliderLeftXOut; k++) {
        output.println((int)red(c1) + " " + (int)green(c1) + " " + (int)blue(c1));
      }
      for (int m = sliderLeftXOut; m < sliderLeftXOut + (sliderMidXOut - sliderLeftXOut)/2; m++) {
        float n = norm(m, sliderLeftXOut, sliderLeftXOut + (sliderMidXOut - sliderLeftXOut)/2);
        int c = lerpColor(c1, c2, n);
        output.println((int)red(c) + " " + (int)green(c) + " " + (int)blue(c));
      }
      for (int m = sliderLeftXOut + (sliderMidXOut - sliderLeftXOut)/2; m < sliderMidXOut; m++) {
        float n = norm(m, sliderLeftXOut + (sliderMidXOut - sliderLeftXOut)/2, sliderMidXOut);
        int c = lerpColor(c2, c5, n);
        output.println((int)red(c) + " " + (int)green(c) + " " + (int)blue(c));
      }
      for (int m = sliderMidXOut; m < sliderMidXOut + (sliderRightXOut - sliderMidXOut)/2; m++) {
        float n = norm(m, sliderMidXOut, sliderMidXOut + (sliderRightXOut - sliderMidXOut)/2);
        int c = lerpColor(c5, c3, n);
        output.println((int)red(c) + " " + (int)green(c) + " " + (int)blue(c));
      }
      for (int q = sliderMidXOut + (sliderRightXOut - sliderMidXOut)/2; q < sliderRightXOut; q++) {
        float n = norm(q, sliderMidXOut + (sliderRightXOut - sliderMidXOut)/2, sliderRightXOut);
        int c = lerpColor(c3, c4, n);
        output.println((int)red(c) + " " + (int)green(c) + " " + (int)blue(c));
      }
      for (int r = sliderRightXOut; r < barRight; r++) {
        output.println((int)red(c4) + " " + (int)green(c4) + " " + (int)blue(c4));
      }
    }
    else {
      for (int k = plotLeft; k < sliderLeftXOut; k++) {
        output.println((int)red(c1) + " " + (int)green(c1) + " " + (int)blue(c1));  
      }
      for (int i = sliderLeftXOut; i < sliderMidXOut; i++) {      
        float n = norm(i, sliderLeftXOut, sliderMidXOut);
        int c = lerpColor(c1, c3, n, HSB);
        output.println((int)red(c) + " " + (int)green(c) + " " + (int)blue(c));
      }
      for (int j = sliderMidXOut; j < sliderRightXOut; j++) {      
        float n = norm(j, sliderMidXOut, sliderRightXOut);
        int c = lerpColor(c3, c2, n, HSB);
        output.println((int)red(c) + " " + (int)green(c) + " " + (int)blue(c));
      }
      for (int m = sliderRightXOut; m < plotLeft + 300; m++) {
        output.println((int)red(c2) + " " + (int)green(c2) + " " + (int)blue(c2)); 
      }
    }
      
      output.flush();
      output.close();
  }
}

public void animate() {
  animCounter++;
  if (animCounter % 3 == 0.0f && scaleAnimating) {
    sliderRightX = sliderMidX + (int)(Math.abs(Math.sin(animCounter))*(300 - (sliderMidX - plotLeft)));
    sliderLeftX = sliderMidX - (int)(Math.abs(Math.sin(animCounter))*(sliderMidX - plotLeft));
  }
  if (animCounter % 201 == 0.0f && colorAnimating) {
    colorScheme++;
  }
  println(animCounter);
}

public void mouseWheel(MouseEvent event) {
  float e = event.getCount();
  if (e < 0) {
    zSelector++;
    if (zSelector > 31) {
      zSelector = 31;
      return;
    }
    loadData();
    loadStats();
    loadHistogram();
  }
  else {
    zSelector--;
    if (zSelector < 0) {
      zSelector = 0;
      return;
    }
    loadData();
    loadStats();
    loadHistogram();    
  }
}
public class Button {
  
  private int topX, topY, bottomX, bottomY, textSize;
  private boolean mouseover;
  String text;
  private int buttonColor;

  public Button(String text, int topX, int topY, int bottomX, int bottomY, int textSize) {
    this.text = text;
    this.topX = topX;
    this.topY = topY;
    this.bottomX = bottomX;
    this.bottomY = bottomY;
    this.textSize = textSize;
  }
  
  public void drawButton() {
    
    buttonColor = color(180);
    
    if(this.isHovered())
      buttonColor = color(215);
  
  strokeWeight(1);
  fill(buttonColor);
  rect(topX, topY, bottomX, bottomY, 3);
  
  fill(0);
  textSize(textSize);
  textAlign(CENTER);
  int space = (int)((bottomY - topY)/6);
  text(text, topX, topY + space, bottomX, bottomY);
  }
  
  public boolean isHovered() {
    if((mouseX > topX) && (mouseX < bottomX) && (mouseY > topY) && (mouseY < bottomY)) {
      mouseover = true;
    } else
        mouseover = false;
    return mouseover;
  }

}
public class Slice {
  
  private float z;
  private Table table;
  private 
  DescriptiveStatistics stats = new DescriptiveStatistics();
  DescriptiveStatistics stats2 = new DescriptiveStatistics();
  private float minValue = Float.NaN;
  private float maxValue = Float.NaN;
  private float minX = Float.NaN;
  private float maxX = Float.NaN;
  private float minY = Float.NaN;
  private float maxY = Float.NaN;
  private float statsMax, statsAbsMax;
  private float leftValue, midValue, rightValue;
  private float deltaX;
  
  public Slice(float z, Table table) {
    this.z = z;
    this.table = table;
    
    float dataX, dataY, dataValue;
    dataX = Float.NaN;
    dataY = Float.NaN;
    dataValue = Float.NaN;
    stats = new DescriptiveStatistics();
    
    for (int i = 0; i < table.getRowCount(); i++) {
      dataX = table.getFloat(i,0);
      dataY = table.getFloat(i,1);
      dataValue = table.getFloat(i,2);
      stats.addValue(dataValue);
      if (Float.isNaN(maxX)) {
          maxX = dataX;
          minX = dataX;
        } else {
          maxX = max(maxX, dataX);
          minX = min(minX, dataX);
        }
      if (Float.isNaN(maxY)) {
          maxY = dataY;
          minY = dataY;
        } else {
          maxY = max(maxY, dataY);
          minY = min(minY, dataY);
        }
      if (Float.isNaN(maxValue)) {
          maxValue = dataValue;
          minValue = dataValue;
        //} else if (dataValue < 2.0E-6) {
      } else {
          maxValue = max(maxValue, dataValue);
          minValue = min(minValue, dataValue);
      }
    }
    deltaX = (table.getFloat(1,0) - table.getFloat(0,0));
    statsMax = (float)stats.getPercentile(99.85f);
    statsAbsMax = (float)stats.getMax();
    stats2 = new DescriptiveStatistics();
    for (int i = 0; i < stats.getN(); i++) {
      if(stats.getElement(i) < statsMax) {
        stats2.addValue(stats.getElement(i));
      }
    }
  
    maxValue = (float)stats2.getMax();
    
    leftValue = (float)stats2.getPercentile(25);
    midValue = (float)stats2.getPercentile(85);
    rightValue = (float)stats2.getPercentile(99);
  }
  
  public Table getTable() {
    return table;
  }
  
  public double[] getStatsValues() {
    return stats2.getValues();
  }
  
  public float getLeftValue() {
    return leftValue;
  }
  
  public float getMidValue() {
    return midValue;
  }
  
  public float getRightValue() {
    return rightValue;
  }
  
  public float getMinValue() {
    return minValue;
  }
  
  public float getMaxValue() {
    return maxValue;
  }
  
  public float getMinX() {
    return minX;
  }
  
  public float getMaxX() {
    return maxX;
  }
  
  public float getMinY() {
    return minY;
  }
  
  public float getMaxY() {
    return maxY;
  }
  
  public float getDeltaX() {
    return deltaX;
  }
  
  public boolean isOutlier(float value) {
    boolean result = true;
    if (value < statsMax)
      result = false;
    return result;
  }
  public float getStatsAbsMax() {
    return statsAbsMax;
  }
}
  public void settings() {  size(820, 990); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "DataMatrixSketch" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
