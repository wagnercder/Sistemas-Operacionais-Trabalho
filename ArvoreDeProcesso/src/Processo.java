public class Processo {
    private int pid;
    private int surto;
    private int prioridade;
    private int execucao;
    private int espera;
    private String nome;
    private int typeProcess; // 1 = I/O && 0 = CPU  
    
    private Threads t1;
    private Threads t2;
    private Threads t3;
    
    private Thread_Queue threadQueue;
    
    private static int qProcesses; //Quantidade de processos criados
        
    public Processo(int pid,String nome, int type){
    	this.pid = pid;/*Identificação do processo*/
    	this.nome = nome;/*nome do processo*/
    	this.typeProcess = type;
    	this.qProcesses++;
    	
    	t1 = new Threads(this.pid,1);
    	t2 = new Threads(this.pid,2);
    	t3 = new Threads(this.pid,3);
    	
    	System.out.println("pid_process: "+pid+" pid_thread: "+t1.getPid());
    	System.out.println("pid_process: "+pid+" pid_thread: "+t2.getPid());
    	System.out.println("pid_process: "+pid+" pid_thread: "+t3.getPid());
    	
    	threadQueue = new Thread_Queue();
    	
    	threadQueue.inserir(t1);
    	threadQueue.inserir(t2);
    	threadQueue.inserir(t3);
    }
        
	public int getPid() {
        return pid;
	}

	public void setPid(int pid) {
            this.pid = pid;
	}

	public int getSurto() {
            return surto;
	}

	public void setSurto(int surto) {
            this.surto = surto;
    }

	public int getPrioridade() {
            return prioridade;
	}

	public void setPrioridade(int prioridade) {
            this.prioridade = prioridade;
	}

	public int getExecucao() {
            return execucao;
	}

    public void setExecucao(int execucao) {
            this.execucao = execucao;
	}

	public int getEspera() {
            return espera;
	}

	public void setEspera(int espera) {
            this.espera = espera;
	}

	public String getNome() {
            return nome;
	}

	public void setNome(String nome) {
            this.nome = nome;
	}
        
    public int getTypeProcess(){
            return this.typeProcess;
    }
    
    public Threads getFirstFromQueue_thread(){
    	return this.threadQueue.getFirst();
    }
    
    public Threads getFirstFromIndex(int index){
    	return this.threadQueue.getFromIndex(index);
    }
    
    public Thread_Queue getQueueThread(){
    	return this.threadQueue;
    }
}