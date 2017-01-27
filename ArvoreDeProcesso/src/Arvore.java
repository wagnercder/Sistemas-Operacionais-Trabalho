import java.util.Random;

public class Arvore {
    private No raiz, raiz2;
    private Arvore arvoreEsquerda;
    private Arvore arvoreDireita;
    private int pids[];
    private int start,finish,length,nElements;
    private int pidNumber;
    
    private Pronto fila_pronto;
    private Pronto_Thread ready_thread;
    
    public Arvore(){}
    
    public Arvore(Pronto array_pronto, Pronto_Thread queue_thread){
        start = finish = -1;
        nElements = 100;
        pids = new int[nElements];
        nElements = 0;
        pidNumber = 0;
        
        this.ready_thread = queue_thread;
        fila_pronto = array_pronto.getPronto();
    }

    public Arvore getArvoreDireita() {
        return arvoreDireita;
    }

    public void setArvoreDireita(Arvore arvoreDireita) {
        this.arvoreDireita = arvoreDireita;
    }

    public Arvore getArvoreEsquerda() {
        return arvoreEsquerda;
    }

    public void setArvoreEsquerda(Arvore arvoreEsquerda) {
        this.arvoreEsquerda = arvoreEsquerda;
    }

    public No getRaiz() {
        return raiz;
        
    }
    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    public void insereProcesso(String nome) {
    	int pid = 0;
        boolean isDiferent = false;
       
        Random random = new Random();
        
        if(nElements != 0){
            while(!isDiferent){
                pid = random.nextInt(100);
                for (int i = start; i<=finish; i++) {
                    System.out.println(pids[i]);
                    if(pids[i] == pid || isDiferent)
                        isDiferent = true;
                }
            }
            pidNumber++;
            pids[pidNumber] = pid;
        }else{
            pid = random.nextInt(100);
            pids[pidNumber] = pid;
            pidNumber++;
        }
        
        Processo processo = new Processo(pid, nome, random.nextInt(2));
        No no = new No(processo);
        inserirNo(no);
    }

    private void inserirNo(No no) {
        if (this.raiz == null) {
            this.raiz = no;
        } else {
            if (no.getProcesso().getPid() > this.raiz.getProcesso().getPid()) {
                if (this.arvoreDireita == null) {
                    this.arvoreDireita = new Arvore();
                }
                this.arvoreDireita.inserirNo(no);
            } else if (no.getProcesso().getPid() < this.raiz.getProcesso().getPid()) {
                if (this.arvoreEsquerda == null) {
                    this.arvoreEsquerda = new Arvore();
                }
                this.arvoreEsquerda.inserirNo(no);
            }
        }
    }

    public void percorrerInOrder(Execucao fila) {
        if (this.raiz == null) {
           return;
        }

        if (this.arvoreEsquerda != null) {
            this.arvoreEsquerda.percorrerInOrder(fila);
        }

        fila_pronto.adicionar(this.raiz.getProcesso());
        
        
        if (this.arvoreDireita != null) {
            this.arvoreDireita.percorrerInOrder(fila);
        }
    }

    public void percorrerPreOrder(Pronto fila, Pronto_Thread ready_thread) {
        if (this.raiz == null) {
            return;
        }

        fila.adicionar(this.raiz.getProcesso());
        System.out.println("Thread_a_adicionar: "+this.raiz.getProcesso().getQueueThread().getFromIndex(0).getNome());
        
        ready_thread.adicionar(this.raiz.getProcesso().getQueueThread().getFromIndex(0));
        ready_thread.adicionar(this.raiz.getProcesso().getQueueThread().getFromIndex(1));
        ready_thread.adicionar(this.raiz.getProcesso().getQueueThread().getFromIndex(2));
        
        if (this.arvoreEsquerda != null) {
            this.arvoreEsquerda.percorrerPreOrder(fila,ready_thread);
        }

        if (this.arvoreDireita != null) {
            this.arvoreDireita.percorrerPreOrder(fila,ready_thread);
        }
    }

    public void percorrerPostOrder(Execucao fila) {
        if (this.raiz == null) {
           return;
        }

        if (this.arvoreEsquerda != null) {
            this.arvoreEsquerda.percorrerPostOrder(fila);
        }

        if (this.arvoreDireita != null) {
            this.arvoreDireita.percorrerPostOrder(fila);
        }
        
        
        fila.adicionar(this.raiz.getProcesso());
    }

    public Processo busca(int pid) {
        if (this.raiz == null) {
            return null;
        } else {
            if (pid == this.raiz.getProcesso().getPid()) {
                return this.raiz.getProcesso();
            } else {
                if (pid > this.raiz.getProcesso().getPid()) {
                    if (this.arvoreDireita == null) {
                        return null;
                    }
                    return this.arvoreDireita.busca(pid);
                } else {
                    if (this.arvoreEsquerda == null) {
                        return null;
                    }
                    return this.arvoreEsquerda.busca(pid);
                }
            }
        }
    }
    
    public Processo remocao(int pid){
 	   if (this.raiz == null) {
            return null;
        }else {
     	   if(pid == this.raiz.getProcesso().getPid()){
     		   this.raiz.setProcesso(null);
     		   this.raiz = raiz2;
     		   return null;
     	   }else{
     		  if(pid > this.raiz.getProcesso().getPid()){
        		   raiz2 = this.raiz;
        		   return this.arvoreDireita.remocao(pid);
        	   }else{
        		   raiz2 = this.raiz;
        		   return this.arvoreEsquerda.remocao(pid);
        	   }
     	   }
        }
    } 
    
    public void removeAllFromTree() {
        if (this.raiz == null) {
           return;
        }
        
        if (this.arvoreEsquerda != null) {
            this.arvoreEsquerda.removeAllFromTree();
        }
        
        if (this.arvoreDireita != null) {
            this.arvoreDireita.removeAllFromTree();
        }
        
        this.raiz = null;
        this.nElements --;
    }
    
    public int getAmountProcessesTree(){
    	return this.nElements;
    }
}