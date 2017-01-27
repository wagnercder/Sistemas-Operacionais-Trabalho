import java.io.BufferedReader;
import java.util.Scanner;

public class ArvoreDeProcesso extends Thread {
    static int tamInsecao;
    static int i=1,l=0;
    static int escolha = 0;
    static String pidRemove;
    static Scanner tc = new Scanner(System.in);
    static Pronto fila_pronto = new Pronto();
    static Bloqueados fila_block = new Bloqueados();
    static Execucao fila_exec = new Execucao(); 
    static boolean flagFirst = true;
    
    static Pronto_Thread ready_thread = new Pronto_Thread();
    static Block_Thread block_thread = new Block_Thread(); 
    static Execucao_Thread exec_thread = new Execucao_Thread();
    
    public static void main(String[] args) { 
       
    	Arvore ab = new Arvore(fila_pronto,ready_thread); 
        while(i!=0){
            Menu(ab);
        }
    }
    
    public static void Menu(Arvore a){
    	System.out.println("==============================================");
        System.out.println("1-Adicionar Processo");
        System.out.println("2-Remover Processo");
        System.out.println("3-Demostra Processos");
        System.out.println("4-Excluir todos os processos");
        System.out.println("5-Características to processo"); // não implementado ainda
        System.out.println("Digite: ");
        escolha = tc.nextInt();
        
        switch (escolha) {
		case 1:
			System.out.println("Digite o tamanho do processo a serem inseridos: ");
			tamInsecao = tc.nextInt();
			for(int i=0;i<tamInsecao;i++){
				a.insereProcesso("P"+i);
			}
			break;
		case 2:
			Scanner entrada = new Scanner (System.in);
			System.out.println("Digite o PID para remover o processo: ");
			pidRemove = entrada.nextLine();
			String rm[] = pidRemove.split(" ");
			
			if(pidRemove.equals("kill "+rm[1])){
				int pid = Integer.parseInt(rm[1]);
				a.remocao(pid);
                removeFromQueue(pid);
			}else{
				System.out.println("No such process");
			}
			break;

		case 3:
			if(flagFirst){
				a.percorrerPreOrder(fila_pronto,ready_thread); //Aqui ele já adiciona todos os processos na fila de execução
				flagFirst = false;
			}
			
			System.out.println("==============================================");
            
			for(int i = 0 ;i<5;i++){
	            try{
	                //Fila Pronto
	                if(!fila_pronto.isEmpty()){
	                	
	                	System.out.print("Pronto: ");
	                    for(int m = 0 ; m <fila_pronto.getAmountProcesses(); m++){
	                    	System.out.print(" "+fila_pronto.getPidProcess(m));
	                    }
	                    
	                    System.out.print("                        Pronto_Threads: ");
	                    for(int m = 0 ; m <ready_thread.getAmountProcesses(); m++){
	                    	System.out.print(" "+ready_thread.getPidProcess(m));
	                    }
	                    
	                    System.out.println("");
	                    System.out.print("Execuc: ");
	                    for(int m = 0 ; m <fila_exec.getAmountProcesses(); m++){
	                    	System.out.print(" "+fila_exec.getPidProcess(m));
	                    }
	                    
	                    System.out.print("                        Execut_Threads: ");
	                    for(int m = 0 ; m <exec_thread.getAmountProcesses(); m++){
	                    	System.out.print(" "+exec_thread.getPidProcess(m));
	                    }
	                    
	                    System.out.println("");
	                    System.out.print("Bloque: ");
						for(int m = 0 ; m <fila_block.getAmountProcesses(); m++){
							System.out.print(" "+fila_block.getPidProcess(m));
						}
						
						System.out.print("                        Block_Threads: ");
	                    for(int m = 0 ; m <block_thread.getAmountProcesses(); m++){
	                    	System.out.print(" "+block_thread.getPidProcess(m));
	                    }
						
						System.out.println("");
						
						l = 0;
						while(!fila_pronto.getFirstProcess().getQueueThread().wallState_toExe()){ // Verifica o estado em que as threads se encontram
							Thread.sleep(fila_pronto.getFirstProcess().getQueueThread().getFromIndex(l).getTime());
							exec_thread.adicionar(fila_pronto.getFirstProcess().getQueueThread().getFromIndex(l));
							ready_thread.reorderQueue();
							l++;
						}
	                    
	                    fila_exec.adicionar(fila_pronto.getFirstProcess()); //manda processo para execução
	                    fila_pronto.reorderQueue();
	                    
	                    System.out.println("==============================================");
	                }
	                
	                //Fila Exec
	                if(!fila_exec.isEmpty()){
	                	System.out.print("Pronto: ");
	                    for(int m = 0 ; m <fila_pronto.getAmountProcesses(); m++){
	                    	System.out.print(" "+fila_pronto.getPidProcess(m));
	                    }
	                    
	                    System.out.print("                        Pronto_Threads: ");
	                    for(int m = 0 ; m <ready_thread.getAmountProcesses(); m++){
	                    	System.out.print(" "+ready_thread.getPidProcess(m));
	                    }
	                    
	                    System.out.println("");
	                    System.out.print("Execuc: ");
	                    for(int m = 0 ; m <fila_exec.getAmountProcesses(); m++){
	                    	System.out.print(" "+fila_exec.getPidProcess(m));
	                    }
	                    
	                    System.out.print("                        Execut_Threads: ");
	                    for(int m = 0 ; m <exec_thread.getAmountProcesses(); m++){
	                    	System.out.print(" "+exec_thread.getPidProcess(m));
	                    }
	                    
	                    System.out.println("");
	                    System.out.print("Bloque: ");
						for(int m = 0 ; m <fila_block.getAmountProcesses(); m++){
							System.out.print(" "+fila_block.getPidProcess(m));
						}
						
						System.out.print("                        Block_Threads: ");
	                    for(int m = 0 ; m <block_thread.getAmountProcesses(); m++){
	                    	System.out.print(" "+block_thread.getPidProcess(m));
	                    }
						
						System.out.println("");
	                    
						l = 0;
	                    if(fila_exec.getFirstProcess().getTypeProcess() == 1){
							while(!fila_exec.getFirstProcess().getQueueThread().wallState_toBlock()){ // Verifica o estado em que as threads se encontram
								Thread.sleep(fila_exec.getFirstProcess().getQueueThread().getFromIndex(l).getTime());
								block_thread.adicionar(fila_exec.getFirstProcess().getQueueThread().getFromIndex(l));
			                    exec_thread.reorderQueue();
								l++;
							}
	                    	
	                        fila_block.adicionar(fila_exec.getFirstProcess());	                       
	                    }else{
							while(!fila_exec.getFirstProcess().getQueueThread().wallState_toReady()){ // Verifica o estado em que as threads se encontram
								Thread.sleep(fila_exec.getFirstProcess().getQueueThread().getFromIndex(l).getTime()); 
								ready_thread.adicionar(fila_exec.getFirstProcess().getQueueThread().getFromIndex(l));
			                    exec_thread.reorderQueue();
								l++;
							}
	                        fila_pronto.adicionar(fila_exec.getFirstProcess());
	                    }
	                    fila_exec.reorderQueue();
	                     
	                    System.out.println("==============================================");
	                }
	                
	                //Fila Bloqueados
	                if(!fila_block.isEmpty()){
	                	System.out.print("Pronto: ");
	                    for(int m = 0 ; m <fila_pronto.getAmountProcesses(); m++){
	                    	System.out.print(" "+fila_pronto.getPidProcess(m));
	                    }
	                    
	                    System.out.print("                        Pronto_Threads: ");
	                    for(int m = 0 ; m <ready_thread.getAmountProcesses(); m++){
	                    	System.out.print(" "+ready_thread.getPidProcess(m));
	                    }
	                    
	                    System.out.println("");
	                    System.out.print("Execuc: ");
	                    for(int m = 0 ; m <fila_exec.getAmountProcesses(); m++){
	                    	System.out.print(" "+fila_exec.getPidProcess(m));
	                    }
	                    
	                    System.out.print("                        Execut_Threads: ");
	                    for(int m = 0 ; m <exec_thread.getAmountProcesses(); m++){
	                    	System.out.print(" "+exec_thread.getPidProcess(m));
	                    }
	                    
	                    System.out.println("");
	                    System.out.print("Bloque: ");
						for(int m = 0 ; m <fila_block.getAmountProcesses(); m++){
							System.out.print(" "+fila_block.getPidProcess(m));
						}
						
						System.out.print("                        Block_Threads: ");
	                    for(int m = 0 ; m <block_thread.getAmountProcesses(); m++){
	                    	System.out.print(" "+block_thread.getPidProcess(m));
	                    }
						
						System.out.println("");
						
						l = 0;
						while(!fila_block.getFirstProcess().getQueueThread().wallState_toReady()){ // Verifica o estado em que as threads se encontram
							ready_thread.adicionar(fila_block.getFirstProcess().getQueueThread().getFromIndex(l));
							block_thread.reorderQueue();
							l++;
						}
                        
	                    Thread.sleep(fila_block.getFirstProcess().getExecucao());; //executa o tempo de espera
	                    fila_pronto.adicionar(fila_block.getFirstProcess()); //manda processo para execução
	                    fila_block.reorderQueue();
	                    System.out.println("==============================================");
	                }
	            }catch(Exception ecx){
	              //  System.out.println("exceção");
	            }
	        }
			break;
		case 4:
			a.removeAllFromTree();
			System.out.println("Quantidade de processos na árvore: "+ a.getAmountProcessesTree());
			break;
			
		case 5:
			
			break;
		default:
            break;
		}
    }
    
    public static void removeFromQueue(int pid){
        if(fila_exec.removeFromQueue(pid) && fila_pronto.removeFromQueue(pid) && fila_block.removeFromQueue(pid))
            System.out.println("Processo Removido");
        else
            System.out.println("Processo não encontrado");
    }
}