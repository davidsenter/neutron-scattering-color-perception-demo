public class Button {
  
  private int topX, topY, bottomX, bottomY, textSize;
  private boolean mouseover;
  String text;
  private color buttonColor;

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