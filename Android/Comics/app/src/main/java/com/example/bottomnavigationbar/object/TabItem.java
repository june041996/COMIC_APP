package com.example.bottomnavigationbar.object;

public class TabItem {

  private String text;
  private int icon;
  private boolean status;

  public TabItem(String text, boolean status) {
    this.text = text;

    this.status = status;
  }


  public String getText() {
    return text;
  }


  public void setText(String text) {
    this.text = text;
  }





  public boolean isStatus() {
    return status;
  }


  public void setStatus(boolean status) {
    this.status = status;
  }


}
