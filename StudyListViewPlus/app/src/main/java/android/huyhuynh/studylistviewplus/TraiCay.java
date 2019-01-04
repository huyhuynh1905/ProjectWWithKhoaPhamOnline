package android.huyhuynh.studylistviewplus;

public class TraiCay {
    private String name;
    private String note;
    private int image;

    public TraiCay() {
    }

    public TraiCay(String name, String note, int image) {
        this.name = name;
        this.note = note;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
