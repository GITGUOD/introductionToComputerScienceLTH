package lpt;

import java.util.ArrayList;

public class TestScheduler {

	public static void main(String[] args) {
		Machine[] m = new Machine[3];
		for (int i = 0; i < m.length; i++) {
			m[i] = new Machine(i + 1);
		}
		
		ArrayList<Job> jobList = new ArrayList<Job>();
		String [] names = {"j1", "j2", "j3", "j4", "j5", "j6", "j7"};
		int[] times = {2, 14, 4, 16, 6, 5, 3};
		for (int i = 0; i < names.length; i++) {
			jobList.add(new Job(names[i], times[i]));
		}
		
		Scheduler s = new Scheduler(m);
		s.makeSchedule(jobList);
		s.printSchedule();
	}

}
package lpt;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
	private Machine[] machines;
	/** Skapar en schemaläggare för maskinerna 
		i vektorn machines. */
	
	public Scheduler(Machine[] machineArray) {
		Machine[] machines = machineArray;
	}
	
	/* Returnerar den maskin som har minst att göra. */
	private Machine machineWithLeastToDo() {
		int min = Integer.MAX_VALUE;
		int minPos = -1;
		for (int i = 0; i < machines.length; i++) {
			int totalTime = machines[i].getScheduledTime();
			if (totalTime < min) {
				min = totalTime;
				minPos = i;
			}
		}
		return machines[minPos];
	}
	
	/** Fördelar jobben i listan jobs på maskinerna. */
	public void makeSchedule(List<Job> jobs) {
		List<Job> tempJobList = new ArrayList<>(jobs);
		tempJobList.sort((j1, j2) -> j1.getTime() - j2.getTime());
		for (Job j : tempJobList) {
			Machine m = machineWithLeastToDo();	
			m.assignJob(j);
		}	
	}
	
	/** Tar bort alla jobb från maskinerna. */
	public void clearSchedule() {
		for(int i = 0; i < machines.length; i++) {
			machines[i].clearJobs();
		}	
	}

	/** Skriver ut maskinernas scheman. */
	public void printSchedule() {
		for (int i = 0; i <= machines.length; i++) {
			System.out.println(machines[i]);
		}
	}
}
package lpt;

import java.util.ArrayList;

public class Machine {
	private int nbr;
	private ArrayList<Job> jobs;
	private int scheduledTime;
	
	/** Skapar maskin nr nbr. */
	public Machine(int nbr) {
		this.nbr = nbr;
		jobs = new ArrayList<Job>();
		scheduledTime = 0;
	}

	/** Tilldelar maskinen jobbet j. */
	public void assignJob(Job j) {
		jobs.add(j);
		scheduledTime = j.getTime();
	}
	
	/** Tar bort alla jobb från maskinen. */
	public void clearJobs() {
		jobs.clear();
		scheduledTime = 0;
	}

	/** Tar bort och returnerar nästa jobb som maskinen ska utföra. 
	 	Returnerar null om maskinen inte har några jobb. */
	public Job getNextJob() {
		if (jobs.isEmpty()) {
			return null;
		}
		scheduledTime -= jobs.get(0).getTime();
		return jobs.remove(0);
	}
	
	/** Tar reda på den totala schemalagda tiden för 
	    maskinens jobb. */
	public int getScheduledTime() {
		return scheduledTime;
	}
	
	/** Returnerar en sträng som innehåller maskinens nr,  
	   total schemalagd tid samt maskinens
       schemalagda jobb inom [] med kommatecken mellan.
       Exempel: 2 17 [j2 (14), j7 (3)] */
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("Maskin ");
		b.append(nbr);
		b.append(" har total schemalagd tid ");
		b.append(getScheduledTime());
		b.append(' ');
		b.append('[');
		for (Job j : jobs) {
			b.append(j);
			b.append(", ");
		}
		if (jobs.size() != 0) {
			b.deleteCharAt(b.length() - 1);
			b.deleteCharAt(b.length() - 1);
		}
		b.append(']');
		return b.toString();
	}	
}
package lpt;

public class Job {
	private String name;
	private int time;

	/** Skapar ett jobb med namnet name som tar 
	    tiden time att utföra. */
	public Job(String name, int time) {
		this.name = name;
		this.time = time;
	}
	
	/** Returnerar jobbets tidsåtgång. */
	public int getTime() {
		return time;
	}
	
	/** Returnerar en sträng som representerar jobbet
	 	på formen namn (tidsåtgång). */
	public String toString() {
		return name  + " " + time;
	}	
}
