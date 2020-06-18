package com.rockets.threads;

import java.util.ArrayList;
import java.util.List;

import com.rockets.operations.Accelerate;
import com.rockets.operations.Operation;

/**
 * Classe que encapsula la gestió en la creació de Threads "ThrusterThread"
 * així com la consulta de l'estat dels Threads i verifica la seva aturada
 * 
 * @author Ivan
 *
 */
public class OperationsLauncher {

	private List<ThrusterThread> threadsList;
	
	public static final int CONST_ACCELERATE = 1;
	public static final int CONST_BRAKE = 2;

	/**
	 * Crea un nou Thread ThrusterThread per a cada operació planificada de la llista paràmetre "opsList"
	 * Passa al constructor de cada instància de ThrusterThread: el número de Thread, el coet i propulsor
	 * al qual se li ha d'aplicar una operació, el tipus d'operació(accelerar o frenar) i la potència
	 * objectiu
	 * S'afegeix cada nou Thread a la llista "threadsList"
	 * 
	 * @param opsList - llista d'operacions planificades
	 */
	public OperationsLauncher(List<Operation> opsList) {
		
		threadsList = new ArrayList<>();
		int typeOp=0;
		int i=0;
		
		for (Operation op: opsList) {
			
			if (op instanceof Accelerate) {
				typeOp = CONST_ACCELERATE;
			}else {
				typeOp = CONST_BRAKE;
			}
			
			ThrusterThread thread = 
					new ThrusterThread(i,op.getRocket(),op.getThruster(),typeOp, op.getObjectivePower());
			
			this.threadsList.add(thread); //S'afegeix  el nou Thread a la llista de Threads
			i++;
		}
		
	}
	
	public void execute() { //Executa concurrentment tots els Threads de la llista "threadsList"
		
		for (ThrusterThread t : this.threadsList) {
			t.start();
		}
	}
	
	/**
	 * Retorna true si tots els Threads de la llista "threadsList" han finalitzat
	 * retorna false, en cas contrari
	 * @return
	 */
	public boolean areAllOperationsFinished() {
		
		boolean finished = true;
		int i=0;
		
		while(i<this.threadsList.size() && finished==true) {
			
			 finished = this.threadsList.get(i).isOperationFinished();
			 i++;
		}		
		
		return finished;
	}
	
	/**
	 * Atura tots els Threads de la llista "threadsList"
	 */
	public void stopAllThreads() {
		
		for (ThrusterThread t: this.threadsList) {
			t.interrupt();
		}
		
	}
}
