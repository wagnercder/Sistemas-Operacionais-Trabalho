/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JOptionPane;

public class Bloqueados {
     private int start,finish,length,nElements;
    private Processo processes[];
    private Processo flagProcess;
    private boolean reorde;
    
    public Bloqueados(){
        start = finish = -1;
        nElements = 10;
        processes = new Processo[nElements];
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
 
    public void adicionar(Processo newProcess){
        
        if (! isFull()){
            if (start == -1){
                start = 0;
            }
            processes[this.nElements] = newProcess;
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
            elements += processes[i].getNome()+ " - ";
        }
        JOptionPane.showMessageDialog(null, elements);
    }
    
    public Processo getFirstProcess(){
        return this.processes[0];
    }
    
    public void reorderQueue(){
        if(this.processes[0] !=null && this.processes[1] == null){
            this.processes[0] = null;
        }else{
            for(int i=0; i<= this.nElements - 1 ; i++){
                if(this.processes[i+1] != null){
                   this.processes[i] = this.processes[i+1];
                   this.processes[i+1] = null;
                }
            }
        }
        this.nElements--;
    }
    
    public Bloqueados getBloqueados(){
        return this;
    }
    
    public int getAmountProcesses(){
        return this.nElements;
    }
    
    public int getPidProcess(int index){
        if(this.processes[index]!=null){
            return this.processes[index].getPid();
        }else{
            return 0;
        }
    }
    
    public String getNameProcess(int index){
        if(this.processes[index]!=null){
            return this.processes[index].getNome();
        }else{
            return null;
        }
    }
    
    public boolean removeFromQueue(int pidToRemove){
    	Processo[] filaAux = new Processo[this.nElements];
        int j=0;
        
        for(int i=0;i<this.nElements;i++){	
        	if(this.processes[i].getPid()!=pidToRemove){
        		filaAux[j] = this.processes[i];
        	}else{
        		this.nElements--;
        	}
        	j++;
        }
        
        for(int i=0;i<this.nElements;i++){
        	this.processes[i] = filaAux[i];
        }
       
        return true;
    }
}
