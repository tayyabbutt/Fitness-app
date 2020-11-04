package com.mobxpert.men30dayfitnesschallenge.models;

public class MyImage {
    private String title;
    private String path;
    //private Calendar datetime;
    private String dateTime;
    // protected SimpleDateFormat df = new SimpleDateFormat("MMMM d, yy  h:mm");


    public String getTitle() {
        return title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    // public Calendar getDatetime() { return datetime; }


  /*  public void setDatetime(long datetimeLong) {
        this.datetimeLong = datetimeLong;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(datetimeLong);
        this.datetime = cal;
    }*/


    //   public void setDatetime(Calendar datetime) { this.datetime = datetime; }


    // public String getDescription() { return description; }


    public void setTitle(String title) {
        this.title = title;
    }


//    public void setDescription(String description) { this.description = description; }


    public void setPath(String path) {
        this.path = path;
    }


    public String getPath() {
        return path;
    }

   /* @Override public String toString() {
        return "Title:" + title + "   " + df.format(datetime.getTime()) +
                "\nDescription:" + description + "\nPath:" + path;
    }*/
}