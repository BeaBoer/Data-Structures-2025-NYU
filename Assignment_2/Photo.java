public class Photo{

    private String name; 
    private String photoDigest; //string of characters representing unique identifier
    private boolean photoViewed; 

    public Photo(String name,String photoDigest){
        this.name = name;
        this.photoDigest = photoDigest;
        this.photoViewed = false;
    }
    
    public String getName(){
        return name; 
    }

    public String getPhotoDigest(){ 
        return photoDigest; 
    }
    
    public void viewPhoto(){
        System.out.println("Now viewing " + name + "."); 
        this.photoViewed = true; 
    }
    
    public boolean isViewed(){
        return photoViewed; 
    }
    
    public boolean equals(Photo other){
        return this.photoDigest.equals(other.photoDigest);
    }
    
}