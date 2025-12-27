public class Album implements IAlbum{

    private String albumName; 
    private int size = 0; 
    private Node<Photo> current; 
    private boolean albumOpen = false; 
    private Node<Photo> firstAdded; 

  //// Nested Node ////
  private static class Node<Photo>{
     private Photo element;
     private Node<Photo> prev; 
     private Node<Photo> next; 

    public Node(Photo e, Node<Photo> p, Node<Photo> n){
        element = e;
        prev = p;
        next = n;
     }

    public Photo getElement(){
        return element; 
     }

    public Node<Photo> getPrev(){
        return prev; 
     }

    public Node<Photo> getNext(){
        return next; 
     }

    public void setPrev(Node<Photo> p){
        prev = p;
     }

    public void setNext(Node<Photo> n){
        next = n; 
     }
  }
  //// End of Nested Node ////

    public Album(String albumName){     //constructor   
        this.albumName = albumName; 
    }

    public boolean isEmpty(){
        if (size == 0){
            return true; 
        }
        return false; 
    }


    @Override
    public String getAlbumName(){
        return albumName; 
    }

    @Override
    public int getCount(){
        return size; 
    }

    @Override
    public boolean hasPhoto(Photo photo){
        if (firstAdded == null) 
            return false; 

        Node<Photo> temp = firstAdded; 

        while(true)
        {
            if (temp.getElement().equals(photo))
                return true; 

            temp = temp.getNext(); 

            if (temp == firstAdded){
                break; 
            }
    
        } 

        return false; 

    }

    @Override
    public void addPhoto(Photo photo){

        if (hasPhoto(photo)){
            System.out.println("Attempted to add duplicate photo.");
            return;  
        }
        
        Node<Photo> newNode = new Node<Photo>(photo, null, null); 

        if (current == null){
            current = newNode; 
            firstAdded = newNode; 
            newNode.setNext(newNode); 
            newNode.setPrev(newNode); 
        }
        else{
            Node<Photo> lastAdded = firstAdded.getPrev();

            newNode.setPrev(lastAdded);
            newNode.setNext(firstAdded);
            lastAdded.setNext(newNode);  
            firstAdded.setPrev(newNode);
        }
        size++; 
    }
       
    @Override
    public void deletePhoto(Photo photo){

        Node<Photo> temp = firstAdded;
        boolean found = false;

     while (true){
        if (temp.element.equals(photo)){
            found = true;
            if (size == 1){
                // album becomes empty
                current = null;
                firstAdded = null;
            } else{
                Node<Photo> pred = temp.getPrev();
                Node<Photo> succ = temp.getNext();

                pred.setNext(succ);
                succ.setPrev(pred);

                // update firstAdded if we removed it
                if (temp == firstAdded){
                    firstAdded = succ;
                }
                // update current if we removed it
                if (temp == current){
                    current = succ;
                }
            }
            size--;
            break; // done, exit loop
        }

        temp = temp.getNext();

        if (temp == firstAdded){
            break; // we’ve checked everything once
        }
        }

        if (!found){
         System.out.println("Attempted to delete a photo that is not in the album.");
         }
    }

    @Override
    public boolean allPhotosViewed(){

        if (this.isEmpty()){
            return true; 
        }

        if (firstAdded == null) 
            return false; 

        Node<Photo> temp = firstAdded; 

        while(true){
            if (!temp.getElement().isViewed()){
                return false;  
            }

            temp = temp.getNext(); 

            if (temp == firstAdded){
                break;
            }
        }
    
            return true; 
    }

    @Override
    public boolean equals(IAlbum other){
        if(this.size != other.getCount()){
            return false;
        }

        if (this.isEmpty() && other.getCount() == 0){
            return true; 
        }

        Node<Photo> temp = this.firstAdded; 

        do {
            
            if(!other.hasPhoto(temp.getElement())){
                    return false;
            }

            temp = temp.getNext(); 

            } while (temp != this.firstAdded);

        return true; 
    }


    @Override
    public void openAlbum(){
        System.out.println("Album " + albumName +" opened.");
        albumOpen = true; 
        if(!isEmpty())
        {
          current.getElement().viewPhoto(); 
        }

    }

    @Override
    public void closeAlbum(){
        System.out.println("Album " + albumName + " closed.");
        albumOpen = false; 
    }

    @Override
    public void viewNextPhoto(){
        if(!albumOpen){
            System.out.println("Tried to view next photo, but album is closed.");
        } else if(isEmpty()){
            System.out.println("Tried to view next photo, but album has no photos.");
        }else{
        current = current.getNext(); 
        current.getElement().viewPhoto(); 
        }
   }


    @Override
    public void viewPreviousPhoto(){
        if(!albumOpen){
            System.out.println("Tried to view previous photo, but album is closed.");
        }else if(isEmpty()){
            System.out.println("Tried to view previous photo, but album has no photos.");
        }else{
        current = current.getPrev(); 
        current.getElement().viewPhoto(); 
        }

    }



    
    public static void main(String[] args){



    }
}