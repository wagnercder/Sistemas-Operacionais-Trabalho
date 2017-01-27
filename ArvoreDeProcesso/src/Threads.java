enum state_thread{
	Ready_state,Block_state,Execution_state;
}

public class Threads {
	private int tPid;
	private int pPid;
	private String nome;
	private int prioridade;
	private static int pid = 0;
	private int time = 100;
	private int currentPos = 0; //0 pronto 1 execucao 2 bloqueado
	
	public Threads (int pPid, int prioridade){
		pid++;
		this.tPid = pid;
		this.pPid = pPid;
		this.nome = "p"+pid;
		this.prioridade = prioridade;
	}

	public int getpPid() {
		return pPid;
	}

	public int getPid() {
		return tPid;
	}

	public String getNome() {
		return nome;
	}

	public int getPrioridade() {
		return prioridade;
	}
	
	public state_thread getCurrentState(){
		if(currentPos == 0){
			return state_thread.Ready_state;
		}
		
		if(currentPos == 1){
			return state_thread.Execution_state;
		}
		
		return state_thread.Block_state;
	}
	
	public void setCurrentState(int state){
		this.currentPos = state;
	}
	
	public int getTime(){
		return this.time;
	}
}
