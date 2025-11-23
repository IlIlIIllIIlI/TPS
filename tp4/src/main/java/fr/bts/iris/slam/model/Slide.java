package fr.bts.iris.slam.model;

public class Slide {
    private int id;
    protected String title;
    protected String titleColor;
    protected String imagePath;
    protected int position;

    public Slide(String title, String titleColor, String imagePath, int position) {
        if (title == null|| title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        if (title.trim().length()<3) {
            throw new IllegalArgumentException("Title must be at least 3 characters long");

        }

        if (title.trim().length()>100) {
            throw new IllegalArgumentException("Title cannot exceed 100 characters");

        }

        if (titleColor == null||titleColor.trim().length() !=7||!titleColor.startsWith("#")) {
            throw new IllegalArgumentException("Invalid color format. Expected #RRGGBB (e.g., #FF0000)");
        }
        int  chAscii;
        for (int i = 1; i <titleColor.length() ; i++) {
            chAscii = titleColor.charAt(i);

            if(!Character.isDigit(titleColor.charAt(i))&& (chAscii<65 || chAscii>70) && (chAscii<97||chAscii>102)){
                throw new IllegalArgumentException("Invalid color format. Expected #RRGGBB (e.g., #FF0000)");

            }

        }

        if (imagePath == null ||imagePath.isBlank()) {
            throw new IllegalArgumentException("Image path cannot be null or empty");
        }

        if (imagePath.trim().length()<5) {
            throw new IllegalArgumentException("Image path is too short");
        }

        if (
                !(imagePath.toUpperCase().endsWith(".JPEG"))&&!(imagePath.toUpperCase().endsWith(".JPG"))&&
                !(imagePath.toUpperCase().endsWith(".PNG"))&&!(imagePath.toUpperCase().endsWith(".GIF"))) {

            throw new IllegalArgumentException("Image path must end with .jpg, .jpeg, .png, or .gif");

        }



        if (position <1) {
            throw new IllegalArgumentException("Position can't be below 1");
        }

        this.id=0;
        this.title=title;
        this.imagePath=imagePath;
        this.position = position;
        this.title = title;
        this.titleColor = titleColor;
    }

    public Slide(int id,String title, String titleColor, String imagePath, int position) {
        if (title == null|| title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        if (title.trim().length()<3) {
            throw new IllegalArgumentException("Title must be at least 3 characters long");

        }

        if (title.trim().length()>100) {
            throw new IllegalArgumentException("Title cannot exceed 100 characters");

        }

        if (titleColor == null||titleColor.trim().length() !=7||!titleColor.startsWith("#")) {
            throw new IllegalArgumentException("Invalid color format. Expected #RRGGBB (e.g., #FF0000)");
        }
        int  chAscii;
        for (int i = 1; i <titleColor.length() ; i++) {
            chAscii = titleColor.charAt(i);

            if(!Character.isDigit(titleColor.charAt(i))&& (chAscii<65 || chAscii>70) && (chAscii<97||chAscii>102)){
                throw new IllegalArgumentException("Invalid color format. Expected #RRGGBB (e.g., #FF0000)");

            }

        }

        if (imagePath == null || imagePath.isBlank()) {
            throw new IllegalArgumentException("Image path cannot be null or empty");
        }

        if (imagePath.trim().length()<5) {
            throw new IllegalArgumentException("Image path is too short");
        }

        if (
                !(imagePath.toUpperCase().endsWith(".JPEG"))&&!(imagePath.toUpperCase().endsWith(".JPG"))&&
                        !(imagePath.toUpperCase().endsWith(".PNG"))&&!(imagePath.toUpperCase().endsWith(".GIF"))) {

            throw new IllegalArgumentException("Image path must end with .jpg, .jpeg, .png, or .gif");

        }




        if (position <1) {
            System.out.println(position);
            throw new IllegalArgumentException("Position can't be below 1");
        }

        this.id=id;
        this.title=title;
        this.imagePath=imagePath;
        this.position = position;
        this.title = title;
        this.titleColor = titleColor;
    }

    public String display(){
        return String.format("Slide[title= %s, color= %s, image= %s, position= %s]", this.title, this.titleColor,this.imagePath,this.position);
    }

    public String getTitle() {
        return this.title;
    }

    public int getId() {
        return this.id;
    }

    public String getTitleColor() {
        return this.titleColor;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public int getPosition() {
        return this.position;
    }

    public void setTitle(String title) {
        if (title == null|| title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        if (title.trim().length()<3) {
            throw new IllegalArgumentException("Title must be at least 3 characters long");

        }

        if (title.trim().length()>100) {
            throw new IllegalArgumentException("Title cannot exceed 100 characters");

        }
        this.title = title;
    }

    public void setTitleColor(String titleColor) {
        if (titleColor == null||titleColor.trim().length() !=7||!titleColor.startsWith("#")) {
            throw new IllegalArgumentException("Invalid color format. Expected #RRGGBB (e.g., #FF0000)");
        }
        int  chAscii;
        for (int i = 1; i <titleColor.length() ; i++) {
            chAscii = titleColor.charAt(i);

            if(!Character.isDigit(titleColor.charAt(i))&& (chAscii<65 || chAscii>70) && (chAscii<97||chAscii>102)){
                throw new IllegalArgumentException("Invalid color format. Expected #RRGGBB (e.g., #FF0000)");

            }

        }
        this.titleColor = titleColor;
    }

    public void setImagePath(String imagePath) {
        if (imagePath == null ||imagePath.isBlank()) {
            throw new IllegalArgumentException("Image path cannot be null or empty");
        }

        if (imagePath.trim().length()<5) {
            throw new IllegalArgumentException("Image path is too short");
        }

        if (
                !(imagePath.toUpperCase().endsWith(".JPEG"))&&!(imagePath.toUpperCase().endsWith(".JPG"))&&
                        !(imagePath.toUpperCase().endsWith(".PNG"))&&!(imagePath.toUpperCase().endsWith(".GIF"))) {

            throw new IllegalArgumentException("Image path must end with .jpg, .jpeg, .png, or .gif");

        }



        this.imagePath = imagePath;
    }

    public void setPosition(int position) {
        if (position <1) {
            throw new IllegalArgumentException("Position can't be below 1");
        }

        this.position = position;
    }

    public void setId(int id) {
        this.id = id;
    }
}
