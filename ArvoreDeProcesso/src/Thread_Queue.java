
import java.util.ArrayList;

public class Thread_Queue {
	private ArrayList<Threads> t = new ArrayList<Threads>();
	
	public void inserir(Threads thread){
		t.add(thread);
	}
	
	public void mostra(){
		int n = t.size();
		System.out.print("[");
		for(int i = 0;i<n;i++){
			System.out.print(t.get(i).getNome()+" ");
		}
		System.out.println("]");
	}
	
	public Threads getFromIndex(int index){
		return t.get(index);
	}
	
	public Threads getFirst(){
		return t.get(0);
	}
	
	public Thread_Queue getQueue(){
		return this;
	}
	
	public boolean wallState_toBlock(){
		if(t.get(0).getCurrentState() == state_thread.Block_state && 
				t.get(1).getCurrentState() == state_thread.Block_state &&
				t.get(2).getCurrentState() == state_thread.Block_state){
			return true;
		}
		return false;
	}
	
	public boolean wallState_toReady(){
		if(t.get(0).getCurrentState() == state_thread.Ready_state && 
				t.get(1).getCurrentState() == state_thread.Ready_state &&
				t.get(2).getCurrentState() == state_thread.Ready_state){
			return true;
		}
		return false;
	}
	
	public boolean wallState_toExe(){
		if(t.get(0).getCurrentState() == state_thread.Execution_state && 
				t.get(1).getCurrentState() == state_thread.Execution_state &&
				t.get(2).getCurrentState() == state_thread.Execution_state){
			return true;
		}
		return false;
	}
}
