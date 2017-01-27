import javax.swing.JOptionPane;

public class Block_Thread {
	private int start,finish,length,nElements;
    private Threads threads_queue[];
    private Threads flagProcess;
    private boolean reorde;
    
    public Block_Thread(){ 	
        start = finish = -1;
        nElements = 100;
        threads_queue = new Threads[nElements];
        nElements = 0;
    }
 
    public boolean isEmpty(){
        if (nElements == 0){
            return true;
        }
        return false;
    }
 
    public boolean isFull(){
        if (nElements == length - 1){
            return true;
        }
        return false;
    }
 
    public void adicionar(Threads newThread){
        if (! isFull()){
            if (start == -1){
                start = 0;
            }
            newThread.setCurrentState(2);
            threads_queue[this.nElements] = newThread;
            nElements++;
        }
    }
    
    public void remove(){
        if (! isFull() ){
            start++;
            nElements--;
        }
    }

    public void showElements(){
        String elements = "";
        
        for (int i = start; i<=finish; i++) {
            elements += threads_queue[i].getNome()+ " - ";
        }
        JOptionPane.showMessageDialog(null, elements);
    }
    
    public Threads getFirstProcess(){
        return this.threads_queue[0];
    }
    
    public void reorderQueue(){
        if(this.threads_queue[0] !=null && this.threads_queue[1] == null){
            this.threads_queue[0] = null;
        }else{
            for(int i=0; i<= this.nElements - 1 ; i++){
                if(this.threads_queue[i+1] != null){
                   this.threads_queue[i] = this.threads_queue[i+1];
                   this.threads_queue[i+1] = null;
                }
            }
        }
        this.nElements--;
        
        if(this.nElements == 0){
         //   System.out.println("Fila pronto vazia");
        }
    }
    
    public Block_Thread getPronto(){
        return this;
    }
    
    public int getAmountProcesses(){
        return this.nElements;
    }
    
    public int getPidProcess(int index){
        if(this.threads_queue[index]!=null){
            return this.threads_queue[index].getPid();
        }else{
            return 0;
        }
    }
    
    public String getNameProcess(int index){
        if(this.threads_queue[index]!=null){
            return this.threads_queue[index].getNome();
        }else{
            return null;
        }
    }
    
    public boolean removeFromQueue(int pidToRemove){
    	Threads[] filaAux = new Threads[this.nElements];
        int j=0;
        
        for(int i=0;i<this.nElements;i++){	
        	if(this.threads_queue[i].getPid()!=pidToRemove){
        		filaAux[j] = this.threads_queue[i];
        	}else{
        		this.nElements--;
        	}
        	
        	j++;
        }
        
        for(int i=0;i<this.nElements;i++){
        	this.threads_queue[i] = filaAux[i];
        }
       
        return true;
    }
}
