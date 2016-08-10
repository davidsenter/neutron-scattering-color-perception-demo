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
    statsMax = (float)stats.getPercentile(99.85);
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